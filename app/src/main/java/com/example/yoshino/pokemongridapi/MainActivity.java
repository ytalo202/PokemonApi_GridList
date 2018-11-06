package com.example.yoshino.pokemongridapi;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.yoshino.pokemongridapi.model.Pokemon;
import com.example.yoshino.pokemongridapi.model.Result;
import com.example.yoshino.pokemongridapi.remoto.APIUtils;
import com.example.yoshino.pokemongridapi.remoto.PokemonService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    private RecyclerView myrecyclerView;
    AdaptadorPokemon mAdapter;
    PokemonService pokemonService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myrecyclerView = findViewById(R.id.RecyclerId);

        pokemonService = APIUtils.getUserService();
        new TaskListPokemon().execute();

    }



    public void getPokeList(){
        Call<Pokemon> call = pokemonService.getData();
        call.enqueue(new Callback<Pokemon>() {
            @Override
            public void onResponse(Call<Pokemon> call, Response<Pokemon> response) {



                final Pokemon data = response.body();
                final List<Result> mList = data.getResults();
                mAdapter = new AdaptadorPokemon(data.getResults(),MainActivity.this);
                //myrecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                myrecyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),3));
                myrecyclerView.setAdapter(mAdapter);

                mAdapter.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(getApplicationContext(),
                                mList.get
                                        (myrecyclerView.getChildAdapterPosition(view))
                                        .getName()+" ¡Yo te Elijo! xD",Toast.LENGTH_SHORT).show();
                    }
                });


            }

            @Override
            public void onFailure(Call<Pokemon> call, Throwable t) {
                Log.e("Error",t.getMessage());
            }
        });


    }


    public class TaskListPokemon extends AsyncTask<Void,Void,Void> {

        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(MainActivity.this, "","Cargando...");
        }

        @Override
        protected Void doInBackground(Void... Void) {



            getPokeList();

            return null;
        }


        @Override
        protected void onPostExecute(Void aVoid) {
            progressDialog.dismiss();



        }

    }
}
