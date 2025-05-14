package io.siffert.mobile.app.feature.assets.io.siffert.mobile.app.feature.assets.assetCreation

import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import io.siffert.mobile.app.core.data.repository.AssetRepository
import io.siffert.mobile.app.model.data.Asset
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

data class AssetCreationScreenUiState(
    val asset: Asset? = null,
    val assetNameInput: TextFieldValue = TextFieldValue(),
) {}

class AssetCreationScreenViewModel(private val assetRepository: AssetRepository) : ViewModel() {

    private val _uiState = MutableStateFlow(AssetCreationScreenUiState())
    val uiState = _uiState.asStateFlow()

    suspend fun createAsset() {
        // todo: implement return success/failure etc
        if (uiState.value.asset == null) return
        uiState.value.asset?.let { assetRepository.insertOrIgnoreAsset(listOf(it)) }
    }
}
