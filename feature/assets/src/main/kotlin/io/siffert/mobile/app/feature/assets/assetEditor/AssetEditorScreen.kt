package io.siffert.mobile.app.feature.assets.io.siffert.mobile.app.feature.assets.assetEditor

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.siffert.mobile.app.core.common.dialog.handling.DialogManager
import io.siffert.mobile.app.core.common.flow.LoadingState
import io.siffert.mobile.app.feature.assets.io.siffert.mobile.app.feature.assets.assetOverview.EmptyAssetOverviewList
import io.siffert.mobile.app.feature.assets.io.siffert.mobile.app.feature.assets.components.AssetListLoadingState
import io.siffert.mobile.app.feature.assets.io.siffert.mobile.app.feature.assets.components.exampleAsset
import io.siffert.mobile.app.inventory.core.designsystem.theme.InventoryTheme
import io.siffert.mobile.app.model.data.Asset
import org.koin.compose.koinInject
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
internal fun AssetEditorScreen(assetId: String? = null, navigateBack: () -> Unit) {
    val viewModel: AssetEditorScreenViewModel = koinViewModel { parametersOf(assetId) }
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val dialogManager: DialogManager = koinInject()

    AssetEditorScreenContent(
        asset = uiState.asset,
        navigateBack = navigateBack,
        onSaveChangesClick = viewModel::saveAsset,
    )
}

@Composable
private fun AssetEditorScreenContent(
    asset: LoadingState<Asset>,
    navigateBack: () -> Unit,
    onSaveChangesClick: () -> Unit,
) {
    val loadedAssetState = asset as? LoadingState.Present
    Scaffold(
        modifier = Modifier,
        containerColor = Color.Transparent,
        topBar = {
            AssetEditorTopBar(
                assetName = loadedAssetState?.value?.name,
                assetSaleInfo = loadedAssetState?.value?.saleInfo,
                onBackClick = navigateBack,
                onSaveChangesClick = onSaveChangesClick,
                // todo implement
                isSaveAssetChangesButtonEnabled = true,
            )
        },
    ) { paddingValues ->
        Crossfade(modifier = Modifier.padding(paddingValues), targetState = asset) {
            when (it) {
                LoadingState.Loading -> AssetListLoadingState()
                LoadingState.NotPresent -> EmptyAssetOverviewList()
                is LoadingState.Present<Asset> -> {
                    Column {
                        for ((key, value) in it.value) {
                            Text(text = "$key $value")
                        }
                    }
                }
            }
        }
    }
}

@Composable
@PreviewLightDark
private fun Preview() = InventoryTheme {
    AssetEditorScreenContent(
        asset = LoadingState.Present(exampleAsset),
        navigateBack = {},
        onSaveChangesClick = {},
    )
}
