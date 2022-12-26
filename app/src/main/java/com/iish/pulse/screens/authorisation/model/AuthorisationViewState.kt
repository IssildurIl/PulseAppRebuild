package com.iish.pulse.screens.authorisation.model

sealed class AuthorisationViewState {
    object Loading : AuthorisationViewState()
    object Error : AuthorisationViewState()
    object Success : AuthorisationViewState()
    object WaitingForUser : AuthorisationViewState()

}