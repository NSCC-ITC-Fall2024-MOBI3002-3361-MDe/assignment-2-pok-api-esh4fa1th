package com.example.pokemonapp.models

data class Pokemon(
    val name: String,
    val types: List<Type>,
    val height: Int,
    val weight: Int,
    val sprites: Sprites
)

data class Type(
    val type: PokemonTypeInfo
)

data class Sprites(
    val front_default: String
)

data class PokemonTypeInfo(
    val name: String
)

