package io.siffert.mobile.app.feature.assets.io.siffert.mobile.app.feature.assets.assetDetails

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.siffert.mobile.app.core.common.dialog.AppDialog
import io.siffert.mobile.app.core.common.dialog.handling.DialogManager
import io.siffert.mobile.app.feature.assets.io.siffert.mobile.app.feature.assets.components.AssetListLoadingState
import org.koin.compose.koinInject
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
internal fun AssetDetailsScreen(assetId: String? = null, navigateBack: () -> Unit) {
    val viewModel: AssetDetailsScreenViewModel = koinViewModel { parametersOf(assetId) }
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val dialogManager: DialogManager = koinInject()

    AssetDetailScreenContent(
        uiState = uiState,
        onBackClick = navigateBack,
        onDeleteAssetClick = {
            dialogManager.enqueue(
                AppDialog.DeleteAssetDialog(
                    onConfirm = {
                        viewModel.deleteAsset(assetId = assetId, onAssetDeleted = navigateBack)
                    }
                )
            )
        },
        onEditClick = { dialogManager.enqueue(AppDialog.InformationDialog) },
    )
}

@Composable
private fun AssetDetailScreenContent(
    uiState: AssetDetailsScreenUiState,
    onBackClick: () -> Unit,
    onDeleteAssetClick: () -> Unit,
    onEditClick: () -> Unit,
) {
    val loadedUiState = uiState as? AssetDetailsScreenUiState.Loaded
    Scaffold(
        modifier = Modifier,
        containerColor = Color.Transparent,
        topBar = {
            AssetDetailsTopBar(
                assetName = loadedUiState?.asset?.name,
                assetSaleInfo = loadedUiState?.asset?.saleInfo,
                assetDeletionState = loadedUiState?.deleteAssetState,
                onBackClick = onBackClick,
                onDeleteAssetClick = onDeleteAssetClick,
                onEditClick = onEditClick,
            )
        },
    ) { paddingValues ->
        Crossfade(
            modifier = Modifier.statusBarsPadding().padding(paddingValues),
            targetState = uiState,
        ) {
            when (it) {
                AssetDetailsScreenUiState.Empty,
                AssetDetailsScreenUiState.Error -> ErrorAssetDetailsListItem()
                AssetDetailsScreenUiState.Loading -> AssetListLoadingState()
                is AssetDetailsScreenUiState.Loaded -> AssetDetailsList(asset = it.asset)
            }
        }
    }
}
