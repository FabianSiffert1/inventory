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
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.siffert.mobile.app.feature.assets.R
import io.siffert.mobile.app.feature.assets.io.siffert.mobile.app.feature.assets.components.AssetTextField
import org.koin.compose.viewmodel.koinViewModel

@Composable
internal fun AssetCreationScreen(navigateBack: () -> Unit) {
    val viewModel: AssetCreationScreenViewModel = koinViewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    AssetDetailScreenContent(
        uiState = uiState,
        onBackClick = navigateBack,
        onNameChange = viewModel::onNameChange,
        onFeesChange = viewModel::onFeesChange,
        onNotesChange = viewModel::onNotesChange,
        onUrlChange = viewModel::onUrlChange,
        onCreateAssetClick = {
            try {
                viewModel.createAsset()
                navigateBack()
            } catch (e: Exception) {
                println(e)
            }
        },
    )
}

@Composable
private fun AssetDetailScreenContent(
    uiState: AssetCreationScreenUiState,
    onBackClick: () -> Unit,
    onCreateAssetClick: () -> Unit,
    onNotesChange: (String) -> Unit,
    onFeesChange: (String) -> Unit,
    onNameChange: (String) -> Unit,
    onUrlChange: (String) -> Unit,
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
                input = uiState.nameInput.text,
                onInputChange = onNameChange,
                inputLabel = stringResource(id = R.string.feature_assets_asset_creation_name),
            )
            AssetTextField(
                input = uiState.urlInput.text,
                onInputChange = onUrlChange,
                inputLabel = stringResource(id = R.string.feature_assets_asset_creation_url),
            )
            AssetTextField(
                input = uiState.notesInput.text,
                onInputChange = onNotesChange,
                inputLabel = stringResource(id = R.string.feature_assets_asset_creation_notes),
            )
        }
    }
}
