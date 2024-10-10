package dev.nashe.pokemon.ui.pokemon.list.screen

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import dev.nashe.pokemon.domain.models.PokemonModel
import org.junit.Rule
import org.junit.Test

class PokemonListTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testPokemonListDisplaysItems() {
        val pokemonList = listOf(
            PokemonModel(url = "https://pokeapi.co/api/v2/pokemon/1/", name = "bulbasaur"),
            PokemonModel(url = "https://pokeapi.co/api/v2/pokemon/2/", name = "ivysaur")
        )

        composeTestRule.setContent {
            PokemonList(pokemons = pokemonList, onItemClick = {})
        }

        composeTestRule.onNodeWithText("Bulbasaur").assertIsDisplayed()
        composeTestRule.onNodeWithText("Ivysaur").assertIsDisplayed()
    }

    @Test
    fun testPokemonItemClick() {
        val pokemonList = listOf(
            PokemonModel(url = "https://pokeapi.co/api/v2/pokemon/1/", name = "bulbasaur"),
            PokemonModel(url = "https://pokeapi.co/api/v2/pokemon/2/", name = "ivysaur")
        )

        var selectedPokemonId: Int? = null

        composeTestRule.setContent {
            PokemonList(
                pokemons = pokemonList,
                onItemClick = { selectedPokemonId = it }
            )
        }

        composeTestRule.onNodeWithText("Bulbasaur").performClick()

        assert(selectedPokemonId == 1)
    }
}
