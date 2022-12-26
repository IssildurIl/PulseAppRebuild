package com.iish.pulse.screens.authorisation.model

sealed class AuthorisationEvent {
    object OnAuthClickedScreen : AuthorisationEvent()
}