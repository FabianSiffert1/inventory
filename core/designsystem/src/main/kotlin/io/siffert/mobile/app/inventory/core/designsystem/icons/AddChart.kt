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

val InventoryIcons.AddChart by lazy { InventoryIcons.getAddChart(Color.Black) }

@Suppress("UnusedReceiverParameter", "LongMethod")
private fun InventoryIcons.getAddChart(iconColor: Color) =
    ImageVector.Builder(
        name = "AddChart",
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
            moveTo(200f, 840f)
            quadToRelative(-33f, 0f, -56.5f, -23.5f)
            reflectiveQuadTo(120f, 760f)
            verticalLineToRelative(-560f)
            quadToRelative(0f, -33f, 23.5f, -56.5f)
            reflectiveQuadTo(200f, 120f)
            horizontalLineToRelative(360f)
            verticalLineToRelative(80f)
            horizontalLineTo(200f)
            verticalLineToRelative(560f)
            horizontalLineToRelative(560f)
            verticalLineToRelative(-360f)
            horizontalLineToRelative(80f)
            verticalLineToRelative(360f)
            quadToRelative(0f, 33f, -23.5f, 56.5f)
            reflectiveQuadTo(760f, 840f)
            close()
            moveToRelative(80f, -160f)
            horizontalLineToRelative(80f)
            verticalLineToRelative(-280f)
            horizontalLineToRelative(-80f)
            close()
            moveToRelative(160f, 0f)
            horizontalLineToRelative(80f)
            verticalLineToRelative(-400f)
            horizontalLineToRelative(-80f)
            close()
            moveToRelative(160f, 0f)
            horizontalLineToRelative(80f)
            verticalLineToRelative(-160f)
            horizontalLineToRelative(-80f)
            close()
            moveToRelative(80f, -320f)
            verticalLineToRelative(-80f)
            horizontalLineToRelative(-80f)
            verticalLineToRelative(-80f)
            horizontalLineToRelative(80f)
            verticalLineToRelative(-80f)
            horizontalLineToRelative(80f)
            verticalLineToRelative(80f)
            horizontalLineToRelative(80f)
            verticalLineToRelative(80f)
            horizontalLineToRelative(-80f)
            verticalLineToRelative(80f)
            close()
            moveTo(480f, 480f)
        }
    }.build()

@Preview
@Composable
internal fun AddChartPreviewDefault() = Icon(Cozy.icon.AddChart, contentDescription = null)
