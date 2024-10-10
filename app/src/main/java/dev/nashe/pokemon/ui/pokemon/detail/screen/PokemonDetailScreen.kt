package dev.nashe.pokemon.ui.pokemon.detail.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import dev.nashe.pokemon.R
import dev.nashe.pokemon.domain.models.PokemonDetailModel
import dev.nashe.pokemon.ui.pokemon.detail.state.PokemonDetailUiState
import dev.nashe.pokemon.ui.pokemon.detail.viewmodel.PokemonDetailViewModel
import dev.nashe.pokemon.ui.theme.Dimen16Dp
import dev.nashe.pokemon.ui.theme.Dimen24Dp
import dev.nashe.pokemon.ui.theme.Dimen32Dp

@Composable
fun PokemonDetailScreen(
    modifier: Modifier = Modifier,
    pokemonId: Int,
    viewModel: PokemonDetailViewModel = hiltViewModel()
) {

    LaunchedEffect(Unit) {
        viewModel.getPokemonDetail(pokemonId)
    }

    val pokemonDetailUiState = viewModel.pokemonDetailUiState.collectAsState()

    when (val state = pokemonDetailUiState.value) {
        is PokemonDetailUiState.Present -> {
            PokemonDetail(pokemonDetailModel = state.pokemonDetail)
        }
        is PokemonDetailUiState.IsError -> {
            Text(
                modifier = modifier,
                text = "Error",
                style = MaterialTheme.typography.titleLarge
            )
        }
        is PokemonDetailUiState.IsIdle -> {}
        is PokemonDetailUiState.IsLoading -> {
            LinearProgressIndicator(
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
fun PokemonDetail(
    modifier: Modifier = Modifier,
    pokemonDetailModel: PokemonDetailModel
) {
    Column(
        modifier = modifier.padding(Dimen24Dp)
    ) {
        AsyncImage(
            model = pokemonDetailModel.imageUrl,
            modifier = modifier
                .size(400.dp, 300.dp)
                .align(CenterHorizontally),
            contentDescription = "front image"
        )

        HorizontalDivider()

        Text(
            modifier = modifier.padding(PaddingValues(vertical = Dimen16Dp)),
            text = pokemonDetailModel.name.capitalize(),
            style = MaterialTheme.typography.displayMedium
        )

        Text(
            text = "Height: ${pokemonDetailModel.height}",
            style = MaterialTheme.typography.displaySmall
        )
    }
}

@Preview
@Composable
fun PokemonDetailPreview() {
    PokemonDetailScreen(pokemonId = 25)
}
