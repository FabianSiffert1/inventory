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

val InventoryIcons.Save by lazy { InventoryIcons.getSave(Color.Black) }

@Suppress("UnusedReceiverParameter", "LongMethod")
private fun InventoryIcons.getSave(iconColor: Color) =
    ImageVector.Builder(
            name = "Save",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 960f,
            viewportHeight = 960f,
        )
        .apply {
            path(
                fill = SolidColor(iconColor),
                fillAlpha = 1.0f,
                stroke = null,
                strokeAlpha = 1.0f,
                strokeLineWidth = 1.0f,
                strokeLineCap = StrokeCap.Butt,
                strokeLineJoin = StrokeJoin.Miter,
                strokeLineMiter = 1.0f,
                pathFillType = PathFillType.NonZero,
            ) {
                moveTo(840f, 280f)
                verticalLineToRelative(480f)
                quadToRelative(0f, 33f, -23.5f, 56.5f)
                reflectiveQuadTo(760f, 840f)
                horizontalLineTo(200f)
                quadToRelative(-33f, 0f, -56.5f, -23.5f)
                reflectiveQuadTo(120f, 760f)
                verticalLineToRelative(-560f)
                quadToRelative(0f, -33f, 23.5f, -56.5f)
                reflectiveQuadTo(200f, 120f)
                horizontalLineToRelative(480f)
                close()
                moveToRelative(-80f, 34f)
                lineTo(646f, 200f)
                horizontalLineTo(200f)
                verticalLineToRelative(560f)
                horizontalLineToRelative(560f)
                close()
                moveTo(480f, 720f)
                quadToRelative(50f, 0f, 85f, -35f)
                reflectiveQuadToRelative(35f, -85f)
                reflectiveQuadToRelative(-35f, -85f)
                reflectiveQuadToRelative(-85f, -35f)
                reflectiveQuadToRelative(-85f, 35f)
                reflectiveQuadToRelative(-35f, 85f)
                reflectiveQuadToRelative(35f, 85f)
                reflectiveQuadToRelative(85f, 35f)
                moveTo(240f, 400f)
                horizontalLineToRelative(360f)
                verticalLineToRelative(-160f)
                horizontalLineTo(240f)
                close()
                moveToRelative(-40f, -86f)
                verticalLineToRelative(446f)
                verticalLineToRelative(-560f)
                close()
            }
        }
        .build()

@Preview
@Composable
internal fun SavePreviewDefault() = Icon(Cozy.icon.Save, contentDescription = null)
