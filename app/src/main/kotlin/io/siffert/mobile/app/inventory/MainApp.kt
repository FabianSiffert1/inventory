package io.siffert.mobile.app.inventory

import android.app.Application
import io.siffert.mobile.app.core.database.databaseCoreKoinModule
import io.siffert.mobile.app.feature.assets.assetKoinModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MainApplication.applicationContext)
            modules(
                listOf(
                    assetKoinModule,
                    databaseCoreKoinModule
                )
            )
        }
    }
}