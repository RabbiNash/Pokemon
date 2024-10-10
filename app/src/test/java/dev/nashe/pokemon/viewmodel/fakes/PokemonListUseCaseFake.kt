package dev.nashe.pokemon.viewmodel.fakes

import dev.nashe.pokemon.domain.models.PokemonModel
import dev.nashe.pokemon.domain.usecases.PokemonListUseCase
import dev.nashe.pokemon.domain.util.Result
import dev.nashe.pokemon.domain.util.DispatcherProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class PokemonListUseCaseFake(
    override val dispatcher: DispatcherProvider
): PokemonListUseCase {

    var result = flowOf(
        Result.Success(
            listOf(PokemonModel(name = "gotham", url = "https://pokeapi.co/api/v2/pokemon/1/"))
        )
    )

    override fun execute(parameters: Unit): Flow<Result<List<PokemonModel>>> {
        return result
    }
}
