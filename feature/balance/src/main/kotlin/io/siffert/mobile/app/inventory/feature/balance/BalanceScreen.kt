package io.siffert.mobile.app.inventory.feature.balance

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.material3.Text

@Composable
fun BalanceScreen(){
BalanceScreen(title = "Balance")
}

@Composable
internal fun BalanceScreen(title:String ="Title"){
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(title)
    }
}