package io.siffert.mobile.app.inventory.util

import io.siffert.mobile.app.core.common.config.AppEnvironment
import io.siffert.mobile.app.inventory.BuildConfig

object AppEnvironmentImpl : AppEnvironment {
    override val isDebug: Boolean = BuildConfig.DEBUG
}
