package dev.nashe.pokemon.domain.usecases

import dev.nashe.pokemon.domain.models.PokemonModel
import dev.nashe.pokemon.domain.repository.PokemonRepositoryFake
import dev.nashe.pokemon.domain.util.CoroutineTestRule
import dev.nashe.pokemon.domain.util.data
import dev.nashe.pokemon.domain.util.Result
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class PokemonListUseCaseImplTest {
    @get:Rule
    var testRule = CoroutineTestRule()

    private val pokemonRepository = PokemonRepositoryFake()
    private lateinit var pokemonListUseCase: PokemonListUseCase

    @Before
    fun setup() {
        pokemonListUseCase = PokemonListUseCaseImpl(pokemonRepository, testRule.testDispatcherProvider)
    }

    @Test
    fun `GIVEN pokemon list request WHEN requesting pokemon and no error occurs THEN return success with pokemon domain model`() {
        testRule.runTestScope {
            // arrange
            pokemonRepository.stubPokemonList(
                listOf(
                    PokemonModel(
                        name = "gotham",
                        url = "https://pokeapi.co/api/v2/pokemon/1/"
                    ),
                    PokemonModel(
                        name = "ivysaur",
                        url = "https://pokeapi.co/api/v2/pokemon/2/"
                    ),
                    PokemonModel(
                        name = "venusaur",
                        url = "https://pokeapi.co/api/v2/pokemon/3/"
                    )
                )
            )

            // act and assert
            pokemonListUseCase.start(parameters = Unit).collect {
                Assert.assertTrue(it.data?.count() == 3)
            }
        }
    }

    @Test
    fun `GIVEN pokemon list request WHEN requesting pokemon and error occurs THEN return an error`() {
        testRule.runTestScope {
            // arrange
            pokemonRepository.hasError = true

            // act and assert
            pokemonListUseCase.start(parameters = Unit).collect {
                Assert.assertTrue(it is Result.Error)
            }
        }
    }
}
