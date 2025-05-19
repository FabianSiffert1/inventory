package io.siffert.mobile.app.feature.assets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import io.siffert.mobile.app.feature.assets.io.siffert.mobile.app.feature.assets.assetOverview.AssetOverviewListEmpty
import io.siffert.mobile.app.feature.assets.io.siffert.mobile.app.feature.assets.assetOverview.AssetOverviewListLoading
import io.siffert.mobile.app.feature.assets.io.siffert.mobile.app.feature.assets.assetOverviewList.AssetOverviewList
import io.siffert.mobile.app.inventory.core.designsystem.icons.Save
import io.siffert.mobile.app.inventory.core.designsystem.theme.Cozy
import org.koin.androidx.compose.koinViewModel

@Composable
internal fun AssetsScreen(onAssetClick: (String) -> Unit, modifier: Modifier = Modifier) {
    val viewModel: AssetsScreenViewModel = koinViewModel()
    val uiState: AssetsScreenUiState by viewModel.uiState.collectAsStateWithLifecycle()

    Assets(
        uiState = uiState,
        onAssetClick = onAssetClick,
        onAddDebugAssetsClick = viewModel::addDebugAssets,
        modifier = modifier,
    )
}

@Composable
internal fun Assets(
    uiState: AssetsScreenUiState,
    modifier: Modifier = Modifier,
    onAssetClick: (String) -> Unit = {},
    onAddDebugAssetsClick: () -> Unit = {},
) =
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        DebugAssetButton(onAddDebugAssets = onAddDebugAssetsClick)
        when (uiState) {
            AssetsScreenUiState.Loading -> AssetOverviewListLoading()
            is AssetsScreenUiState.Success ->
                AssetOverviewList(assetList = uiState.assetList, onAssetClick = onAssetClick)
            AssetsScreenUiState.Empty -> AssetOverviewListEmpty()
        }
    }

@Composable
fun DebugAssetButton(onAddDebugAssets: () -> Unit) {
    // todo: show only in debug mode
    Column(
        modifier = Modifier.navigationBarsPadding().padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        ListItem(
            modifier =
                Modifier.fillMaxWidth()
                    .clip(shape = RoundedCornerShape(8.dp))
                    .clickable(enabled = true, onClick = onAddDebugAssets),
            colors = ListItemDefaults.colors(containerColor = MaterialTheme.colorScheme.surface),
            leadingContent = {
                Icon(
                    imageVector = Cozy.icon.Save,
                    contentDescription =
                        stringResource(id = R.string.feature_assets_overview_empty_list_title) +
                            " ${stringResource(id = R.string.feature_assets_overview_empty_list_subtitle)}",
                )
            },
            headlineContent = { Text(text = "Add Debug Assets") },
        )
        Spacer(modifier = Modifier.height(16.dp))
    }
}
