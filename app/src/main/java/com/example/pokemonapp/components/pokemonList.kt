package com.example.pokemonapp.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.pokemonapp.R
import com.example.pokemonapp.models.Pokemon
import com.example.pokemonapp.models.PokemonViewModel

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun pokemonList(pokemonViewModel: PokemonViewModel = viewModel()) {
    var pokemonName by remember { mutableStateOf("") }
    val pokemonDetails by pokemonViewModel.pokemon.collectAsState()
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center // Center the content horizontally
                    ) {
                        AsyncImage(
                            model = R.drawable.pokemon_logo, // Drawable resource ID
                            contentDescription = "Pokemon Logo",
                            modifier = Modifier
                                .size(220.dp)
                        )

                    }
                }
            )
        }



    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(10.dp),
            verticalArrangement = Arrangement.Top
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextField(
                    value = pokemonName,
                    onValueChange = { pokemonName = it },
                    label = { Text("Enter Pokemon Name") },
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White
                    )
                )

                Button(
                    onClick = { pokemonViewModel.fetchPokemonDetailsByName(pokemonName) },
                    modifier = Modifier.align(Alignment.CenterVertically)
                ) {
                    Text("Go")
                }
            }


            LazyColumn(
                contentPadding = PaddingValues(vertical = 16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(pokemonDetails) { pokemon ->
                    PokemonItem(pokemon)
                }
            }
        }
    }
}


@Composable
fun PokemonItem(pokemon: Pokemon) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = pokemon.sprites.front_default,
            contentDescription = "${pokemon.name} image",
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = "${pokemon.name}",
            style = TextStyle(fontSize = 30.sp, fontWeight = FontWeight.Bold)
        )

        Text(
            text = "Types: ${pokemon.types.joinToString { it.type.name }}",
            style = TextStyle(fontSize = 20.sp)
        )

        Text(text = "Height: ${pokemon.height}")

        Text(text = "Weight: ${pokemon.weight}")

    }
}




