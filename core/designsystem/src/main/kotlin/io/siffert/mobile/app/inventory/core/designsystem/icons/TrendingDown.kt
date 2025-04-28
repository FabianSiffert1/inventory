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

val InventoryIcons.TrendingDown by lazy { InventoryIcons.getTrendingDown(Color.Black) }

@Suppress("UnusedReceiverParameter", "LongMethod")
private fun InventoryIcons.getTrendingDown(iconColor: Color) =
    ImageVector.Builder(
        name = "TrendingDown",
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
            moveTo(640f, 720f)
            verticalLineToRelative(-80f)
            horizontalLineToRelative(104f)
            lineTo(536f, 434f)
            lineTo(376f, 594f)
            lineTo(80f, 296f)
            lineToRelative(56f, -56f)
            lineToRelative(240f, 240f)
            lineToRelative(160f, -160f)
            lineToRelative(264f, 264f)
            verticalLineToRelative(-104f)
            horizontalLineToRelative(80f)
            verticalLineToRelative(240f)
            close()
        }
    }.build()

@Preview
@Composable
internal fun TrendingDownPreviewDefault() = Icon(Cozy.icon.TrendingDown, contentDescription = null)
