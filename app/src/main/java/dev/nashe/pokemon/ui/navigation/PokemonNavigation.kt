package dev.nashe.pokemon.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import dev.nashe.pokemon.ui.pokemon.detail.screen.PokemonDetailScreen
import dev.nashe.pokemon.ui.pokemon.list.screen.PokemonListScreen

fun NavGraphBuilder.pokemonNavigation(
    navHostController: NavHostController
) {
    composable(
        route = "pokemonList"
    ) {
        PokemonListScreen(
            onItemClick = { pokemonId ->
                navHostController.navigate("pokemonDetail/${pokemonId}")
            }
        )
    }
    composable(
        route = "pokemonDetail/{pokemonId}",
        arguments = listOf(navArgument("pokemonId") { type = NavType.IntType })
    ) { backStackEntry ->
        val pokemonId = backStackEntry.arguments?.getInt("pokemonId")
        pokemonId?.let {
            PokemonDetailScreen(pokemonId = pokemonId)
        }
    }
}
