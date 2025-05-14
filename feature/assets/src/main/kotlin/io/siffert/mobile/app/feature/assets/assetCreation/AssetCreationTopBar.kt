package io.siffert.mobile.app.feature.assets.io.siffert.mobile.app.feature.assets.assetCreation

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
import io.siffert.mobile.app.inventory.core.designsystem.icons.Save
import io.siffert.mobile.app.inventory.core.designsystem.theme.Cozy

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun AssetCreationTopBar(
    onBackClick: () -> Unit,
    onCreateAssetClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    CenterAlignedTopAppBar(
        title = { Text(text = stringResource(id = R.string.feature_assets_asset_creation_title)) },
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
            IconButton(onClick = onCreateAssetClick) {
                Icon(
                    imageVector = Cozy.icon.Save,
                    contentDescription =
                        stringResource(id = R.string.feature_assets_asset_creation_title),
                    // todo: implement cozy.color.redMuted
                    tint = Color.Red,
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent),
        modifier = modifier.testTag("assetDetailsTopBar"),
    )
}
