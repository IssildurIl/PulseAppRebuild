package com.iish.pulse.screens.verification.models

sealed class VerificationEvent {
    object OnVerificationClicked : VerificationEvent()
}