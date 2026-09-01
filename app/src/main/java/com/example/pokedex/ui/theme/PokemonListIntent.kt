package com.example.pokedex.ui.theme

sealed interface PokemonListIntent {

    data object LoadPokemons: PokemonListIntent
}