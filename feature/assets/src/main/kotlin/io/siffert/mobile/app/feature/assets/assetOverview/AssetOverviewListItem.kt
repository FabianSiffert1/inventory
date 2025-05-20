package io.siffert.mobile.app.feature.assets.io.siffert.mobile.app.feature.assets.assetOverviewList

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.siffert.mobile.app.feature.assets.R
import io.siffert.mobile.app.feature.assets.io.siffert.mobile.app.feature.assets.components.AssetClassIcon
import io.siffert.mobile.app.feature.assets.io.siffert.mobile.app.feature.assets.components.TrendIcon
import io.siffert.mobile.app.inventory.core.designsystem.icons.Gavel
import io.siffert.mobile.app.inventory.core.designsystem.theme.Cozy
import io.siffert.mobile.app.model.data.Asset
import io.siffert.mobile.app.model.data.AssetClass
import io.siffert.mobile.app.model.data.Currency
import io.siffert.mobile.app.model.data.PriceHistoryEntry
import io.siffert.mobile.app.model.data.SaleEntry
import io.siffert.mobile.app.model.data.Trend
import kotlinx.datetime.Clock

@Composable
fun AssetOverviewListItem(
    asset: Asset,
    onAssetClick: (String) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    ListItem(
        modifier =
            modifier
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(8.dp))
                .clickable(enabled = true, onClick = { onAssetClick(asset.id) }),
        colors = ListItemDefaults.colors(containerColor = MaterialTheme.colorScheme.surface),
        leadingContent = { AssetClassIcon(assetClass = asset.assetClass) },
        headlineContent = { Text(text = asset.name) },
        supportingContent = {
            val lastPrice = asset.priceHistory.lastOrNull()?.value
            lastPrice?.let {
                Text(text = "${asset.priceHistory.lastOrNull()?.value} ${asset.currency.name}")
            }
        },
        trailingContent = {
            // todo implement that users can sell partials of their assets
            if (asset.saleInfo != null) {
                Icon(
                    imageVector = Cozy.icon.Gavel,
                    contentDescription =
                        stringResource(id = R.string.feature_assets_asset_details_sold_for),
                )
            } else {
                // todo implement
                TrendIcon(trend = Trend.FLAT)
            }
        },
    )
}

@Composable
@Preview
fun AssetOverviewListItemPreview() {
    AssetOverviewListItem(
        Asset(
            id = "uuid1",
            name = "asset1",
            assetGroupId = "groupId1",
            assetClass = AssetClass.REAL_ASSET,
            fees = 0.10,
            priceHistory =
                listOf(
                    PriceHistoryEntry(
                        assetId = "assetId",
                        value = 1.30,
                        timestamp = Clock.System.now(),
                        id = "uuid2",
                    )
                ),
            saleInfo =
                SaleEntry(
                    id = "uuid3",
                    assetId = "assetId",
                    value = 1.20,
                    timestamp = Clock.System.now(),
                ),
            currency = Currency.EUR,
            url = null,
            userNotes = "notes1",
        )
    )
}
