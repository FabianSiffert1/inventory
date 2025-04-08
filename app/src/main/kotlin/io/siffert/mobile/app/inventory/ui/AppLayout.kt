package io.siffert.mobile.app.inventory.ui

import androidx.compose.runtime.Composable
import kotlinx.serialization.Serializable
import theme.InventoryTheme

@Serializable
object AppLayout

@Composable
fun AppLayout() =   {}

@Serializable
data class ScreenB(
    val name: String?,
    val age: Int
)