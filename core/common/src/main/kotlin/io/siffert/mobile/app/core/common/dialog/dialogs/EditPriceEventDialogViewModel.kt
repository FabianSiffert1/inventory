package io.siffert.mobile.app.core.common.dialog.dialogs

import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import io.siffert.mobile.app.model.data.PriceHistoryEntry
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class EditPriceEventDialogViewModel(priceHistoryEntry: PriceHistoryEntry?) : ViewModel() {

  private val _price =
      MutableStateFlow(
          if (priceHistoryEntry?.value != null) TextFieldValue(priceHistoryEntry.value.toString())
          else TextFieldValue())
  val price = _price.asStateFlow()

  fun onPriceChange(newValue: TextFieldValue) {
    _price.value = newValue
  }
}
