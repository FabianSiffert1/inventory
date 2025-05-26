package io.siffert.mobile.app.feature.assets

import io.siffert.mobile.app.feature.assets.io.siffert.mobile.app.feature.assets.assetDetails.AssetDetailsScreenViewModel
import io.siffert.mobile.app.feature.assets.io.siffert.mobile.app.feature.assets.assetEditor.AssetEditorMode
import io.siffert.mobile.app.feature.assets.io.siffert.mobile.app.feature.assets.assetEditor.AssetEditorScreenViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val assetKoinModule = module {
    viewModelOf(::AssetsScreenViewModel)
    viewModel { assetId ->
        AssetDetailsScreenViewModel(assetId = assetId.get(), assetRepository = get())
    }
    viewModel { (assetId: String?, assetEditorMode: AssetEditorMode) ->
        AssetEditorScreenViewModel(
            assetId = assetId,
            assetEditorMode = assetEditorMode,
            createAssetUseCase = get(),
            assetRepository = get(),
        )
    }
}
