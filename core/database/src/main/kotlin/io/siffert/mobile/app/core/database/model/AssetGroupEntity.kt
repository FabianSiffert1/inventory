package io.siffert.mobile.app.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import io.siffert.mobile.app.model.data.AssetGroup

@Entity(tableName = "asset_groups")
data class AssetGroupEntity(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "name") val name: String
)

fun AssetGroupEntity.asExternalModel() = AssetGroup(
    id = id,
    name = name
)