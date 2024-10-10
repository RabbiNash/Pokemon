package dev.nashe.pokemon.ui.pokemon.detail.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import dev.nashe.pokemon.domain.models.PokemonDetailModel
import dev.nashe.pokemon.ui.pokemon.detail.state.PokemonDetailUiState
import dev.nashe.pokemon.ui.pokemon.detail.viewmodel.PokemonDetailViewModel
import dev.nashe.pokemon.ui.theme.Dimen16Dp
import dev.nashe.pokemon.ui.theme.Dimen24Dp
import dev.nashe.pokemon.ui.theme.Dimen32Dp
import dev.nashe.pokemon.ui.theme.helveticaFontFamily
import dev.nashe.pokemon.ui.theme.pokemonFontFamily
import java.util.Locale

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
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxHeight()
            .background(Color.White)
            .verticalScroll(scrollState)
            .padding(paddingValues = PaddingValues(horizontal = Dimen24Dp))
            .padding(paddingValues = PaddingValues(bottom = Dimen32Dp)),
        horizontalAlignment = CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        AsyncImage(
            model = pokemonDetailModel.imageUrl,
            modifier = modifier
                .size(400.dp, 300.dp)
                .align(CenterHorizontally),
            contentDescription = "front image"
        )

        Text(
            modifier = modifier.padding(PaddingValues(vertical = Dimen16Dp)),
            text = pokemonDetailModel.name.capitalize(Locale.getDefault()),
            style = MaterialTheme.typography.displayMedium,
            fontWeight = FontWeight.Bold,
            fontFamily = helveticaFontFamily
        )

        Text(
            text = "Height",
            style = MaterialTheme.typography.displaySmall,
            fontFamily = pokemonFontFamily,
        )

        Text(
            text = pokemonDetailModel.height.toString(),
            style = MaterialTheme.typography.displaySmall,
            fontFamily = pokemonFontFamily,
        )
    }
}

@Preview
@Composable
fun PokemonDetailPreview() {
    PokemonDetailScreen(pokemonId = 25)
}
