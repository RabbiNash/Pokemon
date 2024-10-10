package dev.nashe.pokemon.ui.pokemon.list.state

import androidx.compose.runtime.Immutable
import dev.nashe.pokemon.domain.models.PokemonModel

@Immutable
sealed class PokemonListUiState {
    data object IsLoading : PokemonListUiState()
    data object IsIdle: PokemonListUiState()

    data class IsError(val message: String) : PokemonListUiState()
    data class Present(val pokemons: List<PokemonModel>) : PokemonListUiState()
}
