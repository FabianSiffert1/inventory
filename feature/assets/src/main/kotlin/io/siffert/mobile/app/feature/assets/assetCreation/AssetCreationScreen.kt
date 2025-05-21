package io.siffert.mobile.app.feature.assets.io.siffert.mobile.app.feature.assets.assetCreation

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.siffert.mobile.app.feature.assets.R
import io.siffert.mobile.app.feature.assets.io.siffert.mobile.app.feature.assets.components.AssetClassIcon
import io.siffert.mobile.app.feature.assets.io.siffert.mobile.app.feature.assets.components.AssetClassWithStringRes
import io.siffert.mobile.app.feature.assets.io.siffert.mobile.app.feature.assets.components.AssetDropdownMenu
import io.siffert.mobile.app.feature.assets.io.siffert.mobile.app.feature.assets.components.AssetTextField
import io.siffert.mobile.app.model.data.Currency
import org.koin.compose.viewmodel.koinViewModel

@Composable
internal fun AssetCreationScreen(
    navigateBack: () -> Unit,
    viewModel: AssetCreationScreenViewModel = koinViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.uiCommands.collect { command ->
            when (command) {
                AssetCreationScreenUiCommand.NavigateBack -> navigateBack()
            }
        }
    }

    AssetDetailScreenContent(
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
private fun AssetDetailScreenContent(
    uiState: AssetCreationScreenUiState,
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
            if (uiState.assetCreationState == AssetCreationState.Failure) {
                ListItem(
                    modifier =
                        Modifier.clip(RoundedCornerShape(8.dp))
                            .border(
                                width = 1.dp,
                                color = Color.Red,
                                shape = RoundedCornerShape(8.dp),
                            ),
                    headlineContent = {
                        Text(stringResource(id = R.string.feature_assets_creation_failed_title))
                    },
                    supportingContent = {
                        Text(stringResource(id = R.string.feature_assets_creation_failed_subtitle))
                    },
                )
            }
            AssetTextField(
                input = uiState.nameInput.text,
                onInputChange = onNameChange,
                inputLabel = stringResource(id = R.string.feature_assets_creation_name),
            )
            AssetTextField(
                input = uiState.currentPrice.text,
                inputLabel = stringResource(id = R.string.feature_assets_asset_creation_price),
                onInputChange = onPriceChange,
                numericOnly = true,
            )
            AssetDropdownMenu(
                values = Currency.entries.toTypedArray(),
                currentlySelected = uiState.currency.name,
                assetLabel = stringResource(id = R.string.feature_assets_creation_currency),
                onItemSelected = onCurrencyChange,
            )
            AssetDropdownMenu(
                values = AssetClassWithStringRes.entries.toTypedArray(),
                currentlySelected =
                    stringResource(id = uiState.assetClassWithStringRes.nameResource),
                onItemSelected = onAssetClassChange,
                assetLabel = stringResource(id = R.string.feature_assets_creation_asset_class),
                trailingIcon = { AssetClassIcon(uiState.assetClassWithStringRes.assetClass) },
            )
            HorizontalDivider()
            Text(
                modifier = Modifier.padding(start = 8.dp),
                text = stringResource(id = R.string.feature_assets_creation_optional),
                fontSize = MaterialTheme.typography.labelSmall.fontSize,
                textAlign = TextAlign.Left,
            )
            AssetTextField(
                input = uiState.feesInput.text,
                inputLabel = stringResource(id = R.string.feature_assets_creation_fees),
                onInputChange = onFeesChange,
                numericOnly = true,
            )
            AssetTextField(
                input = uiState.urlInput.text,
                onInputChange = onUrlChange,
                inputLabel = stringResource(id = R.string.feature_assets_creation_url),
            )
            AssetTextField(
                input = uiState.notesInput.text,
                onInputChange = onNotesChange,
                inputLabel = stringResource(id = R.string.feature_assets_creation_notes),
            )
        }
    }
}
