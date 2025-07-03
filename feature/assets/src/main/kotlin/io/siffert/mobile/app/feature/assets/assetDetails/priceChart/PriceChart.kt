package io.siffert.mobile.app.feature.assets.io.siffert.mobile.app.feature.assets.assetDetails.priceChart

import android.graphics.DashPathEffect
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.siffert.mobile.app.inventory.core.designsystem.theme.InventoryTheme

@Composable
fun PriceChart() {
  Column(
      modifier =
          Modifier.background(
                  color = MaterialTheme.colorScheme.surface, shape = RoundedCornerShape(8.dp))
              .padding(16.dp)) {

        Canvas(modifier = Modifier.fillMaxWidth().height(200.dp)) {
          val width = size.width
          val height = size.height
          drawLine(
              color = Color.Black,
              start = Offset(x = 0f, 0f),
              end = Offset(x = width, y = height),
              strokeWidth = 5f)
          drawIntoCanvas { canvas ->
            val paint =
                Paint().asFrameworkPaint().apply {
                  color = Color.Gray.toArgb()
                  strokeWidth = 2f
                  pathEffect = DashPathEffect(floatArrayOf(10f, 10f), 0f)
                }
            drawRect(color = Color.Red, topLeft = Offset(width/2, height/2), size = Size(100f, 100f))
            canvas.nativeCanvas.drawLine(1f, 3f, 5f, 6f, paint)
          }
        }
      }
}

@Preview
@Composable
private fun PriceChartPreview() {
  InventoryTheme { PriceChart() }
}
