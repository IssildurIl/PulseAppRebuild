package com.iish.pulse.screens.registration.models

sealed class RegistrationViewState {
    object Loading : RegistrationViewState()
    object Error : RegistrationViewState()
    object Success : RegistrationViewState()
    object WaitingForUser : RegistrationViewState()
}