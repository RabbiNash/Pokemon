package dev.nashe.pokemon.data.api

import dev.nashe.pokemon.data.models.PokemonDetailEntity
import dev.nashe.pokemon.data.models.PokemonEntity
import dev.nashe.pokemon.data.models.PokemonResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonService {
    @GET("pokemon")
    suspend fun getPokemons(): PokemonResponse

    @GET("pokemon/{id}")
    suspend fun getPokemon(@Path("id") id: Int): PokemonDetailEntity
}
