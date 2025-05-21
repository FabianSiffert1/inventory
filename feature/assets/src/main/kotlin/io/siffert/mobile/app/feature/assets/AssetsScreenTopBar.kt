package io.siffert.mobile.app.feature.assets.io.siffert.mobile.app.feature.assets

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import io.siffert.mobile.app.feature.assets.R
import io.siffert.mobile.app.inventory.core.designsystem.icons.AddCircle
import io.siffert.mobile.app.inventory.core.designsystem.theme.Cozy
import io.siffert.mobile.app.inventory.core.designsystem.theme.InventoryTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AssetsScreenTopBar(
    modifier: Modifier = Modifier,
    onSearchClick: () -> Unit = {},
    onCreateAssetClick: () -> Unit = {},
) {
    CenterAlignedTopAppBar(
        modifier = Modifier.testTag("inventoryTopAppBar").then(modifier),
        title = {},
        navigationIcon = {
            IconButton(onClick = onSearchClick) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription =
                        stringResource(id = R.string.feature_assets_top_app_bar_search),
                    tint = MaterialTheme.colorScheme.onSurface,
                )
            }
        },
        actions = {
            IconButton(onClick = onCreateAssetClick) {
                Icon(
                    imageVector = Cozy.icon.AddCircle,
                    contentDescription =
                        stringResource(id = R.string.feature_assets_top_app_bar_add_asset),
                    tint = MaterialTheme.colorScheme.onSurface,
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent),
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview("Assets Screen Top App Bar")
@Composable
private fun Preview() {
    InventoryTheme { AssetsScreenTopBar() }
}
