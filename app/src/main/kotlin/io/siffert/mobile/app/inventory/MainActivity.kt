package io.siffert.mobile.app.inventory

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import io.siffert.mobile.app.inventory.ui.AppLayout
import io.siffert.mobile.app.inventory.ui.ScreenB
import theme.InventoryTheme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      InventoryTheme {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = AppLayout) {
          composable<AppLayout> {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally) {
                  Button(onClick = {
                      navController.navigate(ScreenB(
                          name = "Fabian",
                          age = 28
                      ))
                  }) { Text("Go to screen B") }
                }
          }
            composable<ScreenB> {
                val args = it.toRoute<ScreenB>()
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("${args.name}, ${args.age} years old")
                }
            }
        }
      }
    }
  }
}
