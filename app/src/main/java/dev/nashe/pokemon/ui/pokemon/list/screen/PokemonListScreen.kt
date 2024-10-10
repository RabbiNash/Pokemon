package dev.nashe.pokemon.ui.pokemon.list.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import dev.nashe.pokemon.domain.models.PokemonModel
import dev.nashe.pokemon.ui.pokemon.list.state.PokemonListUiState
import dev.nashe.pokemon.ui.pokemon.list.viewmodel.PokemonListViewModel
import dev.nashe.pokemon.ui.theme.Dimen16Dp
import dev.nashe.pokemon.ui.theme.Dimen8Dp
import dev.nashe.pokemon.ui.theme.Gray30
import dev.nashe.pokemon.ui.theme.White
import java.util.Locale

@Composable
fun PokemonListScreen(
    modifier: Modifier = Modifier,
    viewModel: PokemonListViewModel = hiltViewModel(),
    onItemClick: (Int) -> Unit,
) {
    LaunchedEffect(Unit) {
        viewModel.getPokemonList()
    }

    val pokemonListUiState = viewModel.pokemonListUiState.collectAsState()

    when (val state = pokemonListUiState.value) {
        is PokemonListUiState.Present -> {
            PokemonList(
                modifier = modifier,
                pokemons = state.pokemons,
                onItemClick = onItemClick
            )
        }

        is PokemonListUiState.IsError -> {
            Text(
                modifier = modifier,
                text = state.message,
                style = MaterialTheme.typography.titleLarge
            )
        }

        is PokemonListUiState.IsIdle -> {}
        is PokemonListUiState.IsLoading -> {
            LinearProgressIndicator(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(Dimen16Dp)
            )
        }
    }
}

@Composable
fun PokemonList(
    modifier: Modifier = Modifier,
    pokemons: List<PokemonModel>,
    onItemClick: (Int) -> Unit
) {
    LazyColumn(
        modifier = modifier
            .background(color = White)
            .padding(Dimen16Dp),
        verticalArrangement = Arrangement.spacedBy(Dimen8Dp)
    ) {
        items(items = pokemons) { pokemon ->
            PokemonItem(
                modifier = modifier,
                pokemonModel = pokemon,
                onItemClick = onItemClick
            )
        }
    }
}

@Composable
fun PokemonItem(
    modifier: Modifier = Modifier,
    pokemonModel: PokemonModel,
    onItemClick: (Int) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onItemClick(getPokemonId(pokemonModel.url)) }
    ) {
        Text(
            modifier = modifier
                .padding(Dimen16Dp),
            text = pokemonModel.name.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() },
            maxLines = 1,
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold
            ),
            color = Color.Black
        )
        HorizontalDivider(color = Gray30)
    }
}

fun getPokemonId(url: String): Int {
    val split = url.split("/")
    val id = split[split.size - 2]
    return id.toInt()
}

@Preview
@Composable
private fun PokemonItemPreview() {
    PokemonList(
        pokemons = listOf(
            PokemonModel("gotham", "https://pokeapi.co/api/v2/pokemon/1/"),
            PokemonModel("gather", "https://pokeapi.co/api/v2/pokemon/1/"),
            PokemonModel("gudam", "https://pokeapi.co/api/v2/pokemon/1/")
        ),
    ) {

    }
}
