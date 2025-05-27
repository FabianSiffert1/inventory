package io.siffert.mobile.app.feature.assets

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.siffert.mobile.app.feature.assets.io.siffert.mobile.app.feature.assets.AssetsScreenTopBar
import io.siffert.mobile.app.feature.assets.io.siffert.mobile.app.feature.assets.assetOverview.EmptyAssetOverviewList
import io.siffert.mobile.app.feature.assets.io.siffert.mobile.app.feature.assets.assetOverviewList.AssetOverviewList
import io.siffert.mobile.app.feature.assets.io.siffert.mobile.app.feature.assets.components.AssetListLoadingState
import io.siffert.mobile.app.inventory.core.designsystem.icons.Code
import io.siffert.mobile.app.inventory.core.designsystem.theme.Cozy
import org.koin.androidx.compose.koinViewModel

@Composable
internal fun AssetsScreen(
    onAssetClick: (String) -> Unit,
    onCreateAssetClick: () -> Unit,
    onSearchClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val viewModel: AssetsScreenViewModel = koinViewModel()
    val uiState: AssetsScreenUiState by viewModel.uiState.collectAsStateWithLifecycle()

    Assets(
        uiState = uiState,
        isDebug = viewModel.isDebug,
        onAssetClick = onAssetClick,
        onCreateAssetClick = onCreateAssetClick,
        onSearchClick = onSearchClick,
        onDebugAddExampleAssets = viewModel::debugAddExampleAssets,
        onDebugDeleteAllAssetsClick = viewModel::debugDeleteAllAssets,
        modifier = modifier,
    )
}

@Composable
internal fun Assets(
    uiState: AssetsScreenUiState,
    modifier: Modifier = Modifier,
    onCreateAssetClick: () -> Unit = {},
    onSearchClick: () -> Unit = {},
    onAssetClick: (String) -> Unit = {},
    onDebugAddExampleAssets: () -> Unit = {},
    onDebugDeleteAllAssetsClick: () -> Unit = {},
    isDebug: Boolean = false,
) =
    Scaffold(
        modifier = Modifier.then(modifier),
        containerColor = Color.Transparent,
        topBar = {
            AssetsScreenTopBar(
                onCreateAssetClick = onCreateAssetClick,
                onSearchClick = onSearchClick,
            )
        },
    ) { paddingValues ->
        Crossfade(modifier = Modifier.padding(paddingValues), targetState = uiState) { uiState ->
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                when (uiState) {
                    AssetsScreenUiState.Loading -> AssetListLoadingState()
                    is AssetsScreenUiState.Success -> {
                        DebugAssetButton(
                            onButtonClick = onDebugAddExampleAssets,
                            title =
                                stringResource(
                                    id = R.string.feature_assets_overview_debug_add_assets
                                ),
                            isVisible = isDebug,
                        )
                        DebugAssetButton(
                            onButtonClick = onDebugDeleteAllAssetsClick,
                            title =
                                stringResource(
                                    id = R.string.feature_assets_overview_debug_delete_all_assets
                                ),
                            isVisible = isDebug,
                        )
                        AssetOverviewList(
                            assetList = uiState.assetList,
                            onAssetClick = onAssetClick,
                        )
                    }
                    AssetsScreenUiState.Empty -> {
                        DebugAssetButton(
                            onButtonClick = onDebugAddExampleAssets,
                            title =
                                stringResource(id = R.string.feature_assets_top_app_bar_add_asset),
                            isVisible = isDebug,
                        )
                        EmptyAssetOverviewList()
                    }
                }
            }
        }
    }

@Composable
fun DebugAssetButton(title: String, onButtonClick: () -> Unit, isVisible: Boolean) {
    if (isVisible) {
        Column(
            modifier = Modifier.padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            ListItem(
                modifier =
                    Modifier.fillMaxWidth()
                        .clip(shape = RoundedCornerShape(8.dp))
                        .clickable(enabled = true, onClick = onButtonClick),
                colors =
                    ListItemDefaults.colors(containerColor = MaterialTheme.colorScheme.surface),
                leadingContent = { Icon(imageVector = Cozy.icon.Code, contentDescription = title) },
                headlineContent = { Text(text = title) },
            )
        }
    }
}
