package dev.nashe.pokemon.domain.usecases

import dev.nashe.pokemon.domain.models.PokemonDetailModel
import dev.nashe.pokemon.domain.repository.PokemonRepository
import dev.nashe.pokemon.domain.usecases.base.UseCase
import dev.nashe.pokemon.domain.util.DefaultDispatcherProvider
import dev.nashe.pokemon.domain.util.DispatcherProvider
import dev.nashe.pokemon.domain.util.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface PokemonDetailUseCase: UseCase<Int, PokemonDetailModel>

class PokemonDetailUseCaseImpl @Inject constructor(
    private val pokemonRepository: PokemonRepository,
    override val dispatcher: DispatcherProvider = DefaultDispatcherProvider()
): PokemonDetailUseCase {
    override fun execute(parameters: Int): Flow<Result<PokemonDetailModel>> {
        return pokemonRepository.getPokemon(parameters)
    }
}
