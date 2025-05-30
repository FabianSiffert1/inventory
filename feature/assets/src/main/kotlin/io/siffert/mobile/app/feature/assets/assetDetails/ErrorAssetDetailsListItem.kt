package io.siffert.mobile.app.feature.assets.io.siffert.mobile.app.feature.assets.assetDetails

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import io.siffert.mobile.app.feature.assets.R
import io.siffert.mobile.app.inventory.core.designsystem.icons.Error
import io.siffert.mobile.app.inventory.core.designsystem.theme.Cozy
import io.siffert.mobile.app.inventory.core.designsystem.theme.InventoryTheme

@Composable
fun ErrorAssetDetailsListItem() {
    Column(
        modifier = Modifier.navigationBarsPadding().padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        ListItem(
            modifier = Modifier.fillMaxWidth().clip(shape = RoundedCornerShape(8.dp)),
            colors = ListItemDefaults.colors(containerColor = MaterialTheme.colorScheme.surface),
            leadingContent = {
                Icon(
                    imageVector = Cozy.icon.Error,
                    contentDescription =
                        stringResource(id = R.string.feature_asset_details_error_title),
                )
            },
            headlineContent = {
                Text(text = stringResource(id = R.string.feature_asset_details_error_title))
            },
            supportingContent = {
                Text(text = stringResource(id = R.string.feature_asset_details_error_subtitle))
            },
        )
    }
}

@Composable
@PreviewLightDark
internal fun ErrorAssetDetailsListItemPreview() =
    InventoryTheme { ErrorAssetDetailsListItem() }

