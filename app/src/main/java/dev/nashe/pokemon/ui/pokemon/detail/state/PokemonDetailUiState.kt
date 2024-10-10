package dev.nashe.pokemon.ui.pokemon.detail.state

import androidx.compose.runtime.Immutable
import dev.nashe.pokemon.domain.models.PokemonDetailModel

@Immutable
sealed class PokemonDetailUiState {
    data object IsLoading : PokemonDetailUiState()
    data object IsError : PokemonDetailUiState()
    data object IsIdle: PokemonDetailUiState()

    data class Present(val pokemonDetail: PokemonDetailModel) : PokemonDetailUiState()
}
