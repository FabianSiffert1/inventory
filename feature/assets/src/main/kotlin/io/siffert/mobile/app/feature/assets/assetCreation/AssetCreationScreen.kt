package io.siffert.mobile.app.feature.assets.io.siffert.mobile.app.feature.assets.assetCreation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.siffert.mobile.app.feature.assets.io.siffert.mobile.app.feature.assets.components.AssetTextField
import org.koin.compose.viewmodel.koinViewModel

@Composable
internal fun AssetCreationScreen(navigateBack: () -> Unit) {
    val viewModel: AssetCreationScreenViewModel = koinViewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    AssetDetailScreenContent(
        uiState = uiState,
        onBackClick = navigateBack,
        onInputChange = viewModel::onInputChange,
        onCreateAssetClick = {},
    )
}

@Composable
private fun AssetDetailScreenContent(
    uiState: AssetCreationScreenUiState,
    onInputChange: (field: AssetCreationTextField, newValue: String) -> Unit,
    onBackClick: () -> Unit,
    onCreateAssetClick: () -> Unit,
) {
    Scaffold(
        modifier = Modifier.statusBarsPadding(),
        containerColor = Color.Transparent,
        topBar = {
            AssetCreationTopBar(onBackClick = onBackClick, onCreateAssetClick = onCreateAssetClick)
        },
    ) { paddingValues ->
        Column(
            modifier =
                Modifier.padding(paddingValues).verticalScroll(rememberScrollState()).fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            AssetTextField(
                input = uiState.textInputs[AssetCreationTextField.NAME]?.text.orEmpty(),
                onInputChange = { onInputChange(AssetCreationTextField.NAME, it) },
            )
            AssetTextField(
                input =
                    uiState.textInputs[AssetCreationTextField.ACQUISITION_PRICE]?.text.orEmpty(),
                onInputChange = { onInputChange(AssetCreationTextField.ACQUISITION_PRICE, it) },
            )
            AssetTextField(
                input = uiState.textInputs[AssetCreationTextField.ACQUISITION_DATE]?.text.orEmpty(),
                onInputChange = { onInputChange(AssetCreationTextField.ACQUISITION_DATE, it) },
            )
        }
    }
}
