package com.iish.pulse.navigation

sealed class Screen(val route:String) {
    object AuthorisationScreen: Screen("authorisation_screen")
    object RegistrationScreen: Screen("registration_screen")
    object VerificationScreen: Screen("verification_screen")
    object MainScreen: Screen("main_screen")

}