package com.iish.pulse.screens.elements

import android.content.pm.PackageManager
import android.net.Uri
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.core.content.ContextCompat
import coil.compose.rememberImagePainter
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.iish.pulse.screens.theme.Shapes
import com.iish.pulse.screens.theme.pulseAppColor
import com.iish.pulse.screens.theme.socialMediaButtonBackground
import com.iish.pulse.utils.Country
import com.iish.pulse.utils.getFlagEmojiFor
import com.iish.pulse.utils.gilroy_extra_bold
import com.iish.pulse.utils.gilroy_regular
import com.iish.pulseapprebuild.R

@Composable
fun CountryPickerView(
    selectedCountry: Country,
    onSelection: (Country) -> Unit,
    countries: List<Country>
) {
    var showDialog by remember { mutableStateOf(false) }
    Text(
        modifier = Modifier
            .clickable {
                showDialog = true
            }
            .padding(start = 20.dp, end = 5.dp),
        text = "${getFlagEmojiFor(selectedCountry.nameCode)} +${selectedCountry.code}"
    )

    if (showDialog)
        CountryCodePickerDialog(countries, onSelection) {
            showDialog = false
        }
}

@Composable
fun CountryCodePickerDialog(
    countries: List<Country>,
    onSelection: (Country) -> Unit,
    dismiss: () -> Unit,
) {
    Dialog(onDismissRequest = dismiss) {
        Box {
            LazyColumn(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp, vertical = 40.dp)
                    .background(shape = RoundedCornerShape(20.dp), color = Color.White)
            ) {
                for (country in countries) {
                    item {
                        Text(
                            modifier = Modifier
                                .clickable {
                                    onSelection(country)
                                    dismiss()
                                }
                                .fillMaxWidth()
                                .padding(10.dp),
                            text = "${getFlagEmojiFor(country.nameCode)} ${country.fullName}"
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun CustomOutlinedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String = "",
    leadingIconImageVector: ImageVector,
    leadingIconDescription: String = "",
    isPasswordField: Boolean = false,
    isPasswordVisible: Boolean = false,
    isPhoneField: Boolean = false,
    visualPhoneTransformation: VisualTransformation = VisualTransformation.None,
    onVisibilityChange: (Boolean) -> Unit = {},
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    showError: Boolean = false,
    errorMessage: String = ""
) {
    OutlinedTextField(
        value = value,
        onValueChange = { onValueChange(it) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        label = { Text(label) },
        leadingIcon = {
            Icon(
                imageVector = leadingIconImageVector,
                contentDescription = leadingIconDescription,
                tint = if (showError) MaterialTheme.colors.error else MaterialTheme.colors.onPrimary
            )
        },
        isError = showError,
        trailingIcon = {
            if (showError && !isPasswordField)
                Icon(imageVector = Icons.Filled.Error, contentDescription = "Show error icon")
            if (isPasswordField) {
                IconButton(onClick = { onVisibilityChange(!isPasswordVisible) }) {
                    Icon(
                        imageVector = if (isPasswordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                        contentDescription = "Toggle password visibility"
                    )
                }
            }
        },
        visualTransformation = when {
            isPasswordField && isPasswordVisible -> VisualTransformation.None
            isPasswordField -> PasswordVisualTransformation()
            isPhoneField -> visualPhoneTransformation
            else -> VisualTransformation.None
        },
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        singleLine = true
    )
    if (showError) {
        Text(
            text = errorMessage,
            color = MaterialTheme.colors.error,
            style = MaterialTheme.typography.caption,
            modifier = Modifier
                .padding(start = 8.dp)
                .offset(y = (-8).dp)
                .fillMaxWidth(0.9f)
        )
    }
}

@Composable
fun RegistrationTextField(
    modifier: Modifier = Modifier,
    text: String,
    placeholder: String,
    leadingIconImageVector: ImageVector? = null,
    leadingIconDescription: String = "",
    onChange: (String) -> Unit = {},
    imeAction: ImeAction = ImeAction.Next,
    keyboardType: KeyboardType = KeyboardType.Text,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyBoardActions: KeyboardActions = KeyboardActions(),
    isEnabled: Boolean = true,
    showError: Boolean = false,
    errorMessage: String = "",
    isPasswordField: Boolean = false,
    isPasswordVisible: Boolean = false,
    onVisibilityChange: (Boolean) -> Unit = {},
) {
    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        value = text,
        placeholder = {
            Text(text = placeholder, style = TextStyle(fontSize = 18.sp, color = Color.LightGray))
        },
        leadingIcon = {
            leadingIconImageVector?.let {
                Icon(
                    imageVector = it,
                    contentDescription = leadingIconDescription,
                    tint = if (showError) MaterialTheme.colors.error else MaterialTheme.colors.onPrimary
                )
            }
        },
        trailingIcon = {
            if (showError && !isPasswordField)
                Icon(imageVector = Icons.Filled.Error, contentDescription = "Show error icon")
            if (isPasswordField) {
                IconButton(onClick = { onVisibilityChange(!isPasswordVisible) }) {
                    Icon(
                        imageVector = if (isPasswordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                        contentDescription = "Toggle password visibility"
                    )
                }
            }
        },
        isError = showError,
        onValueChange = onChange,
        keyboardOptions = KeyboardOptions(imeAction = imeAction, keyboardType = keyboardType),
        visualTransformation = visualTransformation,
        textStyle = TextStyle(fontSize = 18.sp),
        keyboardActions = keyBoardActions,
        enabled = isEnabled,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.Black,
            unfocusedBorderColor = Color.Gray,
            disabledBorderColor = Color.Gray,
            disabledTextColor = Color.Black,
            backgroundColor = Color.White
        ),
        singleLine = true,
    )
    if (showError) {
        Text(
            text = errorMessage,
            color = MaterialTheme.colors.error,
            style = MaterialTheme.typography.caption,
            modifier = Modifier
                .padding(start = 8.dp)
                .offset(y = (-8).dp)
                .fillMaxWidth(0.9f)
        )
    }
}


@Composable
fun RegistrationWithIconTextField(
    modifier: Modifier = Modifier,
    text: String,
    placeholder: String,
    leadingIcon: @Composable (() -> Unit)? = null,
    onChange: (String) -> Unit = {},
    imeAction: ImeAction = ImeAction.Next,
    keyboardType: KeyboardType = KeyboardType.Text,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyBoardActions: KeyboardActions = KeyboardActions(),
    isEnabled: Boolean = true,
    showError: Boolean = false,
    errorMessage: String = ""
) {
    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        value = text,
        placeholder = {
            Text(text = placeholder, style = TextStyle(fontSize = 18.sp, color = Color.LightGray))
        },
        isError = showError,
        leadingIcon = leadingIcon,
        onValueChange = onChange,
        keyboardOptions = KeyboardOptions(imeAction = imeAction, keyboardType = keyboardType),
        visualTransformation = visualTransformation,
        textStyle = TextStyle(fontSize = 18.sp),
        keyboardActions = keyBoardActions,
        enabled = isEnabled,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.Black,
            unfocusedBorderColor = Color.Gray,
            disabledBorderColor = Color.Gray,
            disabledTextColor = Color.Black,
            backgroundColor = Color.White
        ),
        singleLine = true,

        )
    if (showError) {
        Text(
            text = errorMessage,
            color = MaterialTheme.colors.error,
            style = MaterialTheme.typography.caption,
            modifier = Modifier
                .padding(start = 8.dp)
                .offset(y = (-8).dp)
                .fillMaxWidth(0.9f)
        )
    }
}

@Composable
fun Title(
    text: String,
) {
    Text(
        text = text,
        style = TextStyle(
            fontFamily = gilroy_extra_bold,
            fontWeight = FontWeight.Bold,
            fontSize = 34.sp,
        )
    )
}

@Composable
fun SocialMediaSignInButtons(
    painter: Painter
) {
    Button(
        onClick = {},
        colors = ButtonDefaults.buttonColors(
            backgroundColor = socialMediaButtonBackground,
        ),
        elevation = ButtonDefaults.elevation(defaultElevation = 0.dp),
        modifier = Modifier.clip(
            shape = Shapes.large
        ),
        contentPadding = PaddingValues(horizontal = 10.dp, vertical = 10.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painter,
                contentDescription = "",
                modifier = Modifier.size(20.dp),
                tint = Color.Unspecified
            )
        }
    }
}


@Composable
fun ImagePickerView(
    modifier: Modifier = Modifier,
    lastSelectedImage: Uri?,
    onClicked: (Boolean) -> Unit,
) {
    Image(
        modifier = modifier
            .size(150.dp)
            .clip(CircleShape)
            .background(Color.LightGray)
            .clickable {
                onClicked(true)
            },
        painter = if (lastSelectedImage == null)
            painterResource(R.drawable.icon_add_photo)
        else
            rememberImagePainter(lastSelectedImage),
        contentDescription = "Profile Picture",
        contentScale = ContentScale.Crop
    )
}

@Composable
fun InfoDialog(
    title: String,
    description: String,
    onDismiss: () -> Unit,
    firstBtnText: String,
    onFirstBtnClicked: () -> Unit,
    secondBtnText: String,
    onSecondBtnClicked: () -> Unit,
    rawRes: Int
) {

    Dialog(
        onDismissRequest = onDismiss
    ) {
        Box(modifier = Modifier.height(480.dp)) {
            Column(modifier = Modifier) {
                Spacer(modifier = Modifier.height(130.dp))
                Box(
                    modifier = Modifier
                        .height(490.dp)
                        .background(
                            color = pulseAppColor,
                            shape = RoundedCornerShape(25.dp, 10.dp, 25.dp, 10.dp)
                        )
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Spacer(modifier = Modifier.height(24.dp))

                        Text(
                            text = title,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .padding(top = 10.dp)
                                .fillMaxWidth(),
                            style = MaterialTheme.typography.h3,
                            color = Color.Black,
                            fontFamily = gilroy_regular
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = description,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .padding(top = 10.dp, start = 25.dp, end = 25.dp),
                            style = MaterialTheme.typography.h4,
                            color = Color.Black,
                            fontFamily = gilroy_regular
                        )

                        Spacer(modifier = Modifier.height(24.dp))

                        ElevatedButton(
                            onClick = {
                                onFirstBtnClicked()
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(10.dp))
                        ) {
                            Text(
                                text = firstBtnText,
                                color = Color.Black
                            )
                        }
                        ElevatedButton(
                            onClick = {
                                onSecondBtnClicked()
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(5.dp))
                        ) {
                            Text(text = secondBtnText, color = Color.Black)
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                    }
                }
            }
            HeaderImage(
                modifier = Modifier
                    .size(200.dp)
                    .align(Alignment.TopCenter),
                rawRes = rawRes
            )
        }
    }
}

@Composable
fun HeaderImage(modifier: Modifier, rawRes: Int) {
    val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(rawRes))
    val progress by animateLottieCompositionAsState(composition = composition)

    LottieAnimation(
        composition = composition,
        progress = progress,
        modifier = modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class) // CenterAlignedTopAppBar is experimental in m3
@Composable
fun CustomTopAppBar(topAppBarText: String, onBackPressed: () -> Unit) {
    CenterAlignedTopAppBar(
        title = {
            androidx.compose.material3.Text(
                text = topAppBarText,
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.CenterStart),
            )
        },
        navigationIcon = {
            androidx.compose.material3.IconButton(onClick = onBackPressed) {
                Icon(
                    painter = painterResource(id = R.drawable.icon_back),
                    contentDescription = "Back",
                    tint = androidx.compose.material3.MaterialTheme.colorScheme.primary
                )
            }
        },
        // We need to balance the navigation icon, so we add a spacer.
        actions = {
            Spacer(modifier = Modifier.width(68.dp))
        },
    )
}


/**
 * Composable helper for permission checking
 *
 * onDenied contains lambda for request permission
 *
 * @param permission permission for request
 * @param onGranted composable for [PackageManager.PERMISSION_GRANTED]
 * @param onDenied composable for [PackageManager.PERMISSION_DENIED]
 */
@Composable
fun ComposablePermission(
    permission: String,
    onDenied: @Composable (requester: () -> Unit) -> Unit,
    onGranted: @Composable () -> Unit
) {
    val ctx = LocalContext.current

    // check initial state of permission, it may be already granted
    var grantState by remember {
        mutableStateOf(
            ContextCompat.checkSelfPermission(
                ctx,
                permission
            ) == PackageManager.PERMISSION_GRANTED
        )
    }
    if (grantState) {
        onGranted()
    } else {
        val launcher: ManagedActivityResultLauncher<String, Boolean> =
            rememberLauncherForActivityResult(contract = ActivityResultContracts.RequestPermission()) {
                grantState = it
            }
        onDenied { launcher.launch(permission) }
    }
}