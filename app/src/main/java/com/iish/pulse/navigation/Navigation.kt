package com.iish.pulse.navigation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.iish.pulse.screens.authorisation.AuthorisationScreen
import com.iish.pulse.screens.authorisation.AuthorisationViewModel
import com.iish.pulse.screens.main_screen.MainScreen
import com.iish.pulse.screens.main_screen.MainScreenViewModel
import com.iish.pulse.screens.registration.RegistrationScreen
import com.iish.pulse.screens.registration.RegistrationViewModel
import com.iish.pulse.screens.verification.VerificationScreen

@ExperimentalMaterialApi
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.AuthorisationScreen.route) {
        composable(route = Screen.AuthorisationScreen.route) {
            val authorisationViewModel = hiltViewModel<AuthorisationViewModel>()
            AuthorisationScreen(navController, authorisationViewModel)
        }
        composable(route = Screen.RegistrationScreen.route) {
            val registrationViewModel = hiltViewModel<RegistrationViewModel>()
            RegistrationScreen(navController, registrationViewModel)
        }
        composable(route = Screen.VerificationScreen.route) {
            val registrationViewModel = hiltViewModel<RegistrationViewModel>()
            VerificationScreen(navController, registrationViewModel)
        }
        composable(route = Screen.MainScreen.route) {
            val mainScreenViewModel = hiltViewModel<MainScreenViewModel>()
            MainScreen(navController, mainScreenViewModel)
        }
    }
}