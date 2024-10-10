package dev.nashe.pokemon.data.models

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonDetailEntity (
    val height: Long,
    val name: String,
    val sprites: Sprites,
)

@Serializable
data class Sprites (
    @SerializedName("front_default")
    val frontDefault: String,
)
