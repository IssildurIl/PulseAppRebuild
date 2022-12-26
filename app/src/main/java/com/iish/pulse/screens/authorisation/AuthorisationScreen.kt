package com.iish.pulse.screens.authorisation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.iish.pulse.screens.elements.*
import com.iish.pulse.screens.authorisation.model.AuthorisationEvent
import com.iish.pulse.screens.authorisation.model.AuthorisationViewState
import com.iish.pulse.utils.gilroy_medium
import com.iish.pulseapprebuild.R

@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Composable
fun AuthorisationScreen(
    navController: NavController,
    authorisationViewModel: AuthorisationViewModel
) {

    val viewState = authorisationViewModel.authorisationViewState.observeAsState()
    when (viewState.value) {
        AuthorisationViewState.Loading -> AuthorisationScreenLoading()
        AuthorisationViewState.Success -> {
            LaunchedEffect(key1 = Unit, block = {
                navController.navigate(route = "main_screen")
            })
        }
        AuthorisationViewState.Error -> {
            AuthorisationScreenError()
        }
        AuthorisationViewState.WaitingForUser -> {
            CommonAuthorisationScreen(authorisationViewModel)
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

@Composable
fun CommonAuthorisationScreen(
    authorisationViewModel: AuthorisationViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        val focusManager = LocalFocusManager.current
        var isPasswordVisible by remember { mutableStateOf(false) }

        Title(
            text = stringResource(R.string.app_name)
        )
        RegistrationTextField(
            text = authorisationViewModel.phone,
            placeholder = stringResource(id = R.string.phone_hint),
            leadingIcon = {
                CountryPickerView(
                    countries = authorisationViewModel.countriesList,
                    selectedCountry = authorisationViewModel.mobileCountry,
                    onSelection = { selectedCountry ->
                        authorisationViewModel.mobileCountry = selectedCountry
                    },
                )
            },
            onChange = {
                authorisationViewModel.phone = it
            },
            imeAction = ImeAction.Next,//Show next as IME button
            keyboardType = KeyboardType.Phone, //Plain text keyboard
            keyBoardActions = KeyboardActions(
                onNext = {
                    focusManager.moveFocus(FocusDirection.Down)
                }
            )
        )

        RegistrationTextField(
            text = authorisationViewModel.password,
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
                authorisationViewModel.password = it
            },
            imeAction = ImeAction.Done,
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
        )
        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = stringResource(R.string.alternative_login),
            style = TextStyle(
                fontFamily = gilroy_medium,
                fontSize = 14.sp
            )
        )

        Spacer(modifier = Modifier.height(20.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        )
        {
            SocialMediaSignInButtons(painter = painterResource(id = R.drawable.icon_instagram))
            SocialMediaSignInButtons(painter = painterResource(id = R.drawable.icon_facebook))
            SocialMediaSignInButtons(painter = painterResource(id = R.drawable.icon_apple))
            SocialMediaSignInButtons(painter = painterResource(id = R.drawable.icon_google))
            SocialMediaSignInButtons(painter = painterResource(id = R.drawable.icon_twitter))

        }

        Spacer(modifier = Modifier.height(20.dp))
        Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
            Button(
                onClick = { authorisationViewModel.obtainEvent(AuthorisationEvent.OnAuthClickedScreen) },
                shape = RoundedCornerShape(50.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text(text = stringResource(id = R.string.auth_btn))
            }
        }

        TextButton(
            onClick = {},
            contentPadding = PaddingValues(vertical = 0.dp)
        ) {
            Text(
                text = stringResource(id = R.string.reg_btn),
                color = Color.Black,
                fontFamily = gilroy_medium,
                fontSize = 12.sp,
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
    }
}

