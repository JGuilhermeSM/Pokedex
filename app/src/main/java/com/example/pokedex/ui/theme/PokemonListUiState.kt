package com.example.pokedex.ui.theme

sealed interface PokemonListUiState {

    data object Loading: PokemonListUiState
    data class Success(val pokemons: List<PokemonsResults>): PokemonListUiState
    data class Error(val message: String): PokemonListUiState
}
