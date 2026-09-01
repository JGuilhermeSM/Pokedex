package com.example.pokedex.ui.theme

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Preview
@Composable
fun PokemonListScreen(
    viewModel: PokemonListViewModel = viewModel()
) {
    val uiState by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.processIntent(PokemonListIntent.LoadPokemons)
    }

    PokemonListContent(uiState = uiState)
}

@Composable
fun PokemonListContent(
    uiState: PokemonListUiState
) {
    Box(modifier = Modifier.fillMaxSize()) {
        when (uiState) {
            is PokemonListUiState.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            is PokemonListUiState.Error -> {
                Text(
                    text = uiState.message,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(16.dp)
                )
            }
            is PokemonListUiState.Success -> {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(uiState.pokemons) { pokemon ->
                        PokemonItemRow(pokemon = pokemon)
                    }
                }
            }
        }
    }
}

@Composable
fun PokemonItemRow(pokemon: PokemonsResults) {
    Text(
        text = pokemon.pokemonName.replaceFirstChar { it.uppercase() },
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun PokemonListContentPreview() {
    PokedexTheme {
        PokemonListContent(
            uiState = PokemonListUiState.Success(
                pokemons = listOf(
                    PokemonsResults("bulbasaur"),
                    PokemonsResults("ivysaur"),
                    PokemonsResults("venusaur")
                )
            )
        )
    }
}