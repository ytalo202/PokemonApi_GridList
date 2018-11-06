package com.example.yoshino.pokemongridapi;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yoshino.pokemongridapi.model.Pokemon;
import com.example.yoshino.pokemongridapi.model.Result;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CHENAO on 3/07/2017.
 */

public class AdaptadorPokemon
        extends RecyclerView.Adapter<AdaptadorPokemon.ViewHolderPersonajes>
        implements View.OnClickListener{



    private List<Result> mPokemonList;
    private View.OnClickListener listener;

    Context mContext;

    public AdaptadorPokemon(List<Result> listaPersonajes,Context context) {
        this.mPokemonList = listaPersonajes;
        mContext=context;
    }

    @Override
    public ViewHolderPersonajes onCreateViewHolder(ViewGroup parent, int viewType) {
        int layout=0;

            layout=R.layout.item_grid_pokemones;


        View view= LayoutInflater.from(parent.getContext()).inflate(layout,null,false);

        view.setOnClickListener(this);

        return new ViewHolderPersonajes(view);
    }

    @Override
    public void onBindViewHolder(ViewHolderPersonajes holder, int position) {

        holder.etiNombre.setText(mPokemonList.get(position).getName());

        int i =position+1;

        String url = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"+i+".png";
        Picasso.with(mContext).load(url).into(holder.foto);
        Picasso.with(mContext).setIndicatorsEnabled(true);



    }

    @Override
    public int getItemCount() {
        return mPokemonList.size();
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener=listener;
    }

    @Override
    public void onClick(View view) {
        if (listener!=null){
            listener.onClick(view);
        }
    }

    public class ViewHolderPersonajes extends RecyclerView.ViewHolder {

        TextView etiNombre;
        ImageView foto;

        public ViewHolderPersonajes(View itemView) {
            super(itemView);
            etiNombre= (TextView) itemView.findViewById(R.id.idNombre);

            foto= (ImageView) itemView.findViewById(R.id.idImagen);
        }
    }




}
