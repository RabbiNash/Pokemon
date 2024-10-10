package dev.nashe.pokemon.viewmodel.list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import dev.nashe.pokemon.ui.pokemon.list.state.PokemonListUiState
import dev.nashe.pokemon.ui.pokemon.list.viewmodel.PokemonListViewModel
import dev.nashe.pokemon.util.CoroutineTestRule
import dev.nashe.pokemon.viewmodel.fakes.PokemonListUseCaseFake
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.flow.first
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class PokemonListViewModelTest {

    @get:Rule
    var testRule = CoroutineTestRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: PokemonListViewModel

    @Before
    fun setup() {
        viewModel =
            PokemonListViewModel(
                pokemonListUseCase = PokemonListUseCaseFake(testRule.testDispatcherProvider)
            )
    }

    @Test
    fun `GIVEN pokemon list request WHEN requesting pokemon and no error occurs THEN return success with pokemon domain model`() {
        testRule.runTestScope {
            // act
            viewModel.getPokemonList()

            // assert
            val pokemonUiState = viewModel.pokemonListUiState.first()
            assertTrue(pokemonUiState is PokemonListUiState.Present)
            val pokemonList = (pokemonUiState as PokemonListUiState.Present).pokemons
            assertEquals("https://pokeapi.co/api/v2/pokemon/1/", pokemonList.first().url)
        }
    }
}
