package io.siffert.mobile.app.feature.assets

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.siffert.mobile.app.inventory.core.designsystem.icons.ShowChart
import io.siffert.mobile.app.inventory.core.designsystem.icons.TrendingDown
import io.siffert.mobile.app.inventory.core.designsystem.icons.TrendingFlat
import io.siffert.mobile.app.inventory.core.designsystem.icons.TrendingUp
import io.siffert.mobile.app.inventory.core.designsystem.theme.Cozy
import io.siffert.mobile.app.model.data.Asset
import io.siffert.mobile.app.model.data.Trend


@Composable
fun AssetListItem(
    asset: Asset,
    modifier: Modifier = Modifier,
) {
    ListItem(
        modifier = modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(8.dp)),
        colors = ListItemDefaults.colors(
            containerColor = Color.Transparent,
        ),
        leadingContent = {
            AssetIcon()
        },
        headlineContent = { Text(text = asset.name) },
        supportingContent = { Text(text = asset.info) },
        trailingContent = {
            TrendIcon(trend = asset.trend)
        }
    )
}

@Composable
private fun AssetIcon(modifier: Modifier = Modifier) {
    Icon(
        modifier = modifier
            .padding(4.dp),
        imageVector = Cozy.icon.ShowChart,
        contentDescription = "assetIcon",
    )
}

@Composable
private fun TrendIcon(
    trend: Trend,
    modifier: Modifier = Modifier,
    showTrendColors: Boolean = false,
) {
    Icon(
        modifier = modifier
            .padding(4.dp),
        imageVector = when (trend) {
            Trend.UP -> Cozy.icon.TrendingUp
            Trend.DOWN -> Cozy.icon.TrendingDown
            Trend.FLAT -> Cozy.icon.TrendingFlat
        },
        contentDescription = "trendIcon",
        tint = when (trend) {
            Trend.UP -> if (showTrendColors) {
                Color.Green
            } else {
                MaterialTheme.colorScheme.onSurface
            }

            Trend.DOWN -> if (showTrendColors) {
                Color.Red
            } else MaterialTheme.colorScheme.onSurface

            Trend.FLAT -> MaterialTheme.colorScheme.onSurface
        }
    )
}


@Composable
@Preview
fun AssetPreview() {
    AssetListItem(Asset(name = "assetName", info = "total pnl: +100%", trend = Trend.UP))
}

