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

val InventoryIcons.Security by lazy { InventoryIcons.getSecurity(Color.Black) }

@Suppress("UnusedReceiverParameter", "LongMethod")
private fun InventoryIcons.getSecurity(iconColor: Color) =
    ImageVector.Builder(
            name = "Security",
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
                moveTo(280f, 720f)
                quadToRelative(-100f, 0f, -170f, -70f)
                reflectiveQuadTo(40f, 480f)
                reflectiveQuadToRelative(70f, -170f)
                reflectiveQuadToRelative(170f, -70f)
                quadToRelative(66f, 0f, 121f, 33f)
                reflectiveQuadToRelative(87f, 87f)
                horizontalLineToRelative(432f)
                verticalLineToRelative(240f)
                horizontalLineToRelative(-80f)
                verticalLineToRelative(120f)
                horizontalLineTo(600f)
                verticalLineToRelative(-120f)
                horizontalLineTo(488f)
                quadToRelative(-32f, 54f, -87f, 87f)
                reflectiveQuadToRelative(-121f, 33f)
                moveToRelative(0f, -80f)
                quadToRelative(66f, 0f, 106f, -40.5f)
                reflectiveQuadToRelative(48f, -79.5f)
                horizontalLineToRelative(246f)
                verticalLineToRelative(120f)
                horizontalLineToRelative(80f)
                verticalLineToRelative(-120f)
                horizontalLineToRelative(80f)
                verticalLineToRelative(-80f)
                horizontalLineTo(434f)
                quadToRelative(-8f, -39f, -48f, -79.5f)
                reflectiveQuadTo(280f, 320f)
                reflectiveQuadToRelative(-113f, 47f)
                reflectiveQuadToRelative(-47f, 113f)
                reflectiveQuadToRelative(47f, 113f)
                reflectiveQuadToRelative(113f, 47f)
                moveToRelative(0f, -80f)
                quadToRelative(33f, 0f, 56.5f, -23.5f)
                reflectiveQuadTo(360f, 480f)
                reflectiveQuadToRelative(-23.5f, -56.5f)
                reflectiveQuadTo(280f, 400f)
                reflectiveQuadToRelative(-56.5f, 23.5f)
                reflectiveQuadTo(200f, 480f)
                reflectiveQuadToRelative(23.5f, 56.5f)
                reflectiveQuadTo(280f, 560f)
                moveToRelative(0f, -80f)
            }
        }
        .build()

@Preview
@Composable
internal fun SecurityPreviewDefault() = Icon(Cozy.icon.Security, contentDescription = null)
