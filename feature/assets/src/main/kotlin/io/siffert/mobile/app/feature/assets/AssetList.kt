package io.siffert.mobile.app.feature.assets

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import io.siffert.mobile.app.inventory.core.designsystem.component.PreviewGradientBackgroundWrapper
import io.siffert.mobile.app.inventory.core.designsystem.component.ThemePreviews

@Composable
fun AssetList(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth(),
    ) {
        val scrollableState = rememberLazyListState()
        var i = 0
        LazyColumn(
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .testTag("assets:assetlist"),
            contentPadding = PaddingValues(vertical = 16.dp),
            state = scrollableState,
        ) {
            while ( i < 10 ){
            item {
                Asset(
                    assetName = "asset1", assetInfo = "currentValue and PnL", trend = Trend.UP
                )
            }
                i++
            }
            item {
                Asset(
                    assetName = "asset2", assetInfo = "currentValue and PnL", trend = Trend.FLAT
                )
            }
            item {
                Asset(
                    assetName = "asset3", assetInfo = "currentValue and PnL", trend = Trend.DOWN
                )
            }
        }
    }
}


@ThemePreviews
@Composable
fun AssetsListPreview() = PreviewGradientBackgroundWrapper {
    AssetList()
}
