package io.siffert.mobile.app.feature.assets

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.traceEventStart
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.siffert.mobile.app.inventory.core.designsystem.component.PreviewGradientBackgroundWrapper
import io.siffert.mobile.app.inventory.core.designsystem.component.ThemePreviews
import org.koin.androidx.compose.koinViewModel


@Composable
internal fun AssetsScreen(modifier: Modifier = Modifier) {
    val viewModel: AssetsScreenViewModel = koinViewModel()
    val uiState: AssetsScreenUiState by viewModel.uiState.collectAsStateWithLifecycle()

    Assets(
       uiState = uiState,
        modifier = modifier)
}

@Composable
internal fun Assets(
    uiState: AssetsScreenUiState,
    modifier: Modifier = Modifier
) =
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        when (uiState) {
            AssetsScreenUiState.Loading -> Text("LoadingStateImplPlaceholder")
            is AssetsScreenUiState.Success -> AssetList(assetList = uiState.assetList)
        }
    }



@ThemePreviews
@Composable
fun AssetsScreenPreview() =
    PreviewGradientBackgroundWrapper {
        val exampleAssetList = mutableListOf(
            Asset(
                name = "asset1", info = "currentValue and PnL", trend = Trend.UP
            ),
            Asset(
                name = "asset2", info = "currentValue and PnL", trend = Trend.FLAT
            ),
            Asset(
                name = "asset3", info = "currentValue and PnL", trend = Trend.DOWN
            )
        )
        Assets(
            uiState = AssetsScreenUiState.Success(
                assetList = exampleAssetList
            )
        )

    }