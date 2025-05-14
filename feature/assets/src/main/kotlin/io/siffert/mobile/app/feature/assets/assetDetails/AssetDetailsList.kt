package io.siffert.mobile.app.feature.assets.io.siffert.mobile.app.feature.assets.assetDetails

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import io.siffert.mobile.app.feature.assets.R
import io.siffert.mobile.app.feature.assets.io.siffert.mobile.app.feature.assets.components.AssetClassIcon
import io.siffert.mobile.app.feature.assets.io.siffert.mobile.app.feature.assets.components.prettyPrint
import io.siffert.mobile.app.feature.assets.io.siffert.mobile.app.feature.assets.mapper.getDisplayName
import io.siffert.mobile.app.inventory.core.designsystem.theme.InventoryTheme
import io.siffert.mobile.app.model.data.Asset
import io.siffert.mobile.app.model.data.AssetClass

@Composable
internal fun AssetDetailsList(asset: Asset, modifier: Modifier = Modifier) {
    val localContext = LocalContext.current
    Column(
        modifier = modifier.verticalScroll(rememberScrollState()).fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        AssetDetailsListItem(
            title = stringResource(id = R.string.feature_assets_asset_details_current_value),
            supportingContent = {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(text = "${asset.priceHistory.last().value} ${asset.currency.name}")
                    Text(text = asset.priceHistory.last().timestamp.prettyPrint())
                }
            },
        )
        AssetDetailsListItem(
            title = stringResource(id = R.string.feature_assets_asset_details_acquisition_price),
            supportingContent = {
                // todo: onClick move to asset acquisition screen
                Column {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                    ) {
                        Text(text = "${asset.priceHistory.first().value} ${asset.currency.name}")
                        Text(text = asset.priceHistory.first().timestamp.prettyPrint())
                    }
                }
            },
        )
        asset.assetGroupId?.let {
            AssetDetailsListItem(
                title = stringResource(id = R.string.feature_assets_asset_details_group),
                supportingContent = {
                    Row(modifier = Modifier.fillMaxWidth()) { Text(text = "${asset.assetGroupId}") }
                },
            )
        }
        AssetDetailsListItem(
            title = stringResource(id = R.string.feature_assets_asset_details_asset_class),
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
        asset.userNotes?.let {
            AssetDetailsListItem(
                title = stringResource(id = R.string.feature_assets_asset_details_notes),
                supportingContent = {
                    Row(modifier = Modifier.fillMaxWidth()) { Text(text = "${asset.userNotes}") }
                },
            )
        }
        // todo: implement rest of asset details
        Text(text = "Placeholder: Price Graph?")
    }
}

@Composable
private fun AssetDetailsListItem(
    title: String,
    supportingContent: @Composable () -> Unit,
    modifier: Modifier = Modifier,
) {
    ListItem(
        modifier = Modifier.fillMaxWidth().then(modifier),
        headlineContent = {
            Text(
                text = title,
                // todo: replace with Cozy color that supports light and dark
                fontSize = MaterialTheme.typography.labelSmall.fontSize,
                color = Color.Gray,
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
        AssetDetailsListItem(
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
