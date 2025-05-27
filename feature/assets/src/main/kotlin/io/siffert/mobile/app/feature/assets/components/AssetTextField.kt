package io.siffert.mobile.app.feature.assets.io.siffert.mobile.app.feature.assets.components

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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
internal fun AssetTextField(
    input: String,
    inputLabel: String,
    onInputChange: (String) -> Unit,
    onComplete: (String) -> Unit = {},
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    numericOnly: Boolean = false,
    isNoteBlock: Boolean = false,
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
                if (!isNoteBlock && input.isNotEmpty()) {
                    IconButton(onClick = { onInputChange("") }) {
                        Icon(
                            imageVector = Icons.Filled.Clear,
                            contentDescription = "Clear text field",
                            tint = MaterialTheme.colorScheme.onSurface,
                        )
                    }
                }
            },
            onValueChange = { newValue ->
                if (!isNoteBlock && "\n" in newValue) return@TextField

                val filteredValue =
                    if (numericOnly) {
                        newValue.filter { it.isDigit() || it == '.' }
                    } else {
                        newValue
                    }

                onInputChange(filteredValue)
            },
            modifier =
                Modifier.fillMaxWidth()
                    .focusRequester(focusRequester)
                    .onKeyEvent {
                        if (!isNoteBlock && it.key == Key.Enter) {
                            if (input.isBlank()) return@onKeyEvent false
                            onCompleteExplicitlyTriggered()
                            true
                        } else {
                            false
                        }
                    }
                    .testTag("assetTextField"),
            shape = RoundedCornerShape(4.dp),
            value = input,
            keyboardOptions =
                if (numericOnly) {
                    keyboardOptions.copy(keyboardType = KeyboardType.Number)
                } else {
                    keyboardOptions
                },
            keyboardActions =
                KeyboardActions(
                    onSearch = {
                        if (!isNoteBlock && input.isNotBlank()) {
                            onCompleteExplicitlyTriggered()
                        }
                    }
                ),
            singleLine = !isNoteBlock,
            maxLines = if (isNoteBlock) Int.MAX_VALUE else 1,
        )

        if (isNoteBlock && input.isNotEmpty()) {
            IconButton(
                onClick = { onInputChange("") },
                modifier = Modifier.align(Alignment.TopEnd).padding(top = 4.dp, end = 4.dp),
            ) {
                Icon(
                    imageVector = Icons.Filled.Clear,
                    contentDescription = "Clear notes field",
                    tint = MaterialTheme.colorScheme.onSurface,
                )
            }
        }
    }
}
