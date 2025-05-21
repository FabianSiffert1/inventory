package io.siffert.mobile.app.feature.assets.io.siffert.mobile.app.feature.assets.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ListItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import io.siffert.mobile.app.inventory.core.designsystem.util.loadingAnimationBackground

@Composable
fun AssetListLoadingState() {
    Column(
        modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        for (i in 0..3) {
            val alpha = 1f - (i / (i.toFloat() * 1.4f))
            ListItem(
                modifier =
                    Modifier.fillMaxWidth().clip(shape = RoundedCornerShape(8.dp)).alpha(alpha),
                leadingContent = {
                    Box(
                        modifier =
                            Modifier.size(24.dp)
                                .clip(shape = RoundedCornerShape(8.dp))
                                .loadingAnimationBackground()
                    )
                },
                headlineContent = {
                    Row(
                        modifier =
                            Modifier.fillMaxWidth()
                                .height(32.dp)
                                .clip(shape = RoundedCornerShape(8.dp))
                                .loadingAnimationBackground(),
                        content = {},
                    )
                },
                trailingContent = {
                    Box(
                        modifier =
                            Modifier.size(24.dp)
                                .clip(shape = RoundedCornerShape(8.dp))
                                .loadingAnimationBackground(),
                        content = {},
                    )
                },
            )
        }
    }
}
