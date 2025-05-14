package io.siffert.mobile.app.feature.assets.io.siffert.mobile.app.feature.assets.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import io.siffert.mobile.app.inventory.core.designsystem.icons.TrendingDown
import io.siffert.mobile.app.inventory.core.designsystem.icons.TrendingFlat
import io.siffert.mobile.app.inventory.core.designsystem.icons.TrendingUp
import io.siffert.mobile.app.inventory.core.designsystem.theme.Cozy
import io.siffert.mobile.app.model.data.Trend

@Composable
internal fun TrendIcon(
    trend: Trend,
    modifier: Modifier = Modifier,
    showTrendColors: Boolean = false,
) {
    Icon(
        modifier = modifier.padding(4.dp),
        imageVector =
            when (trend) {
                Trend.UP -> Cozy.icon.TrendingUp
                Trend.DOWN -> Cozy.icon.TrendingDown
                Trend.FLAT -> Cozy.icon.TrendingFlat
            },
        contentDescription = "trendIcon",
        tint =
            when (trend) {
                Trend.UP ->
                    if (showTrendColors) {
                        Color.Green
                    } else {
                        MaterialTheme.colorScheme.onSurface
                    }

                Trend.DOWN ->
                    if (showTrendColors) {
                        Color.Red
                    } else MaterialTheme.colorScheme.onSurface

                Trend.FLAT -> MaterialTheme.colorScheme.onSurface
            },
    )
}
