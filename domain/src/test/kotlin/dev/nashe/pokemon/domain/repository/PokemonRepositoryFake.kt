package dev.nashe.pokemon.domain.repository

import dev.nashe.pokemon.domain.models.PokemonDetailModel
import dev.nashe.pokemon.domain.models.PokemonModel
import dev.nashe.pokemon.domain.util.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PokemonRepositoryFake: PokemonRepository {
    var hasError = false

    private var pokemonList: List<PokemonModel> = listOf()
    private var pokemonDetail: PokemonDetailModel? = null

    override fun getPokemonList(): Flow<Result<List<PokemonModel>>> {
        return flow {
            if (hasError) {
                emit(Result.Error(Error("An error occurred")))
            } else {
                emit(Result.Success(pokemonList))
            }
        }
    }

    override fun getPokemon(id: Int): Flow<Result<PokemonDetailModel>> {
        return flow {
            if (hasError) {
                emit(Result.Error(Error("An error occurred")))
            } else {
                pokemonDetail?.let {
                    emit(Result.Success(it))
                }
            }
        }
    }

    fun stubPokemonList(pokemonList: List<PokemonModel>) {
        this.pokemonList = pokemonList
    }

    fun stubPokemonDetailList(pokemonDetail: PokemonDetailModel) {
        this.pokemonDetail = pokemonDetail
    }
}
