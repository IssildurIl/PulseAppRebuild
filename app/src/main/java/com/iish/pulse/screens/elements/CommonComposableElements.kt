package com.iish.pulse.screens.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.iish.pulse.screens.theme.Shapes
import com.iish.pulse.screens.theme.socialMediaButtonBackground
import com.iish.pulse.utils.Country
import com.iish.pulse.utils.getFlagEmojiFor
import com.iish.pulse.utils.gilroy_extra_bold

@Composable
fun CountryPickerView(
    selectedCountry: Country,
    onSelection: (Country) -> Unit,
    countries: List<Country>
) {
    var showDialog by remember { mutableStateOf(false) }
    Text(
        modifier = Modifier
            .clickable {
                showDialog = true
            }
            .padding(start = 20.dp, end = 5.dp),
        text = "${getFlagEmojiFor(selectedCountry.nameCode)} +${selectedCountry.code}"
    )

    if (showDialog)
        CountryCodePickerDialog(countries, onSelection) {
            showDialog = false
        }
}

@Composable
fun CountryCodePickerDialog(
    countries: List<Country>,
    onSelection: (Country) -> Unit,
    dismiss: () -> Unit,
) {
    Dialog(onDismissRequest = dismiss) {
        Box {
            LazyColumn(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp, vertical = 40.dp)
                    .background(shape = RoundedCornerShape(20.dp), color = Color.White)
            ) {
                for (country in countries) {
                    item {
                        Text(
                            modifier = Modifier
                                .clickable {
                                    onSelection(country)
                                    dismiss()
                                }
                                .fillMaxWidth()
                                .padding(10.dp),
                            text = "${getFlagEmojiFor(country.nameCode)} ${country.fullName}"
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun RegistrationTextField(
    modifier: Modifier = Modifier,
    text: String,
    placeholder: String,
    leadingIcon: @Composable (() -> Unit)? = null,
    onChange: (String) -> Unit = {},
    imeAction: ImeAction = ImeAction.Next,
    keyboardType: KeyboardType = KeyboardType.Text,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyBoardActions: KeyboardActions = KeyboardActions(),
    isEnabled: Boolean = true
) {
    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        value = text,
        placeholder = {
            Text(text = placeholder, style = TextStyle(fontSize = 18.sp, color = Color.LightGray))
        },
        leadingIcon = leadingIcon,
        onValueChange = onChange,
        keyboardOptions = KeyboardOptions(imeAction = imeAction, keyboardType = keyboardType),
        visualTransformation = visualTransformation,
        textStyle = TextStyle(fontSize = 18.sp),
        keyboardActions = keyBoardActions,
        enabled = isEnabled,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.Black,
            unfocusedBorderColor = Color.Gray,
            disabledBorderColor = Color.Gray,
            disabledTextColor = Color.Black
        )
    )
}

@Composable
fun Title(
    text: String,
) {
    Text(
        text = text,
        style = TextStyle(
            fontFamily = gilroy_extra_bold,
            fontWeight = FontWeight.Bold,
            fontSize = 34.sp,
        )
    )
}

@Composable
fun SocialMediaSignInButtons(
    painter: Painter
) {
    Button(
        onClick = {},
        colors = ButtonDefaults.buttonColors(
            backgroundColor = socialMediaButtonBackground,
        ),
        elevation = ButtonDefaults.elevation(defaultElevation = 0.dp),
        modifier = Modifier.clip(
            shape = Shapes.large
        ),
        contentPadding = PaddingValues(horizontal = 10.dp, vertical = 10.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painter,
                contentDescription = "",
                modifier = Modifier.size(20.dp),
                tint = Color.Unspecified
            )
        }
    }

}

