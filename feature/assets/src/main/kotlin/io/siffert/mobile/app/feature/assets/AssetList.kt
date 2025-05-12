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
import androidx.compose.ui.unit.dp
import io.siffert.mobile.app.inventory.core.designsystem.component.PreviewGradientBackgroundWrapper
import io.siffert.mobile.app.inventory.core.designsystem.component.ThemePreviews
import io.siffert.mobile.app.model.data.Asset
import io.siffert.mobile.app.model.data.AssetClass
import io.siffert.mobile.app.model.data.Currency
import io.siffert.mobile.app.model.data.PriceHistoryDate
import java.util.Date

@Composable
fun AssetList(
    assetList: List<Asset>,
    onAssetClick: (String) -> Unit = {},
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth(),
    ) {
        val scrollableState = rememberLazyListState()
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
                        asset = asset,
                        onAssetClick = onAssetClick,
                    )
                }
            }
        }
    }
}


@ThemePreviews
@Composable
fun AssetListPreview() = PreviewGradientBackgroundWrapper {
    val exampleAssetList = mutableListOf(
        Asset(
            id = "uuid1",
            name = "asset1",
            assetGroupId = "groupId1",
            assetClass = AssetClass.REAL_ASSET,
            acquisitionPrice = 1.00,
            acquisitionDate = Date(),
            fees = 0.10,
            priceHistory = listOf(
                PriceHistoryDate(
                    id = 1,
                    assetId = "assetId",
                    value = 1.20,
                    timestamp = Date()
                )
            ),
            sellPrice = null,
            sellDate = null,
            realizedGain = null,
            currency = Currency.EUR,
            url = null,
            userNotes = "currentValue and PnL",
        ),
        Asset(
            id = "uuid2",
            name = "asset2",
            assetGroupId = "groupId2",
            assetClass = AssetClass.REAL_ASSET,
            acquisitionPrice = 1.00,
            acquisitionDate = Date(),
            fees = 0.10,
            priceHistory = listOf(
                PriceHistoryDate(
                    id = 2,
                    assetId = "assetId",
                    value = 1.30,
                    timestamp = Date()
                )
            ),
            sellPrice = null,
            sellDate = null,
            realizedGain = null,
            currency = Currency.EUR,
            url = null,
            userNotes = "currentValue and PnL",
        ),
        Asset(
            id = "uuid1",
            name = "asset1",
            assetGroupId = "groupId3",
            assetClass = AssetClass.REAL_ASSET,
            acquisitionPrice = 1.00,
            acquisitionDate = Date(),
            fees = 0.10,
            priceHistory = listOf(
                PriceHistoryDate(
                    id = 3,
                    assetId = "assetId",
                    value = 1.40,
                    timestamp = Date()
                )
            ),
            sellPrice = null,
            sellDate = null,
            realizedGain = null,
            currency = Currency.EUR,
            url = null,
            userNotes = "currentValue and PnL",
        ),
    )
    AssetList(assetList = exampleAssetList)
}
