package com.iish.pulse.screens.registration.models

sealed class RegistrationEvent {
    object onRegistrationClicked : RegistrationEvent()
}