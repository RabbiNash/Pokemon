package dev.nashe.pokemon.viewmodel.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import dev.nashe.pokemon.ui.pokemon.detail.state.PokemonDetailUiState
import dev.nashe.pokemon.ui.pokemon.detail.viewmodel.PokemonDetailViewModel
import dev.nashe.pokemon.util.CoroutineTestRule
import dev.nashe.pokemon.viewmodel.fakes.PokemonDetailUseCaseFake
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.flow.first
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class PokemonDetailViewModelTest {

    @get:Rule
    var testRule = CoroutineTestRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: PokemonDetailViewModel

    @Before
    fun setup() {
        viewModel =
            PokemonDetailViewModel(
                pokemonDetailUseCase = PokemonDetailUseCaseFake(testRule.testDispatcherProvider)
            )
    }

    @Test
    fun `GIVEN pokemon id WHEN requesting pokemon and no error occurs THEN return success with pokemon domain model`() {
        testRule.runTestScope {
            // arrange
            val pokemonId = 1

            // act
            viewModel.getPokemonDetail(pokemonId)

            // assert
            val pokemonUiState = viewModel.pokemonDetailUiState.first()
            assertTrue(pokemonUiState is PokemonDetailUiState.Present)
            val pokemon = (pokemonUiState as PokemonDetailUiState.Present).pokemonDetail
            assertEquals("gotham", pokemon.name)
        }
    }
}
