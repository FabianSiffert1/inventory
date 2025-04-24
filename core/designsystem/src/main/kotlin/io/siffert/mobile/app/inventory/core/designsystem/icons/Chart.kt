package io.siffert.mobile.app.inventory.core.designsystem.icons

import androidx.compose.runtime.Composable
import androidx.compose.material3.Icon
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.siffert.mobile.app.inventory.core.designsystem.theme.InventoryIcons
import io.siffert.mobile.app.inventory.core.designsystem.theme.Cozy


val InventoryIcons.Chart by lazy { InventoryIcons.getChart(Color.Black) }

@Suppress("UnusedReceiverParameter", "LongMethod")
private fun InventoryIcons.getChart(iconColor: Color) =
    ImageVector.Builder(
        name = "ChartNoAxesCombined",
        defaultWidth = 24.dp,
        defaultHeight = 24.dp,
        viewportWidth = 24f,
        viewportHeight = 24f
    ).apply {
        path(
            fill = null,
            fillAlpha = 1.0f,
            stroke = SolidColor(iconColor),
            strokeAlpha = 1.0f,
            strokeLineWidth = 2f,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
            strokeLineMiter = 1.0f,
            pathFillType = PathFillType.NonZero
        ) {
            moveTo(12f, 16f)
            verticalLineToRelative(5f)
        }
        path(
            fill = null,
            fillAlpha = 1.0f,
            stroke = SolidColor(iconColor),
            strokeAlpha = 1.0f,
            strokeLineWidth = 2f,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
            strokeLineMiter = 1.0f,
            pathFillType = PathFillType.NonZero
        ) {
            moveTo(16f, 14f)
            verticalLineToRelative(7f)
        }
        path(
            fill = null,
            fillAlpha = 1.0f,
            stroke = SolidColor(iconColor),
            strokeAlpha = 1.0f,
            strokeLineWidth = 2f,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
            strokeLineMiter = 1.0f,
            pathFillType = PathFillType.NonZero
        ) {
            moveTo(20f, 10f)
            verticalLineToRelative(11f)
        }
        path(
            fill = null,
            fillAlpha = 1.0f,
            stroke = SolidColor(iconColor),
            strokeAlpha = 1.0f,
            strokeLineWidth = 2f,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
            strokeLineMiter = 1.0f,
            pathFillType = PathFillType.NonZero
        ) {
            moveTo(22f, 3f)
            lineToRelative(-8.646f, 8.646f)
            arcToRelative(0.5f, 0.5f, 0f, isMoreThanHalf = false, isPositiveArc = true, -0.708f, 0f)
            lineTo(9.354f, 8.354f)
            arcToRelative(0.5f, 0.5f, 0f, isMoreThanHalf = false, isPositiveArc = false, -0.707f, 0f)
            lineTo(2f, 15f)
        }
        path(
            fill = null,
            fillAlpha = 1.0f,
            stroke = SolidColor(iconColor),
            strokeAlpha = 1.0f,
            strokeLineWidth = 2f,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
            strokeLineMiter = 1.0f,
            pathFillType = PathFillType.NonZero
        ) {
            moveTo(4f, 18f)
            verticalLineToRelative(3f)
        }
        path(
            fill = null,
            fillAlpha = 1.0f,
            stroke = SolidColor(iconColor),
            strokeAlpha = 1.0f,
            strokeLineWidth = 2f,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
            strokeLineMiter = 1.0f,
            pathFillType = PathFillType.NonZero
        ) {
            moveTo(8f, 14f)
            verticalLineToRelative(7f)
        }
    }.build()

@Preview
@Composable
internal fun ChartPreviewDefault() = Icon(Cozy.icon.Chart, contentDescription = null)
