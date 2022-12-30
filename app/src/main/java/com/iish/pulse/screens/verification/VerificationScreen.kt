package com.iish.pulse.screens.verification

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.iish.pulse.screens.elements.CustomTopAppBar
import com.iish.pulse.screens.registration.models.RegistrationViewState
import com.iish.pulseapprebuild.R

@Composable
fun VerificationScreen(
    navController: NavController,
    verificationViewModel: VerificationViewModel
) {
    val viewState = verificationViewModel.verificationViewState.observeAsState()
    when (viewState.value) {
        RegistrationViewState.Loading -> {

        }

        RegistrationViewState.Success -> {

        }

        RegistrationViewState.Error -> {
        }
        RegistrationViewState.WaitingForUser -> {
            CommonVerificationScreen(verificationViewModel = verificationViewModel)
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommonVerificationScreen(verificationViewModel: VerificationViewModel) {

    val topAppBarState = rememberTopAppBarState()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(topAppBarState)
    var otpCode by remember {
        mutableStateOf("")
    }

    Scaffold(topBar = {
        CustomTopAppBar(
            topAppBarText = stringResource(id = R.string.activation_title),
            onBackPressed = { }
        )
    }) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .nestedScroll(scrollBehavior.nestedScrollConnection)
                .padding(paddingValues = paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            BasicTextField(
                value = otpCode,
                onValueChange = {
                    if (it.length <= 4) {
                        otpCode = it
                    }
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.NumberPassword
                ),
                decorationBox = {
                    Row(horizontalArrangement = Arrangement.Center) {
                        repeat(4) { index ->
                            val char = when {
                                index >= otpCode.length -> ""
                                else -> otpCode[index].toString()
                            }
                            val isFocused = otpCode.length == index
                            Text(
                                modifier = Modifier
                                    .width(40.dp)
                                    .border(
                                        if (isFocused) 2.dp
                                        else 1.dp,
                                        if (isFocused) Color.DarkGray else Color.LightGray,
                                        RoundedCornerShape(8.dp)
                                    )
                                    .padding(2.dp),
                                text = char,
                                style = MaterialTheme.typography.h4,
                                color = Color.DarkGray,
                                textAlign = TextAlign.Center
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                        }
                    }
                }
            )
        }
    }
}