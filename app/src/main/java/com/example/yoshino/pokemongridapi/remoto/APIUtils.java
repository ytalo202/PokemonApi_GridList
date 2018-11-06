package com.example.yoshino.pokemongridapi.remoto;

public class APIUtils {

    private APIUtils(){

    }
    public static  String API_URL = "https://pokeapi.co/api/v2/";


    public static PokemonService getUserService(){
        return  RetrofitClient.getClient(API_URL).create(PokemonService.class);
    }
}
