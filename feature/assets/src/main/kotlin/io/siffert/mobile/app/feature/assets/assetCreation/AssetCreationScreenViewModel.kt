package io.siffert.mobile.app.feature.assets.io.siffert.mobile.app.feature.assets.assetCreation

import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.siffert.mobile.app.core.data.repository.AssetRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.uuid.ExperimentalUuidApi

data class AssetCreationScreenUiState
@OptIn(ExperimentalUuidApi::class)
constructor(
    val nameInput: TextFieldValue = TextFieldValue(),
    val feesInput: TextFieldValue = TextFieldValue(),
    val urlInput: TextFieldValue = TextFieldValue(),
    val notesInput: TextFieldValue = TextFieldValue(),
) {
    val isValidAsset = nameInput.text.isNotEmpty()
}

class AssetCreationScreenViewModel(private val assetRepository: AssetRepository) : ViewModel() {

    private val _uiState = MutableStateFlow(AssetCreationScreenUiState())
    val uiState = _uiState.asStateFlow()

    fun onNameChange(newName: String) {
        _uiState.update { it.copy(nameInput = TextFieldValue(newName)) }
    }

    fun onFeesChange(newFees: String) {
        val fees = newFees.toDoubleOrNull()
        _uiState.update { it.copy(feesInput = TextFieldValue(fees.toString())) }
    }

    fun onNotesChange(newNotes: String) {
        _uiState.update { it.copy(notesInput = TextFieldValue(newNotes)) }
    }

    fun onUrlChange(newUrl: String) {
        _uiState.update { it.copy(urlInput = TextFieldValue(newUrl)) }
    }

    fun createAsset() {
        viewModelScope.launch {
            val state = _uiState.value
            if (!state.isValidAsset) return@launch

            //          val asset =
            //                Asset()
            // todo: implement return success/failure etc
            //  uiState.value.asset.let { assetRepository.insertOrIgnoreAsset(listOf(it)) }
            // on success -> navigate back
        }
    }
}
