package io.siffert.mobile.app.core.common.dialog.dialogs

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import io.siffert.mobile.app.core.common.R

@Composable
internal fun EditPriceEventTextField(
    input: TextFieldValue,
    inputLabel: String,
    onInputChange: (TextFieldValue) -> Unit,
    onComplete: (String) -> Unit = {},
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
) {
    val focusManager = LocalFocusManager.current

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
          if (input.text.isNotEmpty()) {
            IconButton(onClick = { onInputChange(TextFieldValue()) }) {
              Icon(
                  imageVector = Icons.Filled.Clear,
                  contentDescription = stringResource(id = R.string.dialogs_clear_text_field),
                  tint = MaterialTheme.colorScheme.onSurface,
              )
            }
          }
        },
        onValueChange = { newValue ->
            val filtered = newValue.text.filter { it.isDigit() || it == '.' }
            val adjusted = newValue.copy(text = filtered)
            onInputChange(adjusted)
        },
        modifier =
            Modifier.fillMaxWidth()
                .testTag("editPriceEventTextField"),
        shape = RoundedCornerShape(4.dp),
        value = input,
        keyboardOptions = keyboardOptions.copy(keyboardType = KeyboardType.Number),
        keyboardActions = KeyboardActions { focusManager.clearFocus() },
        singleLine = true,
        maxLines = 1,
    )
  }
}
