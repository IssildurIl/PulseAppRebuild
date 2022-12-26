package com.iish.pulse.screens.registration

import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.navigation.NavController
import com.iish.pulse.screens.elements.CountryPickerView
import com.iish.pulse.screens.elements.MyCoolForm
import com.iish.pulse.screens.elements.RegistrationTextField
import com.iish.pulse.screens.registration.models.RegistrationViewState
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

@Composable
fun CommonRegistrationScreen(registrationViewModel: RegistrationViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        val focusManager = LocalFocusManager.current
        var isPasswordVisible by remember { mutableStateOf(false) }

        //Public Name
        RegistrationTextField(
            text = registrationViewModel.firstName,
            placeholder = "First Name",
            onChange = {
                registrationViewModel.firstName = it
            },
            imeAction = ImeAction.Next,//Show next as IME button
            keyboardType = KeyboardType.Text, //Plain text keyboard
            keyBoardActions = KeyboardActions(
                onNext = {
                    focusManager.moveFocus(FocusDirection.Down)
                }
            )
        )

        //Phone
        RegistrationTextField(
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
            )
        )

        //Password
        RegistrationTextField(
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

        //RePassword
        RegistrationTextField(
            text = registrationViewModel.passwordReply,
            placeholder = stringResource(id = R.string.reply_password_hint),
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

    }

}