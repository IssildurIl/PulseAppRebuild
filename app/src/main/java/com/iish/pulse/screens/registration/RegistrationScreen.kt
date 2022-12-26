package com.iish.pulse.screens.registration

import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.NavController
import com.iish.pulse.screens.registration.models.RegistrationViewState

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
    Column {

    }

}