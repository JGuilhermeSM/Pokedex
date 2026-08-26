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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun PokemonListScreen(){
    var pokemonList by remember { mutableStateOf<List<PokemonsResults>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }
    var errorMessage by remember { mutableStateOf<String?>(null)}

    LaunchedEffect(Unit) {
        try {
            val response = PokeApiInstance.api.getPokemonList()
            pokemonList = response.results
        } catch (e: Exception) {
            errorMessage = "Falha ao carregar Pokémons: ${e.localizedMessage}"
        } finally {
            isLoading = false
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        when {
            isLoading -> {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            errorMessage != null -> {
                Text(
                    text = errorMessage ?: "Erro Desconhecido",
                    modifier = Modifier.align(Alignment.Center).padding(16.dp)
                )
            }
            else -> {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(pokemonList) { pokemon ->
                        PokemonItemRow(pokemon = pokemon)
                    }
                }
            }
        }
    }
}

@Composable
fun PokemonItemRow(pokemon: PokemonsResults){
    Text(
        text = pokemon.pokemonName.replaceFirstChar { it.uppercase() },
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    )
}