package dev.nashe.pokemon.data.repositories

import dev.nashe.pokemon.data.api.PokemonService
import dev.nashe.pokemon.data.mapper.PokemonResponseDetailMapper
import dev.nashe.pokemon.data.mapper.PokemonResponseMapper
import dev.nashe.pokemon.domain.models.PokemonDetailModel
import dev.nashe.pokemon.domain.models.PokemonModel
import dev.nashe.pokemon.domain.repository.PokemonRepository
import dev.nashe.pokemon.domain.util.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val pokemonService: PokemonService,
    private val pokemonResponseMapper: PokemonResponseMapper,
    private val pokemonResponseDetailMapper: PokemonResponseDetailMapper,
): PokemonRepository {
    override fun getPokemonList(): Flow<Result<List<PokemonModel>>> {
        return flow {
            try {
                val result = pokemonService.getPokemons().results
                emit(Result.Success(pokemonResponseMapper.mapToDomainList(result)))
            } catch (e: Exception) {
                emit(Result.Error(e))
            }
        }
    }

    override fun getPokemon(id: Int): Flow<Result<PokemonDetailModel>> {
        return flow {
            try {
                emit(Result.Success(pokemonResponseDetailMapper.mapToDomain(pokemonService.getPokemon(id))))
            } catch (e: Exception) {
                emit(Result.Error(e))
            }
        }
    }
}
