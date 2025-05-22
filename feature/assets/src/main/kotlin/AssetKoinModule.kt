package io.siffert.mobile.app.feature.assets

import io.siffert.mobile.app.feature.assets.io.siffert.mobile.app.feature.assets.assetCreation.AssetCreationScreenViewModel
import io.siffert.mobile.app.feature.assets.io.siffert.mobile.app.feature.assets.assetDetails.AssetDetailsScreenViewModel
import io.siffert.mobile.app.feature.assets.io.siffert.mobile.app.feature.assets.assetEditor.AssetEditorScreenViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val assetKoinModule = module {
    viewModelOf(::AssetsScreenViewModel)
    viewModelOf(::AssetCreationScreenViewModel)
    viewModel { assetId ->
        AssetDetailsScreenViewModel(assetId = assetId.get(), assetRepository = get())
    }
    viewModel { assetId ->
        AssetEditorScreenViewModel(assetId = assetId.get(), assetRepository = get())
    }
}
