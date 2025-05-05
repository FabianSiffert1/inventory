package io.siffert.mobile.app.feature.assets

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.fontResource
import androidx.compose.ui.unit.dp
import io.siffert.mobile.app.inventory.core.designsystem.component.PreviewGradientBackgroundWrapper
import io.siffert.mobile.app.inventory.core.designsystem.component.ThemePreviews

@Composable
fun AssetList(
    assetList: List<Asset>,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth(),
    ) {
        val scrollableState = rememberLazyListState()
        var i = 0
        LazyColumn(
            modifier = Modifier
                .navigationBarsPadding()
                .padding(horizontal = 24.dp)
                .testTag("assets:assetlist"),
            contentPadding = PaddingValues(vertical = 16.dp),
            state = scrollableState,
        ) {
            assetList.forEach { asset ->
                item {
                    AssetListItem(
                        asset =  asset
                    )
                }
            }
        }
    }
}


@ThemePreviews
@Composable
fun AssetsListPreview() = PreviewGradientBackgroundWrapper {
    val exampleAssetList = mutableListOf(
        Asset(
            name = "asset1", info = "currentValue and PnL", trend = Trend.UP
        ),
        Asset(
            name = "asset2", info = "currentValue and PnL", trend = Trend.FLAT
        ),
        Asset(
            name = "asset3", info = "currentValue and PnL", trend = Trend.DOWN
        )
    )
    AssetList(assetList =  exampleAssetList)
}
