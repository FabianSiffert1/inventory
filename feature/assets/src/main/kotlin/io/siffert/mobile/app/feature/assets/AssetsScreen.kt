package io.siffert.mobile.app.feature.assets

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import io.siffert.mobile.app.inventory.core.designsystem.component.PreviewGradientBackgroundWrapper
import io.siffert.mobile.app.inventory.core.designsystem.component.ThemePreviews

@Composable
internal fun AssetsScreen(modifier: Modifier = Modifier) {
    Assets(modifier = modifier)
}

@Composable
internal fun Assets(modifier: Modifier = Modifier) =
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        AssetList()
    }


@ThemePreviews
@Composable
fun AssetsScreenPreview() =
    PreviewGradientBackgroundWrapper { AssetList() }