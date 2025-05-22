package io.siffert.mobile.app.core.common.flow

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapLatest

sealed interface LoadingState<out T> {

    data object Loading : LoadingState<Nothing>

    data class Present<T>(val value: T) : LoadingState<T>

    data object NotPresent : LoadingState<Nothing>
}

fun <T> Flow<T>.mapToLoadingStatePresent(): Flow<LoadingState<T>> = mapLatest {
    LoadingState.Present(it)
}
