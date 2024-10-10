package dev.nashe.pokemon.ui.pokemon.detail.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.nashe.pokemon.domain.models.PokemonModel
import dev.nashe.pokemon.domain.usecases.PokemonDetailUseCase
import dev.nashe.pokemon.ui.pokemon.detail.state.PokemonDetailUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import dev.nashe.pokemon.domain.util.Result
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonDetailViewModel @Inject constructor(
    private val pokemonDetailUseCase: PokemonDetailUseCase,
) : ViewModel() {

    private val _pokemonDetailUiState =
        MutableStateFlow<PokemonDetailUiState>(PokemonDetailUiState.IsLoading)
    val pokemonDetailUiState = _pokemonDetailUiState.asStateFlow()

    fun getPokemonDetail(id: Int) {
        viewModelScope.launch {
            pokemonDetailUseCase
                .start(id)
                .onStart {
                    _pokemonDetailUiState.value = PokemonDetailUiState.IsLoading
                }
                .collect {
                when (it) {
                    is Result.Success -> {
                        _pokemonDetailUiState.value = PokemonDetailUiState.Present(it.data)
                    }

                    is Result.Error -> {
                        _pokemonDetailUiState.value = PokemonDetailUiState.IsError
                    }

                    is Result.Loading -> {
                        _pokemonDetailUiState.value = PokemonDetailUiState.IsLoading
                    }
                }
            }
        }
    }
}
