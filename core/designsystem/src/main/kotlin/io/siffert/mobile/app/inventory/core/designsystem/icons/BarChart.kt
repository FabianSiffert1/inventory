package io.siffert.mobile.app.inventory.core.designsystem.icons


import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.siffert.mobile.app.inventory.core.designsystem.theme.Cozy
import io.siffert.mobile.app.inventory.core.designsystem.theme.InventoryIcons

val InventoryIcons.BarChart by lazy { InventoryIcons.getBarChart(Color.Black) }

@Suppress("UnusedReceiverParameter", "LongMethod")
private fun InventoryIcons.getBarChart(iconColor: Color) =
    ImageVector.Builder(
        name = "BarChart",
        defaultWidth = 24.dp,
        defaultHeight = 24.dp,
        viewportWidth = 960f,
        viewportHeight = 960f
    ).apply {
        path(
            fill = SolidColor(iconColor),
            fillAlpha = 1.0f,
            stroke = null,
            strokeAlpha = 1.0f,
            strokeLineWidth = 1.0f,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 1.0f,
            pathFillType = PathFillType.NonZero
        ) {
            moveTo(640f, 800f)
            verticalLineToRelative(-280f)
            horizontalLineToRelative(160f)
            verticalLineToRelative(280f)
            close()
            moveToRelative(-240f, 0f)
            verticalLineToRelative(-640f)
            horizontalLineToRelative(160f)
            verticalLineToRelative(640f)
            close()
            moveToRelative(-240f, 0f)
            verticalLineToRelative(-440f)
            horizontalLineToRelative(160f)
            verticalLineToRelative(440f)
            close()
        }
    }.build()

@Preview
@Composable
internal fun BarChartPreviewDefault() = Icon(Cozy.icon.BarChart, contentDescription = null)
