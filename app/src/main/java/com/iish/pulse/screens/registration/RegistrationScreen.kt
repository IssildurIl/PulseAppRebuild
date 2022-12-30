package com.iish.pulse.screens.registration

import android.Manifest
import android.graphics.Bitmap
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import com.iish.pulse.screens.elements.*
import com.iish.pulse.screens.registration.models.RegistrationEvent
import com.iish.pulse.screens.registration.models.RegistrationViewState
import com.iish.pulse.utils.Utils
import com.iish.pulse.utils.Utils.toUri
import com.iish.pulseapprebuild.R

@Composable
fun RegistrationScreen(
    navController: NavController,
    registrationViewModel: RegistrationViewModel
) {
    val viewState = registrationViewModel.registrationViewState.observeAsState()
    when (viewState.value) {
        RegistrationViewState.Loading -> {

        }

        RegistrationViewState.Success -> {
            LaunchedEffect(key1 = Unit, block = {
                navController.navigate(route = "verification_screen")
            })
        }

        RegistrationViewState.Error -> {
        }
        RegistrationViewState.WaitingForUser -> {
            CommonRegistrationScreen(registrationViewModel = registrationViewModel)
        }
        else -> {
            Text(
                "Error! Can't connect to the internet.\n" +
                        "Check your internet connection.",
                color = MaterialTheme.colors.error
            )
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class, ExperimentalPermissionsApi::class, ExperimentalMaterial3Api::class)
@Composable
fun CommonRegistrationScreen(registrationViewModel: RegistrationViewModel) {

    val nameFieldError = stringResource(id = R.string.name_validation)
    val emailFieldError = stringResource(id = R.string.email_validation)
    val passwordsFieldError = stringResource(id = R.string.password_validation)
    val keyboardController = LocalSoftwareKeyboardController.current

    val context = LocalContext.current

    val focusManager = LocalFocusManager.current
    var isPasswordVisible by remember { mutableStateOf(false) }
    var isReplyPasswordVisible by remember { mutableStateOf(false) }
    //iconPick

    val infoDialog = remember { mutableStateOf(false) }
    val cameraPermission = rememberPermissionState(Manifest.permission.CAMERA)
    val galleryPermission = rememberPermissionState(permission = Manifest.permission.READ_EXTERNAL_STORAGE)

    val cameraLauncher: ManagedActivityResultLauncher<Void?, Bitmap?> =
        rememberLauncherForActivityResult(ActivityResultContracts.TakePicturePreview()) {
            registrationViewModel.pickedImage.value = it?.toUri(context)
            infoDialog.value = false
        }

    val galleryLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) {
            registrationViewModel.pickedImage.value = it
            infoDialog.value = false
        }

    val topAppBarState = rememberTopAppBarState()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(topAppBarState)

    Scaffold(topBar = {
        CustomTopAppBar(
            topAppBarText = stringResource(id = R.string.registration_title),
            onBackPressed = { }
        )
    }) { paddingValues ->
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            painter = painterResource(id = R.drawable.icon_registration_background_bottom),
            contentDescription = "",
            contentScale = ContentScale.FillBounds
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .nestedScroll(scrollBehavior.nestedScrollConnection)
                .padding(paddingValues = paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            ImagePickerView(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                lastSelectedImage = registrationViewModel.pickedImage.value,
                onClicked = {
                    infoDialog.value = it
                }
            )

            Spacer(modifier = Modifier.height(20.dp))

            if (infoDialog.value) {
                InfoDialog(
                    title = stringResource(id = R.string.image_picker_title),
                    description = stringResource(id = R.string.image_picker_subtitle),
                    onDismiss = { infoDialog.value = false },
                    firstBtnText = stringResource(id = R.string.image_picker_camera_btn),
                    onFirstBtnClicked = {
                        if (!cameraPermission.hasPermission) {
                            cameraPermission.launchPermissionRequest()
                        } else
                            cameraLauncher.launch()
                    },
                    secondBtnText = stringResource(id = R.string.image_picker_gallery_btn),
                    onSecondBtnClicked = {
                        if (!galleryPermission.hasPermission) {
                            galleryPermission.launchPermissionRequest()
                        } else
                            galleryLauncher.launch("image/*")
                    },
                    R.raw.image_picker
                )
            }

            //Public Name
            RegistrationTextField(
                text = registrationViewModel.name,
                placeholder = stringResource(id = R.string.public_name_hint),
                onChange = {
                    registrationViewModel.name = it
                },
                imeAction = ImeAction.Next,//Show next as IME button
                keyboardType = KeyboardType.Text, //Plain text keyboard
                keyBoardActions = KeyboardActions(
                    onNext = {
                        focusManager.moveFocus(FocusDirection.Down)
                    }
                ),
                showError = !registrationViewModel.isPublicNameFieldError,
                errorMessage = nameFieldError
            )

            //Phone
            RegistrationWithIconTextField(
                text = registrationViewModel.phone,
                placeholder = stringResource(id = R.string.phone_hint),
                leadingIcon = {
                    CountryPickerView(
                        countries = registrationViewModel.countriesList,
                        selectedCountry = registrationViewModel.mobileCountry,
                        onSelection = { selectedCountry ->
                            registrationViewModel.mobileCountry = selectedCountry
                        },
                    )
                },
                onChange = {
                    registrationViewModel.phone = it
                },
                imeAction = ImeAction.Next,//Show next as IME button
                keyboardType = KeyboardType.Phone, //Plain text keyboard
                keyBoardActions = KeyboardActions(
                    onNext = {
                        focusManager.moveFocus(FocusDirection.Down)
                    }
                ),
                visualTransformation = { Utils.mobileNumberFilter(it) }
            )

            //Email
            RegistrationTextField(
                text = registrationViewModel.email,
                placeholder = stringResource(id = R.string.email_hint),
                onChange = {
                    registrationViewModel.email = it
                },
                imeAction = ImeAction.Next,//Show next as IME button
                keyboardType = KeyboardType.Text, //Plain text keyboard
                keyBoardActions = KeyboardActions(
                    onNext = {
                        focusManager.moveFocus(FocusDirection.Down)
                    }
                ),
                showError = !registrationViewModel.isEmailFieldError,
                errorMessage = emailFieldError
            )


            //Password
            RegistrationWithIconTextField(
                text = registrationViewModel.password,
                placeholder = stringResource(id = R.string.password_hint),
                leadingIcon = {
                    IconButton(onClick = {
                        isPasswordVisible = !isPasswordVisible
                    }) {
                        Icon(
                            imageVector = if (isPasswordVisible)
                                Icons.Filled.Visibility
                            else
                                Icons.Filled.VisibilityOff,
                            contentDescription = "Password Visibility"
                        )
                    }
                },
                onChange = {
                    registrationViewModel.password = it
                },
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Password,
                keyBoardActions = KeyboardActions(
                    onNext = {
                        focusManager.moveFocus(FocusDirection.Down)
                    }
                ),
                visualTransformation = if (isPasswordVisible)
                    VisualTransformation.None
                else
                    PasswordVisualTransformation(),
                showError = !registrationViewModel.isPasswordsSame,
                errorMessage = passwordsFieldError
            )

            //RePassword
            RegistrationWithIconTextField(
                text = registrationViewModel.passwordReply,
                placeholder = stringResource(id = R.string.reply_password_hint),
                leadingIcon = {
                    IconButton(onClick = {
                        isReplyPasswordVisible = !isReplyPasswordVisible
                    }) {
                        Icon(
                            imageVector = if (isReplyPasswordVisible)
                                Icons.Filled.Visibility
                            else
                                Icons.Filled.VisibilityOff,
                            contentDescription = "Password Visibility"
                        )
                    }
                },
                onChange = {
                    registrationViewModel.passwordReply = it
                },
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Password,
                keyBoardActions = KeyboardActions(
                    onDone = { keyboardController?.hide() }),
                visualTransformation = if (isReplyPasswordVisible)
                    VisualTransformation.None
                else
                    PasswordVisualTransformation(),
                showError = !registrationViewModel.isPasswordsSame,
                errorMessage = passwordsFieldError
            )

            Spacer(modifier = Modifier.height(20.dp))

            Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
                Button(
                    onClick = { registrationViewModel.obtainEvent(RegistrationEvent.onRegistrationClicked) },
                    shape = RoundedCornerShape(50.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                ) {
                    Text(text = stringResource(id = R.string.registration_btn))
                }
            }

        }
    }
}