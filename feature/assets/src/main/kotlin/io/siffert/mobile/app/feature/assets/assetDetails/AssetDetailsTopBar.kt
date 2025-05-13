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
import io.siffert.mobile.app.inventory.core.designsystem.icons.Delete
import io.siffert.mobile.app.inventory.core.designsystem.theme.Cozy

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun AssetDetailsTopBar(
    onBackClick: () -> Unit,
    onDeleteAssetClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    CenterAlignedTopAppBar(
        title = {
            // todo: set to Asset Name
            // if (titleRes != null) Text(text = stringResource(id = titleRes)) },
            Text(text = "Asset Details")
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
                    tint = MaterialTheme.colorScheme.onSurface,
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent),
        modifier = modifier.testTag("assetDetailsTopBar"),
    )
}
