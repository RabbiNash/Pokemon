package dev.nashe.pokemon.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost


@Composable
fun PokemonNavHost(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    startDestination: String,
) {
    NavHost(
        navController = navHostController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        pokemonNavigation(navHostController)
    }
}
