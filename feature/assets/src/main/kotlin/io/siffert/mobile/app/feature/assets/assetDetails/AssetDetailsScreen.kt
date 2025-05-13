package io.siffert.mobile.app.feature.assets.io.siffert.mobile.app.feature.assets.assetDetails

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
internal fun AssetDetailsScreen(assetId: String? = null, onBackClick: () -> Unit) {
    val viewModel: AssetDetailsScreenViewModel = koinViewModel { parametersOf(assetId) }
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    AssetDetailScreenContent(
        uiState = uiState,
        onBackClick = onBackClick,
        onDeleteAssetClick = {
            if (assetId != null) {
                viewModel.deleteAsset(assetId)
            }
        },
    )
}

@Composable
private fun AssetDetailScreenContent(
    uiState: AssetDetailsScreenUiState,
    onBackClick: () -> Unit,
    onDeleteAssetClick: () -> Unit,
) {
    Scaffold(
        modifier = Modifier.statusBarsPadding(),
        containerColor = Color.Transparent,
        topBar = {
            AssetDetailsTopBar(onBackClick = onBackClick, onDeleteAssetClick = onDeleteAssetClick)
        },
    ) { paddingValues ->
        // todo: Crossfade for loading states -> state.when
        Crossfade(modifier = Modifier.padding(paddingValues), targetState = uiState) {
            when (it) {
                AssetDetailsScreenUiState.Empty,
                AssetDetailsScreenUiState.Error,
                AssetDetailsScreenUiState.Loading -> {
                    AssetDetailsEmptyStateListItem(state = it)
                }
                is AssetDetailsScreenUiState.Loaded -> AssetDetailsList(asset = it.asset)
            }
        }
    }
}

// todo: replace with proper loading state with animation
@Composable
private fun AssetDetailsEmptyStateListItem(state: AssetDetailsScreenUiState) {
    ListItem(
        modifier = Modifier.fillMaxWidth(),
        headlineContent = {
            val title =
                when (state) {
                    AssetDetailsScreenUiState.Empty -> "No asset data found"
                    AssetDetailsScreenUiState.Error -> "Something went wrong"
                    AssetDetailsScreenUiState.Loading -> "Loading..."
                    is AssetDetailsScreenUiState.Loaded -> ""
                }
            Text(text = title, fontWeight = FontWeight.SemiBold)
        },
    )
}
