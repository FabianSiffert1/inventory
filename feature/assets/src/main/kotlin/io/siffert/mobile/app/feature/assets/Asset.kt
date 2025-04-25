package io.siffert.mobile.app.feature.assets

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
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


@Composable
fun Asset(
    assetName: String,
    assetInfo: String,
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
        headlineContent = { Text(text = assetName) },
        supportingContent = { Text(text = assetInfo) }
    )
}

@Composable
private fun AssetIcon(modifier: Modifier = Modifier){
    Icon(
        modifier = modifier
            .padding(4.dp),
        imageVector = Icons.Filled.Build,
        contentDescription = "placeholderContentDescription",
    )
}


@Composable
@Preview
fun AssetPreview() {
    Asset(assetName = "assetName", assetInfo = "total pnl: +100%")
}

