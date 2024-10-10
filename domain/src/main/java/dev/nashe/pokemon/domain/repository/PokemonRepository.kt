package dev.nashe.pokemon.domain.repository

import dev.nashe.pokemon.domain.models.PokemonDetailModel
import dev.nashe.pokemon.domain.models.PokemonModel
import dev.nashe.pokemon.domain.util.Result
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {
    fun getPokemonList(): Flow<Result<List<PokemonModel>>>
    fun getPokemon(id: Int):Flow<Result<PokemonDetailModel>>
}
