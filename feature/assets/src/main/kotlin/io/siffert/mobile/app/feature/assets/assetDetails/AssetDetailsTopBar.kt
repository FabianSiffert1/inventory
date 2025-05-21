package io.siffert.mobile.app.feature.assets.io.siffert.mobile.app.feature.assets.assetDetails

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import io.siffert.mobile.app.feature.assets.R
import io.siffert.mobile.app.inventory.core.designsystem.icons.Delete
import io.siffert.mobile.app.inventory.core.designsystem.icons.Edit
import io.siffert.mobile.app.inventory.core.designsystem.icons.Gavel
import io.siffert.mobile.app.inventory.core.designsystem.theme.Cozy
import io.siffert.mobile.app.model.data.SaleEntry

@Composable
internal fun AssetDetailsTopBar(
    onBackClick: () -> Unit,
    onDeleteAssetClick: () -> Unit,
    onEditClick: () -> Unit,
    modifier: Modifier = Modifier,
    assetName: String?,
    assetSaleInfo: SaleEntry?,
    assetDeletionState: DeleteAssetUiState?,
) {
    CenterAlignedTopAppBar(
        title = {
            if (assetName != null) {
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text(text = assetName)
                    if (assetSaleInfo != null)
                        Icon(
                            imageVector = Cozy.icon.Gavel,
                            contentDescription =
                                stringResource(id = R.string.feature_assets_details_sold_for),
                        )
                }
            } else {
                Text(text = stringResource(id = R.string.feature_assets_details_asset_name))
            }
        },
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Navigate to previous Screen",
                    tint = MaterialTheme.colorScheme.onSurface,
                )
            }
        },
        actions = {
            if (assetDeletionState == DeleteAssetUiState.InProgress) {
                CircularProgressIndicator(
                    modifier = Modifier.size(40.dp),
                    color = MaterialTheme.colorScheme.onSurface,
                )
                Spacer(modifier = Modifier.width(16.dp))
            } else {
                IconButton(onClick = onDeleteAssetClick) {
                    Icon(
                        imageVector = Cozy.icon.Delete,
                        contentDescription =
                            stringResource(id = R.string.feature_assets_top_app_bar_delete_asset),
                    )
                }
                IconButton(onClick = onEditClick) {
                    Icon(
                        imageVector = Cozy.icon.Edit,
                        contentDescription =
                            stringResource(id = R.string.feature_assets_top_app_bar_edit_asset),
                        tint = MaterialTheme.colorScheme.onSurface,
                    )
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent),
        modifier = modifier.testTag("assetDetailsTopBar"),
    )
}
