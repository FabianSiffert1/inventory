package io.siffert.mobile.app.core.common.dialog.dialogs

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import io.siffert.mobile.app.core.common.R


@Composable
internal fun EditPriceEventTextField(
    input: String,
    inputLabel: String,
    onInputChange: (String) -> Unit,
    onComplete: (String) -> Unit = {},
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
) {
    val focusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current

    val onCompleteExplicitlyTriggered = {
        keyboardController?.hide()
        onComplete(input)
    }

    Box(modifier = Modifier.fillMaxWidth()) {
        TextField(
            colors =
                TextFieldDefaults.colors(
                    unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                ),
            label = { Text(text = inputLabel) },
            trailingIcon = {
                if (input.isNotEmpty()) {
                    IconButton(onClick = { onInputChange("") }) {
                        Icon(
                            imageVector = Icons.Filled.Clear,
                            contentDescription = stringResource(id = R.string.dialogs_clear_text_field),
                            tint = MaterialTheme.colorScheme.onSurface,
                        )
                    }
                }
            },
            onValueChange = { newValue ->
                if ("\n" in newValue) return@TextField

                val filteredValue = newValue.filter { it.isDigit() || it == '.' }

                onInputChange(filteredValue)
            },
            modifier =
                Modifier.fillMaxWidth()
                    .focusRequester(focusRequester)
                    .onKeyEvent {
                        if (it.key == Key.Enter) {
                            if (input.isBlank()) return@onKeyEvent false
                            onCompleteExplicitlyTriggered()
                            true
                        } else false
                    }
                    .testTag("editPriceEventTextField"),
            shape = RoundedCornerShape(4.dp),
            value = input,
            keyboardOptions =
                    keyboardOptions.copy(keyboardType = KeyboardType.Number)
,
            keyboardActions =
                KeyboardActions(
                    onSearch = {
                            onCompleteExplicitlyTriggered()
                    }
                ),
            singleLine = true,
            maxLines =  1,
        )
    }
}