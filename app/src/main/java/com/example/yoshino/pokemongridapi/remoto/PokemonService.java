package com.example.yoshino.pokemongridapi.remoto;
import com.example.yoshino.pokemongridapi.model.Pokemon;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PokemonService {

    @GET("pokemon/")
    Call<Pokemon> getData();





}
