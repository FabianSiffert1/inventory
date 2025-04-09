package io.siffert.mobile.app.inventory

import kotlinx.serialization.Serializable

@Serializable
data class Dog(
    val id :Int,
    val name: String,
)

enum class BreedSize {
    SMALL,
    MEDIUM,
    LARGE
}

@Serializable
data object DogListRoute

@Serializable
data class DogDetailRoute(
    val dog: Dog,
    val breedSize: BreedSize,
)