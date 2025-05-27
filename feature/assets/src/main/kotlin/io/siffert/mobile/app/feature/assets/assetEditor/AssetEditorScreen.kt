package io.siffert.mobile.app.feature.assets.io.siffert.mobile.app.feature.assets.assetEditor

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
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
import io.siffert.mobile.app.core.common.flow.LoadingState
import io.siffert.mobile.app.feature.assets.io.siffert.mobile.app.feature.assets.components.AssetClassWithStringRes
import io.siffert.mobile.app.feature.assets.io.siffert.mobile.app.feature.assets.components.AssetListLoadingState
import io.siffert.mobile.app.model.data.Asset
import io.siffert.mobile.app.model.data.Currency
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
internal fun AssetEditorScreen(
    assetId: String?,
    assetEditorMode: AssetEditorMode,
    navigateBack: () -> Unit,
) {
    val viewModel: AssetEditorScreenViewModel = koinViewModel { parametersOf(assetId) }

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val assetLoadingState = uiState.assetToEditState

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
            }
        }
    }

    AssetEditorScreenContent(
        uiState = uiState,
        assetEditorMode = assetEditorMode,
        assetLoadingState = assetLoadingState,
        onBackClick = navigateBack,
        onNameChange = viewModel::onNameChange,
        onFeesChange = viewModel::onFeesChange,
        onNotesChange = viewModel::onNotesChange,
        onAssetClassChange = viewModel::onAssetClassChange,
        onUrlChange = viewModel::onUrlChange,
        onPriceChange = viewModel::onPriceChange,
        onCurrencyChange = viewModel::onCurrencyChange,
        isCurrencyBottomSheetVisible = isCurrencyBottomSheetVisible,
        onShowCurrencyBottomSheet = viewModel::showCurrencyBottomSheet,
        isAssetClassBottomSheetVisible = isAssetClassBottomSheetVisible,
        onShowAssetClassBottomSheet = viewModel::showAssetClassBottomSheet,
        onCreateAssetClick = { viewModel.createOrEditAsset(assetEditorMode = assetEditorMode) },
    )
}

@Composable
private fun AssetEditorScreenContent(
    uiState: AssetEditorScreenUiState,
    assetEditorMode: AssetEditorMode,
    onBackClick: () -> Unit,
    onCreateAssetClick: () -> Unit,
    onNotesChange: (String) -> Unit,
    onPriceChange: (String) -> Unit,
    onFeesChange: (String) -> Unit,
    onNameChange: (String) -> Unit,
    onUrlChange: (String) -> Unit,
    isCurrencyBottomSheetVisible: Boolean,
    isAssetClassBottomSheetVisible: Boolean,
    onCurrencyChange: (Currency) -> Unit,
    onAssetClassChange: (AssetClassWithStringRes) -> Unit,
    onShowCurrencyBottomSheet: () -> Unit,
    onShowAssetClassBottomSheet: () -> Unit,
    assetLoadingState: LoadingState<Asset>?,
) {

    Scaffold(
        modifier = Modifier,
        containerColor = Color.Transparent,
        topBar = {
            AssetEditorTopBar(
                onBackClick = onBackClick,
                onCreateAssetClick = onCreateAssetClick,
                isCreateAssetButtonEnabled =
                    uiState.isValidAsset &&
                        uiState.assetCreationState != AssetCreationState.Loading,
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
            when (assetEditorMode) {
                AssetEditorMode.CREATE ->
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
                        toggleCurrencyBottomSheet = onShowCurrencyBottomSheet,
                        toggleAssetClassBottomSheet = onShowAssetClassBottomSheet,
                    )
                AssetEditorMode.EDIT ->
                    when (assetLoadingState) {
                        LoadingState.Loading -> AssetListLoadingState()
                        LoadingState.NotPresent -> AssetEditorErrorState()
                        is LoadingState.Present<Asset>,
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
                                toggleCurrencyBottomSheet = onShowCurrencyBottomSheet,
                                toggleAssetClassBottomSheet = onShowAssetClassBottomSheet,
                            )
                    }
            }
        }
    }
}
