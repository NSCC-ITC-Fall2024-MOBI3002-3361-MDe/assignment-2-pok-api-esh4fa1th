package com.example.pokemonapp.retrofit

import com.example.pokemonapp.models.Pokemon
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface apiService {
    @GET("pokemon/{name}")
    suspend fun getPokemonDetails(@Path("name") name: String): Pokemon
}

object RetrofitInstance {
    val api: apiService by lazy {
        Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(apiService::class.java)
    }
}