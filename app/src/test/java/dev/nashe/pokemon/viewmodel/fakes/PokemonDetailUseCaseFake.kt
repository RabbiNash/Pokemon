package dev.nashe.pokemon.viewmodel.fakes

import dev.nashe.pokemon.domain.models.PokemonDetailModel
import dev.nashe.pokemon.domain.usecases.PokemonDetailUseCase
import dev.nashe.pokemon.domain.util.DispatcherProvider
import dev.nashe.pokemon.domain.util.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class PokemonDetailUseCaseFake(
    override val dispatcher: DispatcherProvider
): PokemonDetailUseCase {

    var result = flowOf(
        Result.Success(
            PokemonDetailModel(
                name = "gotham",
                height = 7,
                imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png"
            )
        )
    )

    override fun execute(parameters: Int): Flow<Result<PokemonDetailModel>> {
        return result
    }
}
