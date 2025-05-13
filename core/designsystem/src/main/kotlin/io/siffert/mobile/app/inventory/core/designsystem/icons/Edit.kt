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

val InventoryIcons.Edit by lazy { InventoryIcons.getEdit(Color.Black) }

@Suppress("UnusedReceiverParameter", "LongMethod")
private fun InventoryIcons.getEdit(iconColor: Color) =
    ImageVector.Builder(
            name = "Edit",
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
                moveTo(200f, 760f)
                horizontalLineToRelative(57f)
                lineToRelative(391f, -391f)
                lineToRelative(-57f, -57f)
                lineToRelative(-391f, 391f)
                close()
                moveToRelative(-80f, 80f)
                verticalLineToRelative(-170f)
                lineToRelative(528f, -527f)
                quadToRelative(12f, -11f, 26.5f, -17f)
                reflectiveQuadToRelative(30.5f, -6f)
                reflectiveQuadToRelative(31f, 6f)
                reflectiveQuadToRelative(26f, 18f)
                lineToRelative(55f, 56f)
                quadToRelative(12f, 11f, 17.5f, 26f)
                reflectiveQuadToRelative(5.5f, 30f)
                quadToRelative(0f, 16f, -5.5f, 30.5f)
                reflectiveQuadTo(817f, 313f)
                lineTo(290f, 840f)
                close()
                moveToRelative(640f, -584f)
                lineToRelative(-56f, -56f)
                close()
                moveToRelative(-141f, 85f)
                lineToRelative(-28f, -29f)
                lineToRelative(57f, 57f)
                close()
            }
        }
        .build()

@Preview
@Composable
internal fun EditPreviewDefault() = Icon(Cozy.icon.Edit, contentDescription = null)
