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
    val pokemonName: String,
    @SerialName("url")
    val pokemonDescription: String
)

@Serializable
data class Pokemon(
    @SerialName("types")
    val typesList: List<TypesResults>,
    val sprites: SpritesResult
)

@Serializable
data class TypesResults(
    @SerialName("slot")
    val slot: Int,
    @SerialName("type")
    val type: TypeInfoResponse
)

@Serializable
data class TypeInfoResponse(
    @SerialName("name")
    val name: String,
    @SerialName("url")
    val url: String
)

@Serializable
data class SpritesResult(
    @SerialName("versions")
    val spriteVersion: SpriteVersion
)

@Serializable
data class SpriteVersion(
    @SerialName("front_default")
    val spriteVersionFront: String
)