package com.dicedev.thebigchampion.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.dicedev.thebigchampion.utils.AppColors

@Composable
fun MainTextField(
    valueState: MutableState<String>,
    label: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    isSingleLine: Boolean = true,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Next,
    keyboardAction: KeyboardActions = KeyboardActions.Default,
    isPassword: Boolean = false,
    visibility: Boolean = true,
    onTrailingIconClicked: () -> Unit = {}
) {
    var visualTransformation = VisualTransformation.None

    if (!visibility) {
        visualTransformation = PasswordVisualTransformation()
    }
    OutlinedTextField(
        value = valueState.value,
        onValueChange = { valueState.value = it },
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 10.dp),
        label = { Text(text = label) },
        enabled = enabled,
        singleLine = isSingleLine,
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            imeAction = imeAction
        ),
        keyboardActions = keyboardAction,
        visualTransformation = visualTransformation,
        shape = RoundedCornerShape(12.dp),
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = AppColors.lightBlue,
            backgroundColor = Color.Transparent
        ),
        trailingIcon = {
            if (isPassword) {
                IconButton(onClick = { onTrailingIconClicked.invoke() }) {
                    Icon(
                        imageVector = if (visibility) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                        contentDescription = "visibility toggle icon"
                    )
                }
            }
        }
    )
}

@Composable
fun EmailTextField(emailState: MutableState<String>, label: String = "Email") {
    MainTextField(valueState = emailState, label = label)
}

@Composable
fun PasswordTextField(passwordState: MutableState<String>, label: String = "Password") {
    val visibility = remember {
        mutableStateOf(false)
    }
    MainTextField(
        valueState = passwordState,
        label = label,
        isPassword = true,
        visibility = visibility.value
    ) {
        visibility.value = !visibility.value
    }
}