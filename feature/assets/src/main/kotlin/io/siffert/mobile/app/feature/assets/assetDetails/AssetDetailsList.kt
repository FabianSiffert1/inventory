package io.siffert.mobile.app.feature.assets.io.siffert.mobile.app.feature.assets.assetDetails

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import io.siffert.mobile.app.feature.assets.R
import io.siffert.mobile.app.inventory.core.designsystem.icons.TrendingUp
import io.siffert.mobile.app.inventory.core.designsystem.theme.Cozy
import io.siffert.mobile.app.model.data.Asset
import io.siffert.mobile.app.model.data.AssetClass

@Composable
internal fun AssetDetailsList(asset: Asset, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.verticalScroll(rememberScrollState()).fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        AssetDetailsListItem(
            title = stringResource(id = R.string.feature_assets_asset_details_name),
            supportingContent = {
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) { Text(text = asset.name) }
            },
        )
        AssetDetailsListItem(
            title = stringResource(id = R.string.feature_assets_asset_details_current_price),
            supportingContent = {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(text = "${asset.priceHistory.last().value} ${asset.currency.name}")
                    Text(text = "${asset.priceHistory.last().timestamp}")
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
        headlineContent = { Text(text = title, fontWeight = FontWeight.SemiBold) },
        supportingContent = supportingContent,
    )
}

// todo: add icons, implement
@Composable
private fun AssetClassIcon(modifier: Modifier = Modifier, assetClass: AssetClass) {
    Icon(
        modifier = modifier.padding(4.dp),
        imageVector =
            when (assetClass) {
                AssetClass.REAL_ASSET,
                AssetClass.SECURITY,
                AssetClass.DIGITAL_ASSET -> Cozy.icon.TrendingUp
            },
        contentDescription = "assetClassIcon",
    )
}
