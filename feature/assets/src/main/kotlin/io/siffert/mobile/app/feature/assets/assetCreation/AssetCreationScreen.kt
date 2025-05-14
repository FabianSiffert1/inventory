package io.siffert.mobile.app.feature.assets.io.siffert.mobile.app.feature.assets.assetCreation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.compose.viewmodel.koinViewModel

@Composable
internal fun AssetCreationScreen(navigateBack: () -> Unit) {
    val viewModel: AssetCreationScreenViewModel = koinViewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    AssetDetailScreenContent(uiState = uiState, onBackClick = navigateBack, onCreateAssetClick = {})
}

@Composable
private fun AssetDetailScreenContent(
    uiState: AssetCreationScreenUiState,
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
        Column(modifier = Modifier.padding(paddingValues)) {
            TextField(value = "${uiState.asset?.name} asd", onValueChange = {})
        }
    }
}
