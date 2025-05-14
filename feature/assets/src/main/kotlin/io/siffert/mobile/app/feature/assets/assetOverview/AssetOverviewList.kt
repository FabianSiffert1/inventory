package io.siffert.mobile.app.feature.assets.io.siffert.mobile.app.feature.assets.assetOverviewList

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import io.siffert.mobile.app.feature.assets.io.siffert.mobile.app.feature.assets.components.exampleAssetList
import io.siffert.mobile.app.inventory.core.designsystem.component.PreviewGradientBackgroundWrapper
import io.siffert.mobile.app.inventory.core.designsystem.component.ThemePreviews
import io.siffert.mobile.app.model.data.Asset

@Composable
fun AssetOverviewList(
    assetList: List<Asset>?,
    onAssetClick: (String) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier.fillMaxWidth()) {
        val scrollableState = rememberLazyListState()
        LazyColumn(
            modifier =
                Modifier.navigationBarsPadding()
                    .padding(horizontal = 16.dp)
                    .testTag("assets:assetlist"),
            state = scrollableState,
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            assetList?.forEach { asset ->
                item { AssetOverviewListItem(asset = asset, onAssetClick = onAssetClick) }
            }
        }
    }
}

@ThemePreviews
@Composable
fun AssetOverviewListPreview() = PreviewGradientBackgroundWrapper {
    AssetOverviewList(assetList = exampleAssetList)
}
