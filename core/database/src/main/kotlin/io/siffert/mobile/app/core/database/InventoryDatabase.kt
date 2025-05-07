package io.siffert.mobile.app.core.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import io.siffert.mobile.app.core.database.dao.AssetDao
import io.siffert.mobile.app.core.database.dao.AssetGroupDao
import io.siffert.mobile.app.core.database.dao.HistoricalPriceDao
import io.siffert.mobile.app.core.database.model.AssetEntity
import io.siffert.mobile.app.core.database.model.AssetGroupEntity
import io.siffert.mobile.app.core.database.model.HistoricalPriceEntity

interface InventoryAppDatabase {
    fun assetDao(): AssetDao
    fun assetGroupDao(): AssetGroupDao
    fun historicalPriceDao(): HistoricalPriceDao
}

@Database(
    entities = [
        AssetEntity::class,
        AssetGroupEntity::class,
        HistoricalPriceEntity::class,
    ],
    version = 1,
    exportSchema = false
)
abstract class InventoryAppDatabaseImpl : RoomDatabase(), InventoryAppDatabase {
    abstract override fun assetDao(): AssetDao
    abstract override fun assetGroupDao(): AssetGroupDao
    abstract override fun historicalPriceDao(): HistoricalPriceDao

    companion object {
        internal fun initialize(applicationContext: Context): InventoryAppDatabase =
            Room.databaseBuilder(
                applicationContext,
                InventoryAppDatabaseImpl::class.java,
                "inventory-app-database"
            ).build()
    }
}