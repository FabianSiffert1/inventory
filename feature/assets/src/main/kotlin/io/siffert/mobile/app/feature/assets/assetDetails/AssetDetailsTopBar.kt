package io.siffert.mobile.app.feature.assets.io.siffert.mobile.app.feature.assets.assetDetails

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
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
import io.siffert.mobile.app.feature.assets.R
import io.siffert.mobile.app.inventory.core.designsystem.icons.Delete
import io.siffert.mobile.app.inventory.core.designsystem.icons.Edit
import io.siffert.mobile.app.inventory.core.designsystem.theme.Cozy

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun AssetDetailsTopBar(
    title: String?,
    onBackClick: () -> Unit,
    onDeleteAssetClick: () -> Unit,
    onEditClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text =
                    title
                        ?: stringResource(
                            id = R.string.feature_assets_top_app_bar_asset_details_title
                        )
            )
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
            IconButton(onClick = onDeleteAssetClick) {
                Icon(
                    imageVector = Cozy.icon.Delete,
                    contentDescription = "Delete Asset",
                    // todo: implement cozy.color.redMuted
                    tint = Color.Red,
                )
            }
            IconButton(onClick = onEditClick) {
                Icon(
                    imageVector = Cozy.icon.Edit,
                    contentDescription = "Edit Asset Information",
                    tint = MaterialTheme.colorScheme.onSurface,
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent),
        modifier = modifier.testTag("assetDetailsTopBar"),
    )
}
