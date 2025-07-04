package io.siffert.mobile.app.core.common.dialog.dialogs

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.waitForUpOrCancellation
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerEventPass
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.siffert.mobile.app.core.common.R
import io.siffert.mobile.app.model.data.PriceHistoryEntry
import kotlinx.datetime.Clock
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun EditPriceEventDialog(
    priceHistoryEntry: PriceHistoryEntry?,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
    message: String? = null,
) {
  val viewModel: EditPriceEventDialogViewModel = remember {
    EditPriceEventDialogViewModel(priceHistoryEntry = priceHistoryEntry)
  }
  val price by viewModel.price.collectAsStateWithLifecycle()

  EditPriceEventDialogContent(
      modifier = modifier,
      priceHistoryEntry = priceHistoryEntry,
      message = message,
      onDismiss = onDismiss,
      onConfirm = onConfirm,
      price = price,
      onPriceInputChange = viewModel::onPriceChange)
}

@Composable
private fun EditPriceEventDialogContent(
    modifier: Modifier,
    priceHistoryEntry: PriceHistoryEntry?,
    message: String?,
    onPriceInputChange: (TextFieldValue) -> Unit,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
    price: TextFieldValue,
) {
  Column(
      modifier
          .fillMaxWidth()
          .background(MaterialTheme.colorScheme.surface, shape = RoundedCornerShape(8.dp))
          .padding(16.dp),
      verticalArrangement = Arrangement.spacedBy(12.dp)) {
        val title =
            if (priceHistoryEntry == null)
                stringResource(id = R.string.dialogs_price_history_editor_create)
            else stringResource(id = R.string.dialogs_price_history_editor_edit)
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface,
        )

        EditPriceEventTextField(
            input = price,
            inputLabel = stringResource(id = R.string.dialogs_price_history_editor_price),
            onInputChange = onPriceInputChange)

        DatePickerFieldToModal(selectedDate = priceHistoryEntry?.timestamp?.toEpochMilliseconds())

        message?.let {
          Text(
              text = it,
              style = MaterialTheme.typography.bodyMedium,
              color = MaterialTheme.colorScheme.onSurface,
          )
        }

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
          TextButton(
              onClick = onDismiss,
              shape = RoundedCornerShape(16.dp),
          ) {
            Text(
                text = stringResource(id = R.string.dialogs_cancel),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
            )
          }
          TextButton(
              onClick = onConfirm,
              shape = RoundedCornerShape(16.dp),
          ) {
            Text(
                text = stringResource(id = R.string.dialogs_confirm),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface,
            )
          }
        }
      }
}

@Composable
fun DatePickerFieldToModal(selectedDate: Long?, modifier: Modifier = Modifier) {
  val dateToDisplay = selectedDate ?: Clock.System.now().toEpochMilliseconds()
  var selectedDate by remember { mutableStateOf<Long?>(dateToDisplay) }
  var showModal by remember { mutableStateOf(false) }

  OutlinedTextField(
      value = selectedDate?.let { convertMillisToDate(it) } ?: "",
      onValueChange = {},
      label = { Text(stringResource(id = R.string.dialogs_price_history_editor_date)) },
      placeholder = { Text("DD/MM/YYYY") },
      trailingIcon = {
        Icon(
            Icons.Default.DateRange,
            contentDescription =
                stringResource(id = R.string.dialogs_price_history_editor_date_description))
      },
      modifier =
          modifier.fillMaxWidth().pointerInput(selectedDate) {
            awaitEachGesture {
              // Modifier.clickable doesn't work for text fields, so we use Modifier.pointerInput
              // in the Initial pass to observe events before the text field consumes them
              // in the Main pass.
              awaitFirstDown(pass = PointerEventPass.Initial)
              val upEvent = waitForUpOrCancellation(pass = PointerEventPass.Initial)
              if (upEvent != null) {
                showModal = true
              }
            }
          })

  if (showModal) {
    DatePickerModal(onDateSelected = { selectedDate = it }, onDismiss = { showModal = false })
  }
}

@Composable
fun DatePickerModal(onDateSelected: (Long?) -> Unit, onDismiss: () -> Unit) {
  val datePickerState = rememberDatePickerState()

  DatePickerDialog(
      onDismissRequest = onDismiss,
      confirmButton = {
        TextButton(
            onClick = {
              onDateSelected(datePickerState.selectedDateMillis)
              onDismiss()
            }) {
              Text(stringResource(id = R.string.dialogs_confirm))
            }
      },
      dismissButton = {
        TextButton(onClick = onDismiss) { Text(stringResource(id = R.string.dialogs_cancel)) }
      }) {
        DatePicker(state = datePickerState)
      }
}

fun convertMillisToDate(millis: Long): String {
  val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
  return formatter.format(Date(millis))
}
