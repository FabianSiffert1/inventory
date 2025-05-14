package io.siffert.mobile.app.core.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import io.siffert.mobile.app.core.database.dao.AssetDao
import io.siffert.mobile.app.core.database.dao.AssetGroupDao
import io.siffert.mobile.app.core.database.dao.PriceHistoryDao
import io.siffert.mobile.app.core.database.io.siffert.mobile.app.core.database.dao.SalesDao
import io.siffert.mobile.app.core.database.io.siffert.mobile.app.core.database.model.SalesEntity
import io.siffert.mobile.app.core.database.model.AssetEntity
import io.siffert.mobile.app.core.database.model.AssetGroupEntity
import io.siffert.mobile.app.core.database.model.PriceHistoryEntity

interface InventoryAppDatabase {
    fun assetDao(): AssetDao

    fun assetGroupDao(): AssetGroupDao

    fun priceHistoryDao(): PriceHistoryDao

    fun salesDao(): SalesDao
}

@Database(
    entities =
        [
            AssetEntity::class,
            AssetGroupEntity::class,
            PriceHistoryEntity::class,
            SalesEntity::class,
        ],
    version = 5,
    exportSchema = false,
)
abstract class InventoryAppDatabaseImpl : RoomDatabase(), InventoryAppDatabase {
    abstract override fun assetDao(): AssetDao

    abstract override fun assetGroupDao(): AssetGroupDao

    abstract override fun priceHistoryDao(): PriceHistoryDao

    abstract override fun salesDao(): SalesDao

    companion object {
        internal fun initialize(applicationContext: Context): InventoryAppDatabase =
            Room.databaseBuilder(
                    applicationContext,
                    InventoryAppDatabaseImpl::class.java,
                    "inventory-app-database",
                )
                .fallbackToDestructiveMigration(dropAllTables = true)
                .build()
    }
}
