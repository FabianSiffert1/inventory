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

val InventoryIcons.RealAsset by lazy { InventoryIcons.getRealAsset(Color.Black) }

@Suppress("UnusedReceiverParameter", "LongMethod")
private fun InventoryIcons.getRealAsset(iconColor: Color) =
    ImageVector.Builder(
            name = "RealAsset",
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
                moveTo(760f, 560f)
                verticalLineToRelative(-260f)
                lineTo(560f, 160f)
                lineTo(360f, 300f)
                verticalLineToRelative(60f)
                horizontalLineToRelative(-80f)
                verticalLineToRelative(-100f)
                lineToRelative(280f, -200f)
                lineToRelative(280f, 200f)
                verticalLineToRelative(300f)
                close()
                moveTo(580f, 320f)
                horizontalLineToRelative(40f)
                verticalLineToRelative(-40f)
                horizontalLineToRelative(-40f)
                close()
                moveToRelative(-80f, 0f)
                horizontalLineToRelative(40f)
                verticalLineToRelative(-40f)
                horizontalLineToRelative(-40f)
                close()
                moveToRelative(80f, 80f)
                horizontalLineToRelative(40f)
                verticalLineToRelative(-40f)
                horizontalLineToRelative(-40f)
                close()
                moveToRelative(-80f, 0f)
                horizontalLineToRelative(40f)
                verticalLineToRelative(-40f)
                horizontalLineToRelative(-40f)
                close()
                moveTo(280f, 740f)
                lineToRelative(278f, 76f)
                lineToRelative(238f, -74f)
                quadToRelative(-5f, -9f, -14.5f, -15.5f)
                reflectiveQuadTo(760f, 720f)
                horizontalLineTo(558f)
                quadToRelative(-27f, 0f, -43f, -2f)
                reflectiveQuadToRelative(-33f, -8f)
                lineToRelative(-93f, -31f)
                lineToRelative(22f, -78f)
                lineToRelative(81f, 27f)
                quadToRelative(17f, 5f, 40f, 8f)
                reflectiveQuadToRelative(68f, 4f)
                quadToRelative(0f, -11f, -6.5f, -21f)
                reflectiveQuadTo(578f, 606f)
                lineToRelative(-234f, -86f)
                horizontalLineToRelative(-64f)
                close()
                moveTo(40f, 880f)
                verticalLineToRelative(-440f)
                horizontalLineToRelative(304f)
                quadToRelative(7f, 0f, 14f, 1.5f)
                reflectiveQuadToRelative(13f, 3.5f)
                lineToRelative(235f, 87f)
                quadToRelative(33f, 12f, 53.5f, 42f)
                reflectiveQuadToRelative(20.5f, 66f)
                horizontalLineToRelative(80f)
                quadToRelative(50f, 0f, 85f, 33f)
                reflectiveQuadToRelative(35f, 87f)
                verticalLineToRelative(40f)
                lineTo(560f, 900f)
                lineToRelative(-280f, -78f)
                verticalLineToRelative(58f)
                close()
                moveToRelative(80f, -80f)
                horizontalLineToRelative(80f)
                verticalLineToRelative(-280f)
                horizontalLineToRelative(-80f)
                close()
            }
        }
        .build()

@Preview
@Composable
internal fun RealAssetPreviewDefault() = Icon(Cozy.icon.RealAsset, contentDescription = null)
