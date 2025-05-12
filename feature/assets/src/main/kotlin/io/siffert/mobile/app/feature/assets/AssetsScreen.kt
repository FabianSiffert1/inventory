package io.siffert.mobile.app.feature.assets

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.koinViewModel

@Composable
internal fun AssetsScreen(onAssetClick: (String) -> Unit, modifier: Modifier = Modifier) {
    val viewModel: AssetsScreenViewModel = koinViewModel()
    val uiState: AssetsScreenUiState by viewModel.uiState.collectAsStateWithLifecycle()

    Assets(uiState = uiState, onAssetClick = onAssetClick, modifier = modifier)
}

@Composable
internal fun Assets(
    uiState: AssetsScreenUiState,
    modifier: Modifier = Modifier,
    onAssetClick: (String) -> Unit = {},
) =
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        when (uiState) {
            // todo: create loadingState
            AssetsScreenUiState.Loading -> Text("LoadingStateImplPlaceholder")
            is AssetsScreenUiState.Success ->
                AssetList(assetList = uiState.assetList, onAssetClick = onAssetClick)
        }
    }
