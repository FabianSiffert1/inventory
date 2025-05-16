import io.siffert.mobile.app.core.domain.CreateAssetUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val domainCoreKoinModule = module { singleOf(::CreateAssetUseCase) }
