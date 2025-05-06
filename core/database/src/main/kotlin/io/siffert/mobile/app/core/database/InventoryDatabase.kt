package io.siffert.mobile.app.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import io.siffert.mobile.app.core.database.dao.AssetDao
import io.siffert.mobile.app.core.database.dao.AssetGroupDao
import io.siffert.mobile.app.core.database.dao.HistoricalPriceDao
import io.siffert.mobile.app.core.database.model.AssetEntity
import io.siffert.mobile.app.core.database.model.AssetGroupEntity
import io.siffert.mobile.app.core.database.model.HistoricalPriceEntity

@Database(
    entities = [
        AssetEntity::class,
        AssetGroupEntity::class,
        HistoricalPriceEntity::class,
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun assetDao(): AssetDao
    abstract fun assetGroupDao(): AssetGroupDao
    abstract fun historicalPriceDao(): HistoricalPriceDao
}