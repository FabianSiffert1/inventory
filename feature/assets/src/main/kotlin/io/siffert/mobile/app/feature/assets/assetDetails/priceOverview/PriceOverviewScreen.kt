package io.siffert.mobile.app.feature.assets.io.siffert.mobile.app.feature.assets.assetDetails.priceOverview

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation3.runtime.NavKey
import io.siffert.mobile.app.core.common.dialog.AppDialog
import io.siffert.mobile.app.core.common.dialog.handling.DialogManager
import io.siffert.mobile.app.feature.assets.R
import io.siffert.mobile.app.feature.assets.io.siffert.mobile.app.feature.assets.components.AssetListLoadingState
import io.siffert.mobile.app.feature.assets.io.siffert.mobile.app.feature.assets.components.toFullDateString
import io.siffert.mobile.app.inventory.core.designsystem.icons.Code
import io.siffert.mobile.app.inventory.core.designsystem.theme.Cozy
import io.siffert.mobile.app.inventory.core.designsystem.theme.InventoryTheme
import io.siffert.mobile.app.model.data.Currency
import io.siffert.mobile.app.model.data.PriceHistoryEntry
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject
import org.koin.core.parameter.parametersOf

@Serializable data class PriceOverviewRoute(val assetId: String) : NavKey

@Composable
fun PriceOverviewScreen(
    assetId: String?,
    navigateBack: () -> Unit,
) {
  val viewModel: PriceOverviewViewModel = koinViewModel { parametersOf(assetId) }
  val uiState: PriceOverviewScreenUiState by viewModel.uiState.collectAsStateWithLifecycle()

  val dialogManager: DialogManager = koinInject()

  PriceOverviewScreenContent(
      uiState = uiState,
      navigateBack = navigateBack,
      navigateToPriceEditorDialog = {
        dialogManager.enqueue(
            AppDialog.EditPriceDialog(
                priceHistoryEntry = it,
                onConfirm = {
                  // todo: implement
                }))
      })
}

@Composable
private fun PriceOverviewScreenContent(
    uiState: PriceOverviewScreenUiState,
    navigateToPriceEditorDialog: (priceHistoryEntry: PriceHistoryEntry?) -> Unit,
    navigateBack: () -> Unit,
) {

  Scaffold(
      modifier = Modifier,
      containerColor = Color.Transparent,
      topBar = {
        PriceOverviewTopBar(
            assetName =
                when (uiState) {
                  PriceOverviewScreenUiState.Loading ->
                      stringResource(id = R.string.feature_assets_loading)
                  PriceOverviewScreenUiState.Empty ->
                      stringResource(id = R.string.feature_asset_price_overview_asset_name)
                  is PriceOverviewScreenUiState.Success -> uiState.assetName
                },
            assetSaleInfo = null,
            onBackClick = navigateBack,
            onAddPriceHistoryEntryClick = { navigateToPriceEditorDialog(null) })
      },
  ) { paddingValues ->
    Crossfade(modifier = Modifier.padding(paddingValues), targetState = uiState) {
      when (it) {
          // Empty should never occur
        PriceOverviewScreenUiState.Empty,
        PriceOverviewScreenUiState.Loading -> AssetListLoadingState()
        is PriceOverviewScreenUiState.Success ->
            PriceOverviewList(
                assetPriceList = it.assetList,
                assetCurrency = it.assetCurrency,
                navigateToPriceEditorDialog = navigateToPriceEditorDialog)
      }
    }
  }
}

@Composable
private fun PriceOverviewList(
    assetPriceList: List<PriceHistoryEntry>,
    assetCurrency: Currency,
    navigateToPriceEditorDialog: (PriceHistoryEntry?) -> Unit
) {
  LazyColumn(
      modifier = Modifier.padding(horizontal = 16.dp),
      verticalArrangement = Arrangement.spacedBy(4.dp)) {
        items(items = assetPriceList) { priceHistoryEntry ->
          ListItem(
              modifier =
                  Modifier.fillMaxWidth()
                      .clip(shape = RoundedCornerShape(8.dp))
                      .clickable(
                          enabled = true,
                          onClick = { navigateToPriceEditorDialog(priceHistoryEntry) }),
              colors = ListItemDefaults.colors(containerColor = MaterialTheme.colorScheme.surface),
              leadingContent = {
                // todo: replace with arrow up, down sideways?
                Icon(
                    imageVector = Cozy.icon.Code,
                    contentDescription = priceHistoryEntry.value.toString())
              },
              headlineContent = {
                Text(text = "${priceHistoryEntry.value.toString()} $assetCurrency")
              },
              supportingContent = { Text(text = priceHistoryEntry.timestamp.toFullDateString()) })
        }
      }
}

@Composable
@Preview
private fun PriceOverviewScreenPreview() {
  InventoryTheme {
    // todo: implement with PriceOverviewScreenContent()
  }
}
