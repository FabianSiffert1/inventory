package io.siffert.mobile.app.inventory.feature.balance

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun BalanceScreen(onNavigateToBalanceSubScreen: () -> Unit) {
  BalanceScreen(title = "Balance", onNavigateToBalanceSubScreen = onNavigateToBalanceSubScreen)
}

@Composable
internal fun BalanceScreen(title: String = "Title", onNavigateToBalanceSubScreen: () -> Unit) {
  Column(
      modifier = Modifier.fillMaxSize(),
      verticalArrangement = Arrangement.Center,
      horizontalAlignment = Alignment.CenterHorizontally) {
        Text(title)
        Button(onClick = onNavigateToBalanceSubScreen) { Text("Navigate to Balance SubScreen") }
      }
}
