package io.siffert.mobile.app.inventory

import io.siffert.mobile.app.core.common.config.AppEnvironment
import io.siffert.mobile.app.inventory.util.AppEnvironmentImpl
import org.koin.dsl.module

val mainKoinModule = module { single<AppEnvironment> { AppEnvironmentImpl } }
