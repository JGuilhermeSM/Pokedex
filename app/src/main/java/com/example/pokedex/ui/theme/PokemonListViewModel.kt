package com.example.pokedex.ui.theme

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PokemonListViewModel : ViewModel() {

    private val _state = MutableStateFlow<PokemonListUiState>(PokemonListUiState.Loading)
    val state: StateFlow<PokemonListUiState> = _state.asStateFlow()

    fun processIntent(intent: PokemonListIntent){
        when (intent) {
            is PokemonListIntent.LoadPokemons -> fetchPokemons()
        }
    }

    private fun fetchPokemons(){
        viewModelScope.launch {
            _state.value = PokemonListUiState.Loading

            try {
                val response = PokeApiInstance.api.getPokemonList()
                _state.value = PokemonListUiState.Success(response.results)
            } catch (e: Exception) {
                _state.value = PokemonListUiState.Error(
                    "Falha ao localizar pokemons $e.localizedMessage")
            }
        }
    }
}