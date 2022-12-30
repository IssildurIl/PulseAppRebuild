package com.iish.pulse.screens.verification

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.iish.pulse.screens.elements.CustomTopAppBar
import com.iish.pulse.screens.registration.models.RegistrationEvent
import com.iish.pulse.screens.registration.models.RegistrationViewState
import com.iish.pulse.screens.verification.models.VerificationEvent
import com.iish.pulse.utils.gilroy_regular
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

            Text(
                text = stringResource(id = R.string.verification_hint),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(top = 10.dp)
                    .fillMaxWidth(),
                style = MaterialTheme.typography.h5,
                color = Color.Black,
                fontFamily = gilroy_regular
            )

            Spacer(modifier = Modifier.height(18.dp))

            BasicTextField(
                value = otpCode,
                onValueChange = {
                    if (it.length <= 6) {
                        otpCode = it
                    }
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.NumberPassword
                ),
                decorationBox = {
                    Row(horizontalArrangement = Arrangement.Center) {
                        repeat(6) { index ->
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

            Spacer(modifier = Modifier.height(18.dp))

            Text(
                text = stringResource(id = R.string.verification_dont_recieve),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(top = 10.dp)
                    .fillMaxWidth()
                    .clickable(onClick = {

                    }),
                style = MaterialTheme.typography.subtitle1,
                color = Color.Black,
                fontFamily = gilroy_regular
            )

            Spacer(modifier = Modifier.height(18.dp))

            ElevatedButton(
                onClick = {
                    verificationViewModel.obtainEvent(VerificationEvent.OnVerificationClicked)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .clip(RoundedCornerShape(5.dp))
            ) {
                Text(text = stringResource(id = R.string.verification_btn), color = Color.Black)
            }
        }
    }
}