package dev.nashe.pokemon.data.models

import kotlinx.serialization.Serializable

@Serializable
data class PokemonEntity(
    val name: String,
    val url: String
)

@Serializable
data class PokemonResponse(
    val results: List<PokemonEntity>
)
