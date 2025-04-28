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

val InventoryIcons.TrendingFlat by lazy { InventoryIcons.getTrendingFlat(Color.Black) }

@Suppress("UnusedReceiverParameter", "LongMethod")
private fun InventoryIcons.getTrendingFlat(iconColor: Color) =
    ImageVector.Builder(
        name = "TrendingFlat",
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
            moveTo(700f, 660f)
            lineToRelative(-57f, -56f)
            lineToRelative(84f, -84f)
            horizontalLineTo(120f)
            verticalLineToRelative(-80f)
            horizontalLineToRelative(607f)
            lineToRelative(-83f, -84f)
            lineToRelative(57f, -56f)
            lineToRelative(179f, 180f)
            close()
        }
    }.build()

@Preview
@Composable
internal fun TrendingFlatPreviewDefault() = Icon(Cozy.icon.TrendingFlat, contentDescription = null)
