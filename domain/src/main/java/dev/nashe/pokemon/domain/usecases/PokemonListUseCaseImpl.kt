package dev.nashe.pokemon.domain.usecases

import dev.nashe.pokemon.domain.models.PokemonModel
import dev.nashe.pokemon.domain.repository.PokemonRepository
import dev.nashe.pokemon.domain.usecases.base.UseCase
import dev.nashe.pokemon.domain.util.DefaultDispatcherProvider
import dev.nashe.pokemon.domain.util.DispatcherProvider
import dev.nashe.pokemon.domain.util.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface PokemonListUseCase: UseCase<Unit, List<PokemonModel>>

class PokemonListUseCaseImpl @Inject constructor(
    private val pokemonRepository: PokemonRepository,
    override val dispatcher: DispatcherProvider = DefaultDispatcherProvider()
): PokemonListUseCase {
    override fun execute(parameters: Unit): Flow<Result<List<PokemonModel>>> {
        return pokemonRepository.getPokemonList()
    }
}
