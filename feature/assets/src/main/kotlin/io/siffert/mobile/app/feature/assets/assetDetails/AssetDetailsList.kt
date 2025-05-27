package io.siffert.mobile.app.feature.assets.io.siffert.mobile.app.feature.assets.assetDetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import io.siffert.mobile.app.feature.assets.R
import io.siffert.mobile.app.feature.assets.io.siffert.mobile.app.feature.assets.components.AssetClassIcon
import io.siffert.mobile.app.feature.assets.io.siffert.mobile.app.feature.assets.components.toFullDateString
import io.siffert.mobile.app.feature.assets.io.siffert.mobile.app.feature.assets.mapper.getDisplayName
import io.siffert.mobile.app.inventory.core.designsystem.theme.InventoryTheme
import io.siffert.mobile.app.model.data.Asset
import io.siffert.mobile.app.model.data.AssetClass

@Composable
internal fun AssetDetailsList(asset: Asset, modifier: Modifier = Modifier) {
    val localContext = LocalContext.current
    Column(
        modifier = modifier.verticalScroll(rememberScrollState()).padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        AssetDetailsListItem(
            title = stringResource(id = R.string.feature_assets_details_asset_name),
            supportingContent = { Text(text = asset.name) },
        )
        val lastPrice = asset.priceHistory.lastOrNull()?.value
        val boughtFor = asset.priceHistory.firstOrNull()?.value
        lastPrice?.let {
            AssetDetailsListItem(
                title = stringResource(id = R.string.feature_assets_details_current_value),
                supportingContent = {
                    AssetDetailsPriceAndDateRow(
                        price = lastPrice,
                        assetCurrency = asset.currency.name,
                        date = asset.priceHistory.lastOrNull()?.timestamp?.toFullDateString(),
                    )
                },
            )
        }

        boughtFor?.let {
            AssetDetailsListItem(
                title = stringResource(id = R.string.feature_assets_details_acquisition_price),
                supportingContent = {
                    // todo: onClick move to asset acquisition screen
                    Column {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                        ) {
                            Text(
                                text =
                                    "${asset.priceHistory.firstOrNull()?.value} ${asset.currency.name}"
                            )
                            asset.priceHistory.firstOrNull()?.timestamp?.toFullDateString()?.let {
                                Text(text = it)
                            }
                        }
                    }
                },
            )
        }

        asset.saleInfo?.let {
            ListItem(
                modifier =
                    Modifier.clip(shape = RoundedCornerShape(8.dp)).fillMaxWidth().then(modifier),
                headlineContent = {
                    Text(
                        text = stringResource(id = R.string.feature_assets_details_sold_for),
                        fontSize = MaterialTheme.typography.labelSmall.fontSize,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                },
                supportingContent = {
                    AssetDetailsPriceAndDateRow(
                        price = asset.saleInfo?.value,
                        assetCurrency = asset.currency.name,
                        date = asset.saleInfo?.timestamp?.toFullDateString(),
                    )
                    // todo: implement realized Gain info
                },
            )
        }

        asset.fees?.let {
            ListItem(
                modifier =
                    Modifier.clip(shape = RoundedCornerShape(8.dp)).fillMaxWidth().then(modifier),
                headlineContent = {
                    Text(
                        text = stringResource(id = R.string.feature_assets_details_fees),
                        fontSize = MaterialTheme.typography.labelSmall.fontSize,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                },
                supportingContent = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                    ) {
                        Text(text = "${asset.fees} ${asset.currency.name}")
                    }
                },
            )
        }

        asset.assetGroupId?.let {
            AssetDetailsListItem(
                title = stringResource(id = R.string.feature_assets_details_group),
                supportingContent = { Text(text = "${asset.assetGroupId}") },
            )
        }

        AssetDetailsListItem(
            title = stringResource(id = R.string.feature_assets_details_asset_class),
            supportingContent = {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    AssetClassIcon(assetClass = asset.assetClass)
                    Text(text = asset.assetClass.getDisplayName(localContext))
                }
            },
        )

        asset.url?.let {
            AssetDetailsListItem(
                title = stringResource(id = R.string.feature_assets_details_url),
                supportingContent = {
                    // todo: make clickable
                    Text(text = "${asset.url}")
                },
            )
        }

        asset.userNotes?.let {
            AssetDetailsListItem(
                title = stringResource(id = R.string.feature_assets_details_notes),
                supportingContent = { Text(text = "${asset.userNotes}") },
            )
        }
    }
}

@Composable
private fun AssetDetailsPriceAndDateRow(price: Double?, assetCurrency: String, date: String?) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(text = "$price $assetCurrency")
        if (date != null) {
            Text(text = date)
        }
    }
}

@Composable
private fun AssetDetailsListItem(
    title: String,
    supportingContent: @Composable () -> Unit,
    modifier: Modifier = Modifier,
) {
    ListItem(
        modifier = Modifier.clip(shape = RoundedCornerShape(8.dp)).fillMaxWidth().then(modifier),
        headlineContent = {
            Text(
                text = title,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                // todo: create cozy color scheme
                fontSize = MaterialTheme.typography.labelSmall.fontSize,
                color = MaterialTheme.colorScheme.onSurface,
            )
        },
        supportingContent = supportingContent,
    )
}

@Composable
@PreviewLightDark
private fun AssetDetailsListItemPreview() {
    val localContext = LocalContext.current
    InventoryTheme {
        Column(modifier = Modifier.padding(16.dp)) {
            AssetDetailsListItem(
                modifier = Modifier.background(color = MaterialTheme.colorScheme.surface),
                title = "Preview",
                supportingContent = {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        AssetClassIcon(assetClass = AssetClass.DIGITAL_ASSET)
                        Text(text = AssetClass.DIGITAL_ASSET.getDisplayName(localContext))
                    }
                },
            )
        }
    }
}
