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

val InventoryIcons.Gavel by lazy { InventoryIcons.getGavel(Color.Black) }

@Suppress("UnusedReceiverParameter", "LongMethod")
private fun InventoryIcons.getGavel(iconColor: Color) =
    ImageVector.Builder(
            name = "Gavel",
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
                moveTo(160f, 840f)
                verticalLineToRelative(-80f)
                horizontalLineToRelative(480f)
                verticalLineToRelative(80f)
                close()
                moveToRelative(226f, -194f)
                lineTo(160f, 420f)
                lineToRelative(84f, -86f)
                lineToRelative(228f, 226f)
                close()
                moveToRelative(254f, -254f)
                lineTo(414f, 164f)
                lineToRelative(86f, -84f)
                lineToRelative(226f, 226f)
                close()
                moveToRelative(184f, 408f)
                lineTo(302f, 278f)
                lineToRelative(56f, -56f)
                lineToRelative(522f, 522f)
                close()
            }
        }
        .build()

@Preview
@Composable
internal fun GavelPreviewDefault() = Icon(Cozy.icon.Gavel, contentDescription = null)
