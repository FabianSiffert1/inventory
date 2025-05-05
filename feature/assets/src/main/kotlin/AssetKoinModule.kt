package io.siffert.mobile.app.feature.assets

import org.koin.dsl.module

val assetKoinModule = module {
        single {
            AssetsScreenViewModel()
        }
}