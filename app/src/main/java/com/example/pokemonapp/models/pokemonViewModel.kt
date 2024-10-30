package com.example.pokemonapp.models

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemonapp.retrofit.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PokemonViewModel : ViewModel() {
    private val _pokemon = MutableStateFlow<List<Pokemon>>(emptyList())
    val pokemon: StateFlow<List<Pokemon>> = _pokemon

    fun fetchPokemonDetailsByName(name: String) {
        viewModelScope.launch {
            try {
                val pokemonDetail = RetrofitInstance.api.getPokemonDetails(name)
                _pokemon.value = listOf(pokemonDetail)
            } catch (e: Exception) {
                Log.e("pokemonViewModel", "Error fetching Pokémon details: ${e.localizedMessage}")
            }
        }
    }
}





