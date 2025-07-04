package io.siffert.mobile.app.feature.assets.io.siffert.mobile.app.feature.assets.assetEditor

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation3.runtime.NavKey
import io.siffert.mobile.app.core.common.dialog.AppDialog
import io.siffert.mobile.app.core.common.dialog.handling.DialogManager
import io.siffert.mobile.app.core.common.flow.LoadingState
import io.siffert.mobile.app.core.common.flow.LoadingState.Present
import io.siffert.mobile.app.feature.assets.io.siffert.mobile.app.feature.assets.components.AssetClassWithStringRes
import io.siffert.mobile.app.feature.assets.io.siffert.mobile.app.feature.assets.components.AssetListLoadingState
import io.siffert.mobile.app.model.data.Asset
import io.siffert.mobile.app.model.data.Currency
import kotlinx.serialization.Serializable
import org.koin.compose.koinInject
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

@Serializable
data class AssetEditorRoute(val assetId: String? = null, val assetEditorMode: AssetEditorMode) :
    NavKey
@Composable
internal fun AssetEditorScreen(
    assetId: String?,
    assetEditorMode: AssetEditorMode,
    navigateBack: () -> Unit,
) {
    val viewModel: AssetEditorScreenViewModel = koinViewModel { parametersOf(assetId) }
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val dialogManager: DialogManager = koinInject()

    var isCurrencyBottomSheetVisible by remember { mutableStateOf(false) }
    var isAssetClassBottomSheetVisible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        viewModel.uiCommands.collect { command ->
            when (command) {
                AssetEditorScreenUiCommand.NavigateBack -> navigateBack()
                AssetEditorScreenUiCommand.ShowAssetClassBottomSheet -> {
                    isAssetClassBottomSheetVisible = !isAssetClassBottomSheetVisible
                }
                AssetEditorScreenUiCommand.ShowCurrencyBottomSheet -> {
                    isCurrencyBottomSheetVisible = !isCurrencyBottomSheetVisible
                }

                AssetEditorScreenUiCommand.ShowCurrentPriceEditEntryDialog ->
                    dialogManager.enqueue(
                        AppDialog.EditPriceDialog(
                            when (uiState.assetToEditState) {
                                is Present<Asset> -> {
                                    (uiState.assetToEditState as Present<Asset>)
                                        .value
                                        .priceHistory
                                        .last()
                                }
                                else -> null
                            // todo: handle LoadingState.NotPresent -> Error!
                            }
                        )
                    )
            }
        }
    }

    AssetEditorScreenContent(
        uiState = uiState,
        assetEditorMode = assetEditorMode,
        assetLoadingState = uiState.assetToEditState,
        onBackClick = navigateBack,
        onNameChange = viewModel::onNameChange,
        onFeesChange = viewModel::onFeesChange,
        onNotesChange = viewModel::onNotesChange,
        onAssetClassChange = viewModel::onAssetClassChange,
        onUrlChange = viewModel::onUrlChange,
        onPriceChange = viewModel::onPriceChange,
        onCurrencyChange = viewModel::onCurrencyChange,
        isCurrencyBottomSheetVisible = isCurrencyBottomSheetVisible,
        onToggleCurrencyBottomSheet = viewModel::showCurrencyBottomSheet,
        isAssetClassBottomSheetVisible = isAssetClassBottomSheetVisible,
        onToggleAssetClassBottomSheet = viewModel::showAssetClassBottomSheet,
        onCreateOrUpdateAssetClick = {
            viewModel.createOrUpdateAsset(assetEditorMode = assetEditorMode)
        },
        isCreateAssetButtonEnabled =
            uiState.isValidAsset && uiState.assetProcessingState != AssetProcessingState.Loading,
        onClickPriceEditButton = viewModel::showEditPriceEntryDialog,
    )
}

@Composable
private fun AssetEditorScreenContent(
    uiState: AssetEditorScreenUiState,
    assetEditorMode: AssetEditorMode,
    onBackClick: () -> Unit,
    onCreateOrUpdateAssetClick: () -> Unit,
    onNotesChange: (String) -> Unit,
    onPriceChange: (String) -> Unit,
    onFeesChange: (String) -> Unit,
    onNameChange: (String) -> Unit,
    onUrlChange: (String) -> Unit,
    isCurrencyBottomSheetVisible: Boolean,
    isAssetClassBottomSheetVisible: Boolean,
    onCurrencyChange: (Currency) -> Unit,
    onAssetClassChange: (AssetClassWithStringRes) -> Unit,
    onToggleCurrencyBottomSheet: () -> Unit,
    onToggleAssetClassBottomSheet: () -> Unit,
    assetLoadingState: LoadingState<Asset>?,
    isCreateAssetButtonEnabled: Boolean,
    onClickPriceEditButton: () -> Unit,
) {

    Scaffold(
        modifier = Modifier.safeDrawingPadding().imePadding(),
        containerColor = Color.Transparent,
        topBar = {
            AssetEditorTopBar(
                onBackClick = onBackClick,
                onCreateOrUpdateAssetClick = onCreateOrUpdateAssetClick,
                isCreateAssetButtonEnabled = isCreateAssetButtonEnabled,
                assetEditorMode = assetEditorMode,
            )
        },
    ) { paddingValues ->
        Column(
            modifier =
                Modifier.padding(horizontal = 16.dp)
                    .padding(paddingValues)
                    .verticalScroll(rememberScrollState())
                    .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            TextButton(onClick = onClickPriceEditButton) { Text("Debug Edit Price Dialog Button") }
            when (assetEditorMode) {
                AssetEditorMode.CREATE ->
                    AssetEditorInputFields(
                        uiState = uiState,
                        onNameChange = onNameChange,
                        onPriceChange = onPriceChange,
                        onCurrencyChange = onCurrencyChange,
                        onFeesChange = onFeesChange,
                        onAssetClassChange = onAssetClassChange,
                        onUrlChange = onUrlChange,
                        onNotesChange = onNotesChange,
                        isCurrencyBottomSheetVisible = isCurrencyBottomSheetVisible,
                        isAssetClassBottomSheetVisible = isAssetClassBottomSheetVisible,
                        toggleCurrencyBottomSheet = onToggleCurrencyBottomSheet,
                        toggleAssetClassBottomSheet = onToggleAssetClassBottomSheet,
                    )
                AssetEditorMode.EDIT ->
                    when (assetLoadingState) {
                        LoadingState.Loading -> AssetListLoadingState()
                        LoadingState.NotPresent -> AssetEditorErrorState()
                        is Present<Asset>,
                        null ->
                            AssetEditorInputFields(
                                uiState = uiState,
                                onNameChange = onNameChange,
                                onPriceChange = onPriceChange,
                                isCurrencyBottomSheetVisible = isCurrencyBottomSheetVisible,
                                onCurrencyChange = onCurrencyChange,
                                isAssetClassBottomSheetVisible = isAssetClassBottomSheetVisible,
                                onAssetClassChange = onAssetClassChange,
                                onFeesChange = onFeesChange,
                                onUrlChange = onUrlChange,
                                onNotesChange = onNotesChange,
                                toggleCurrencyBottomSheet = onToggleCurrencyBottomSheet,
                                toggleAssetClassBottomSheet = onToggleAssetClassBottomSheet,
                            )
                    }
            }
        }
    }
}
