package io.siffert.mobile.app.feature.assets.io.siffert.mobile.app.feature.assets.assetDetails.priceOverview

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import io.siffert.mobile.app.feature.assets.R
import io.siffert.mobile.app.inventory.core.designsystem.icons.Gavel
import io.siffert.mobile.app.inventory.core.designsystem.theme.Cozy
import io.siffert.mobile.app.inventory.core.designsystem.theme.InventoryTheme
import io.siffert.mobile.app.model.data.SaleEntry

@Composable
internal fun PriceOverviewTopBar(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    assetName: String?,
    assetSaleInfo: SaleEntry?,
) {
  CenterAlignedTopAppBar(
      title = {
        if (assetName != null) {
          Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Text(text = assetName, maxLines = 1, overflow = TextOverflow.Ellipsis)
            if (assetSaleInfo != null)
                Icon(
                    imageVector = Cozy.icon.Gavel,
                    contentDescription = stringResource(id = R.string.feature_assets_sold),
                )
          }
        } else {
          Text(text = stringResource(id = R.string.feature_asset_price_overview_price_history))
        }
      },
      navigationIcon = {
        IconButton(onClick = onBackClick) {
          Icon(
              imageVector = Icons.AutoMirrored.Filled.ArrowBack,
              contentDescription = stringResource(id = R.string.feature_assets_navigate_back),
              tint = MaterialTheme.colorScheme.onSurface,
          )
        }
      },
      actions = {},
      colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent),
      modifier = modifier.testTag("priceOverviewTopBar"),
  )
}

@Composable
@PreviewLightDark
private fun Preview() = InventoryTheme {
  Column(modifier = Modifier.background(color = MaterialTheme.colorScheme.background)) {
      // todo: implement preview
  }
}
