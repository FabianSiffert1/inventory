package io.siffert.mobile.app.core.data

import io.siffert.mobile.app.core.data.repository.AssetRepository
import io.siffert.mobile.app.core.data.repository.AssetRepositoryImpl
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val dataKoinModule = module {
    singleOf(::AssetRepositoryImpl) bind AssetRepository::class
}