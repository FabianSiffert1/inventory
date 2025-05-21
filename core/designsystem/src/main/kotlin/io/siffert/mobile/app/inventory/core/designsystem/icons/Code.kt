package io.siffert.mobile.app.inventory.core.designsystem.icons

import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.siffert.mobile.app.inventory.core.designsystem.theme.Cozy
import io.siffert.mobile.app.inventory.core.designsystem.theme.InventoryIcons

val InventoryIcons.Code by lazy { InventoryIcons.getCode(Color.Black) }

@Suppress("UnusedReceiverParameter", "LongMethod")
private fun InventoryIcons.getCode(iconColor: Color) =
    ImageVector.Builder(
            name = "code",
            defaultWidth = 24.0.dp,
            defaultHeight = 24.0.dp,
            viewportWidth = 24.0f,
            viewportHeight = 24.0f,
        )
        .apply {
            path(fill = SolidColor(iconColor)) {
                moveTo(8.7f, 15.9f)
                lineTo(4.8f, 12f)
                lineTo(8.7f, 8.1f)
                curveTo(9.09f, 7.71f, 9.09f, 7.09f, 8.7f, 6.7f)
                curveTo(8.31f, 6.31f, 7.69f, 6.31f, 7.3f, 6.7f)
                lineTo(2.71f, 11.29f)
                curveTo(2.32f, 11.68f, 2.32f, 12.31f, 2.71f, 12.7f)
                lineTo(7.3f, 17.3f)
                curveTo(7.69f, 17.69f, 8.31f, 17.69f, 8.7f, 17.3f)
                curveTo(9.09f, 16.91f, 9.09f, 16.29f, 8.7f, 15.9f)
                close()
                moveTo(15.3f, 15.9f)
                lineTo(19.2f, 12f)
                lineTo(15.3f, 8.1f)
                curveTo(14.91f, 7.71f, 14.91f, 7.09f, 15.3f, 6.7f)
                curveTo(15.69f, 6.31f, 16.31f, 6.31f, 16.7f, 6.7f)
                lineTo(21.29f, 11.29f)
                curveTo(21.68f, 11.68f, 21.68f, 12.31f, 21.29f, 12.7f)
                lineTo(16.7f, 17.3f)
                curveTo(16.31f, 17.69f, 15.69f, 17.69f, 15.3f, 17.3f)
                curveTo(14.91f, 16.91f, 14.91f, 16.29f, 15.3f, 15.9f)
                close()
            }
        }
        .build()

@Preview @Composable internal fun CodePreview() = Icon(Cozy.icon.Code, contentDescription = null)
