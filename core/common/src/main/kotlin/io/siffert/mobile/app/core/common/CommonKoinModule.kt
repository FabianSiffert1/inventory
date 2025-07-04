package io.siffert.mobile.app.core.common

import io.siffert.mobile.app.core.common.dialog.dialogs.EditPriceEventDialogViewModel
import io.siffert.mobile.app.core.common.dialog.handling.DialogManager
import io.siffert.mobile.app.core.common.dialog.handling.DialogManagerImpl
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val commonKoinModule = module {
    singleOf(::DialogManagerImpl) bind DialogManager::class
}
