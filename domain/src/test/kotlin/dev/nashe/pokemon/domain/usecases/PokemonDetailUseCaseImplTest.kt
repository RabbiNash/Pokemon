package dev.nashe.pokemon.domain.usecases

import dev.nashe.pokemon.domain.models.PokemonDetailModel
import dev.nashe.pokemon.domain.repository.PokemonRepositoryFake
import dev.nashe.pokemon.domain.util.CoroutineTestRule
import dev.nashe.pokemon.domain.util.data
import dev.nashe.pokemon.domain.util.Result
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class PokemonDetailUseCaseImplTest {

    @get:Rule
    var testRule = CoroutineTestRule()

    private val pokemonRepository = PokemonRepositoryFake()
    private lateinit var pokemonDetailUseCase: PokemonDetailUseCaseImpl

    @Before
    fun setup() {
        pokemonDetailUseCase = PokemonDetailUseCaseImpl(pokemonRepository, testRule.testDispatcherProvider)
    }

    @Test
    fun `GIVEN pokemon id WHEN requesting pokemon and no error occurs THEN return success with pokemon domain model`() {
        testRule.runTestScope {
            // arrange
            val pokemonId = 1

            pokemonRepository.stubPokemonDetailList(
                PokemonDetailModel(
                    name = "gotham",
                    height = 7,
                    imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png"

                )
            )

            // act and assert
            pokemonDetailUseCase.start(parameters = pokemonId).collect {
                Assert.assertTrue(it.data?.name == "gotham")
            }
        }
    }

    @Test
    fun `GIVEN pokemon id WHEN requesting pokemon and error occurs THEN return an error`() {
        testRule.runTestScope {
            // arrange
            val pokemonId = 1
            pokemonRepository.hasError = true

            // act and assert
            pokemonDetailUseCase.start(parameters = pokemonId).collect {
                Assert.assertTrue(it is Result.Error)
            }
        }
    }
}
