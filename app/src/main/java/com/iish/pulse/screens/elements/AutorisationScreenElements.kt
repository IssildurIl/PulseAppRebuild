package com.iish.pulse.screens.elements

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.iish.pulseapprebuild.R
import kotlinx.coroutines.launch

@Composable
fun AuthorisationScreenLoading() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        CircularProgressIndicator(
            modifier = Modifier.align(Alignment.Center)
        )
    }
}


@ExperimentalMaterialApi
@Composable
fun AuthorisationScreenError(
) {
    val modalBottomSheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Expanded)
    val scope = rememberCoroutineScope()
    ModalBottomSheetLayout(
        sheetState = modalBottomSheetState,
        sheetContent = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .clickable {
                        scope.launch {
                            modalBottomSheetState.show()
                        }
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(text = stringResource(id = R.string.auth_error), fontSize = 38.sp)
                Icon(painter = painterResource(id = R.drawable.icon_twitter), contentDescription = "Twit")
            }
        },
        sheetBackgroundColor = Color.Red,
        sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
    ) {
    }
}