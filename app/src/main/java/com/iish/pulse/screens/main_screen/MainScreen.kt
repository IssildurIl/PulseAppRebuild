package com.iish.pulse.screens.main_screen

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun MainScreen(
    navController: NavController,
    mainScreenViewModel: MainScreenViewModel
) {
    Text( modifier = Modifier.fillMaxWidth(),

        text =
        "Error! Can't connect to the internet.\n" +
                "\n"+
                "\n"+
                "\n"+
                "\n"+
                "Check your internet connection.",
        color = MaterialTheme.colors.error
    )
}