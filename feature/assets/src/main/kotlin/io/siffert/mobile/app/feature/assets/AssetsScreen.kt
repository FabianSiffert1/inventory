package io.siffert.mobile.app.feature.assets

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
internal fun AssetsScreen(){
    AssetsScreen(title = "Assets")
}

@Composable
internal fun AssetsScreen(title:String ="Title"){
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(title)
    }
}