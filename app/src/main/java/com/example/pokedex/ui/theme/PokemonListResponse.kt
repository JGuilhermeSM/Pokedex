package com.example.pokedex.ui.theme
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonListResponse(
    @SerialName("results")
    val results: List<PokemonsResults>
)
@Serializable
data class PokemonsResults(
    @SerialName("name")
    val pokemonName: String
)
