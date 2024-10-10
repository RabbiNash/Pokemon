package dev.nashe.pokemon.ui.pokemon.list.screen

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import dev.nashe.pokemon.domain.models.PokemonModel
import dev.nashe.pokemon.ui.pokemon.list.state.PokemonListUiState
import dev.nashe.pokemon.ui.pokemon.list.viewmodel.PokemonListViewModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class PokemonListScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var mockViewModel: PokemonListViewModel

    @Before
    fun setUp() {
        mockViewModel = mockk(relaxed = true)

        coEvery { mockViewModel.pokemonListUiState } returns MutableStateFlow(PokemonListUiState.IsLoading)

        val pokemonList = listOf(
            PokemonModel("https://pokeapi.co/api/v2/pokemon/1/", "bulbasaur"),
            PokemonModel("https://pokeapi.co/api/v2/pokemon/2/", "ivysaur")
        )
        coEvery { mockViewModel.pokemonListUiState } returns MutableStateFlow(PokemonListUiState.Present(pokemonList))
    }

    @Test
    fun testLoadingStateDisplaysProgressIndicator() {
        composeTestRule.setContent {
            PokemonListScreen(viewModel = mockViewModel, onItemClick = {})
        }

        composeTestRule.onNodeWithContentDescription("Loading")
            .assertIsDisplayed()
    }

    @Test
    fun testErrorMessageIsDisplayed() {
        coEvery { mockViewModel.pokemonListUiState } returns MutableStateFlow(PokemonListUiState.IsError("Failed to load Pokémon"))

        composeTestRule.setContent {
            PokemonListScreen(viewModel = mockViewModel, onItemClick = {})
        }

        composeTestRule.onNodeWithText("Failed to load Pokémon").assertIsDisplayed()
    }

    @Test
    fun testPokemonListIsDisplayedAndClickNavigatesToDetail() {
        val pokemonList = listOf(
            PokemonModel("https://pokeapi.co/api/v2/pokemon/1/", "bulbasaur"),
            PokemonModel("https://pokeapi.co/api/v2/pokemon/2/", "ivysaur")
        )
        coEvery { mockViewModel.pokemonListUiState } returns MutableStateFlow(PokemonListUiState.Present(pokemonList))

        var selectedPokemonId: Int? = null

        composeTestRule.setContent {
            PokemonListScreen(viewModel = mockViewModel, onItemClick = { selectedPokemonId = it })
        }

        composeTestRule.onNodeWithText("Bulbasaur").assertIsDisplayed()
        composeTestRule.onNodeWithText("Ivysaur").assertIsDisplayed()

        composeTestRule.onNodeWithText("Bulbasaur").performClick()

        assert(selectedPokemonId == 1)
    }
}
