package io.siffert.mobile.app.feature.assets.io.siffert.mobile.app.feature.assets.assetCreation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
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
import androidx.compose.ui.tooling.preview.PreviewLightDark
import io.siffert.mobile.app.feature.assets.R
import io.siffert.mobile.app.inventory.core.designsystem.icons.Save
import io.siffert.mobile.app.inventory.core.designsystem.theme.Cozy
import io.siffert.mobile.app.inventory.core.designsystem.theme.InventoryTheme

@Composable
internal fun AssetCreationTopBar(
    onBackClick: () -> Unit,
    onCreateAssetClick: () -> Unit,
    modifier: Modifier = Modifier,
    isCreateAssetButtonEnabled: Boolean,
) {
    CenterAlignedTopAppBar(
        modifier = modifier.testTag("assetCreationTopBar"),
        title = { Text(text = stringResource(id = R.string.feature_assets_creation_title)) },
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = stringResource(id = R.string.feature_assets_navigate_back),
                    tint = MaterialTheme.colorScheme.onSurface,
                )
            }
        },
        actions = {
            IconButton(onClick = onCreateAssetClick, enabled = isCreateAssetButtonEnabled) {
                Icon(
                    imageVector = Cozy.icon.Save,
                    contentDescription =
                        stringResource(id = R.string.feature_assets_creation_title),
                    tint =
                        if (isCreateAssetButtonEnabled) MaterialTheme.colorScheme.onSurface
                        else MaterialTheme.colorScheme.surfaceVariant,
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent),
    )
}

@PreviewLightDark
@Composable
private fun Preview() = InventoryTheme {
    Column(modifier = Modifier.background(color = MaterialTheme.colorScheme.background)) {
        AssetCreationTopBar(
            onBackClick = {},
            onCreateAssetClick = {},
            isCreateAssetButtonEnabled = true,
        )
    }
}
