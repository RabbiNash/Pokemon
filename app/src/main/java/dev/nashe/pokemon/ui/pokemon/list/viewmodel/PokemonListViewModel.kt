package dev.nashe.pokemon.ui.pokemon.list.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.nashe.pokemon.domain.usecases.PokemonListUseCase
import dev.nashe.pokemon.domain.util.Result
import dev.nashe.pokemon.ui.pokemon.list.state.PokemonListUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val pokemonListUseCase: PokemonListUseCase,
) : ViewModel() {

    private val _pokemonListUiState =
        MutableStateFlow<PokemonListUiState>(PokemonListUiState.IsLoading)
    val pokemonListUiState = _pokemonListUiState.asStateFlow()

    fun getPokemonList() {
        viewModelScope.launch {
            pokemonListUseCase
                .start(parameters = Unit)
                .onStart {
                    _pokemonListUiState.value = PokemonListUiState.IsLoading
                }
                .collect {
                when (it) {
                    is Result.Success -> {
                        _pokemonListUiState.value = PokemonListUiState.Present(it.data)
                    }

                    is Result.Error -> {
                        _pokemonListUiState.value = PokemonListUiState.IsError("Error downloading data")
                    }

                    is Result.Loading -> {
                        _pokemonListUiState.value = PokemonListUiState.IsLoading
                    }
                }
            }
        }
    }
}
