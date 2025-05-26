package io.siffert.mobile.app.feature.assets.io.siffert.mobile.app.feature.assets.assetEditor

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.siffert.mobile.app.feature.assets.R
import io.siffert.mobile.app.feature.assets.io.siffert.mobile.app.feature.assets.components.AssetBottomSheetListItem
import io.siffert.mobile.app.feature.assets.io.siffert.mobile.app.feature.assets.components.AssetClassIcon
import io.siffert.mobile.app.feature.assets.io.siffert.mobile.app.feature.assets.components.AssetClassWithStringRes
import io.siffert.mobile.app.feature.assets.io.siffert.mobile.app.feature.assets.components.AssetTextField
import io.siffert.mobile.app.model.data.Currency
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
internal fun AssetEditorScreen(navigateBack: () -> Unit, assetId: String?) {
    val viewModel: AssetEditorScreenViewModel = koinViewModel { parametersOf(assetId) }

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.uiCommands.collect { command ->
            when (command) {
                AssetEditorScreenUiCommand.NavigateBack -> navigateBack()
            }
        }
    }

    AssetEditorScreenContent(
        uiState = uiState,
        onBackClick = navigateBack,
        onNameChange = viewModel::onNameChange,
        onFeesChange = viewModel::onFeesChange,
        onNotesChange = viewModel::onNotesChange,
        onAssetClassChange = viewModel::onAssetClassChange,
        onUrlChange = viewModel::onUrlChange,
        onPriceChange = viewModel::onPriceChange,
        onCurrencyChange = viewModel::onCurrencyChange,
        onCreateAssetClick = viewModel::createAsset,
    )
}

@Composable
private fun AssetEditorScreenContent(
    uiState: AssetEditorScreenUiState,
    onBackClick: () -> Unit,
    onCreateAssetClick: () -> Unit,
    onNotesChange: (String) -> Unit,
    onPriceChange: (String) -> Unit,
    onFeesChange: (String) -> Unit,
    onNameChange: (String) -> Unit,
    onUrlChange: (String) -> Unit,
    onCurrencyChange: (Currency) -> Unit,
    onAssetClassChange: (AssetClassWithStringRes) -> Unit,
) {

    var isCurrencyBottomSheetVisible by remember { mutableStateOf(false) }
    var isAssetClassBottomSheetVisible by remember { mutableStateOf(false) }

    Scaffold(
        modifier = Modifier,
        containerColor = Color.Transparent,
        topBar = {
            AssetCreationTopBar(
                onBackClick = onBackClick,
                onCreateAssetClick = onCreateAssetClick,
                isCreateAssetButtonEnabled =
                    uiState.isValidAsset && uiState.assetCreationState != AssetCreationState.Loading,
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
                toggleCurrencyBottomSheet = {
                    isCurrencyBottomSheetVisible = !isCurrencyBottomSheetVisible
                },
                toggleAssetClassBottomSheet = {
                    isAssetClassBottomSheetVisible = !isAssetClassBottomSheetVisible
                },
            )
        }
    }
}

@Composable
private fun AssetEditorInputFields(
    uiState: AssetEditorScreenUiState,
    onNameChange: (String) -> Unit,
    onPriceChange: (String) -> Unit,
    isCurrencyBottomSheetVisible: Boolean,
    toggleCurrencyBottomSheet: () -> Unit,
    toggleAssetClassBottomSheet: () -> Unit,
    onCurrencyChange: (Currency) -> Unit,
    isAssetClassBottomSheetVisible: Boolean,
    onAssetClassChange: (AssetClassWithStringRes) -> Unit,
    onFeesChange: (String) -> Unit,
    onUrlChange: (String) -> Unit,
    onNotesChange: (String) -> Unit,
) {
    if (uiState.assetCreationState == AssetCreationState.Failure) {
        AssetEditingFailedListItem()
    }
    AssetTextField(
        input = uiState.assetEditorInputFields.name.text,
        onInputChange = onNameChange,
        inputLabel = stringResource(id = R.string.feature_assets_creation_name),
    )
    AssetTextField(
        input = uiState.assetEditorInputFields.currentPrice.text,
        inputLabel = stringResource(id = R.string.feature_assets_asset_creation_price),
        onInputChange = onPriceChange,
        numericOnly = true,
    )
    AssetBottomSheetListItem(
        toggleBottomSheet = toggleCurrencyBottomSheet,
        currentlySelectedItemName = uiState.assetEditorInputFields.currency.name,
        showBottomSheet = isCurrencyBottomSheetVisible,
        onItemClick = onCurrencyChange,
        enumEntries = Currency.entries.toTypedArray(),
        label = stringResource(id = R.string.feature_assets_creation_currency),
    )

    AssetBottomSheetListItem(
        toggleBottomSheet = toggleAssetClassBottomSheet,
        currentlySelectedItemName =
            stringResource(
                id = uiState.assetEditorInputFields.assetClassWithStringRes.nameResource
            ),
        showBottomSheet = isAssetClassBottomSheetVisible,
        label = stringResource(id = R.string.feature_assets_creation_asset_class),
        enumEntries = AssetClassWithStringRes.entries.toTypedArray(),
        onItemClick = onAssetClassChange,
        trailingContent = {
            AssetClassIcon(uiState.assetEditorInputFields.assetClassWithStringRes.assetClass)
        },
    )
    HorizontalDivider()
    Text(
        modifier = Modifier.padding(start = 8.dp),
        text = stringResource(id = R.string.feature_assets_creation_optional),
        fontSize = MaterialTheme.typography.labelSmall.fontSize,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
        textAlign = TextAlign.Left,
    )
    AssetTextField(
        input = uiState.assetEditorInputFields.fees.text,
        inputLabel = stringResource(id = R.string.feature_assets_creation_fees),
        onInputChange = onFeesChange,
        numericOnly = true,
    )
    AssetTextField(
        input = uiState.assetEditorInputFields.url.text,
        onInputChange = onUrlChange,
        inputLabel = stringResource(id = R.string.feature_assets_creation_url),
    )
    AssetTextField(
        input = uiState.assetEditorInputFields.notes.text,
        onInputChange = onNotesChange,
        inputLabel = stringResource(id = R.string.feature_assets_creation_notes),
    )
}

@Composable
private fun AssetEditingFailedListItem() {
    ListItem(
        modifier =
            Modifier.clip(RoundedCornerShape(8.dp))
                .border(width = 1.dp, color = Color.Red, shape = RoundedCornerShape(8.dp)),
        headlineContent = {
            Text(stringResource(id = R.string.feature_assets_creation_failed_title))
        },
        supportingContent = {
            Text(stringResource(id = R.string.feature_assets_creation_failed_subtitle))
        },
    )
}
