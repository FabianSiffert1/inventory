package io.siffert.mobile.app.core.database

import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module


val databaseCoreKoinModule = module {
    single { InventoryAppDatabaseImpl.initialize(androidContext()) }
}