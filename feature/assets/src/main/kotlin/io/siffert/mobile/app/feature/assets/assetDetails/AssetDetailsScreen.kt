package io.siffert.mobile.app.feature.assets.io.siffert.mobile.app.feature.assets.assetDetails

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.siffert.mobile.app.model.data.Asset
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
internal fun AssetDetailsScreen(assetId: String? = null, onBackClick: () -> Unit) {
    val viewModel: AssetDetailsScreenViewModel = koinViewModel { parametersOf(assetId) }
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    AssetDetailScreenContent(uiState = uiState, onBackClick = onBackClick)
}

@Composable
private fun AssetDetailScreenContent(uiState: AssetDetailsScreenUiState, onBackClick: () -> Unit) {
    Scaffold(
        containerColor = Color.Transparent,
        topBar = { AssetDetailsTopBar(onBackClick = onBackClick, onDeleteAssetClick = {}) },
    ) { paddingValues ->
        // todo: Crossfade for loading states -> state.when
        Crossfade(
            modifier = Modifier.padding(paddingValues.calculateTopPadding()),
            targetState = uiState,
        ) {
            when (it) {
                AssetDetailsScreenUiState.Empty,
                AssetDetailsScreenUiState.Error -> {
                    Text(text = "Something went wrong")
                }

                AssetDetailsScreenUiState.Loading -> {
                    Text(text = "Loading...")
                }

                is AssetDetailsScreenUiState.Loaded -> AssetDetails(asset = it.asset)
            }
        }
    }
}

@Composable
private fun AssetDetails(asset: Asset, modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = asset.toString())
    }
}
