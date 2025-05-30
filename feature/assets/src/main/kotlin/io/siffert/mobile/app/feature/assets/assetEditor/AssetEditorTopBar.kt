package io.siffert.mobile.app.feature.assets.io.siffert.mobile.app.feature.assets.assetEditor

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
internal fun AssetEditorTopBar(
    onBackClick: () -> Unit,
    onCreateOrUpdateAssetClick: () -> Unit,
    assetEditorMode: AssetEditorMode,
    modifier: Modifier = Modifier,
    isCreateAssetButtonEnabled: Boolean,
) {
    val title =
        when (assetEditorMode) {
            AssetEditorMode.CREATE -> R.string.feature_assets_editor_create_title
            AssetEditorMode.EDIT -> R.string.feature_assets_editor_edit_title
        }
    CenterAlignedTopAppBar(
        modifier = modifier.testTag("assetEditorTopBar"),
        title = { Text(text = stringResource(id = title)) },
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
            IconButton(onClick = onCreateOrUpdateAssetClick, enabled = isCreateAssetButtonEnabled) {
                Icon(
                    imageVector = Cozy.icon.Save,
                    contentDescription = stringResource(id = title),
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
        AssetEditorTopBar(
            onBackClick = {},
            onCreateOrUpdateAssetClick = {},
            isCreateAssetButtonEnabled = true,
            assetEditorMode = AssetEditorMode.CREATE,
        )
    }
}
