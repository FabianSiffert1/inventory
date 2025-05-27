import io.siffert.mobile.app.core.domain.CreateAssetUseCase
import io.siffert.mobile.app.core.domain.UpdateAssetUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val domainCoreKoinModule = module {
    singleOf(::CreateAssetUseCase)
    singleOf(::UpdateAssetUseCase)
}
