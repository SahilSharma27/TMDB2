package com.example.android.tmdb2;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CustomAdapterType1 extends RecyclerView.Adapter<ItemViewHolder> {
    ArrayList<Poster> Movies;
    Context context;
    MovieTVClickListener listener;
    String baseurl="http://image.tmdb.org/t/p/w780";
    public CustomAdapterType1(Context context, ArrayList<Poster>Movies,MovieTVClickListener movieTVClickListener){
        this.listener=movieTVClickListener;
        this.context=context;
        this.Movies=Movies;

    }
    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowlayout=inflater.inflate(R.layout.layout_type1,parent,false);
        return new ItemViewHolder(rowlayout);
    }

    @Override
    public void onBindViewHolder(@NonNull final ItemViewHolder holder, int position) {
        Poster postr=Movies.get(position);
        holder.title.setText(postr.getMovietitle());
        Picasso.get().load(baseurl+postr.getThumbNailUrl()).into(holder.imageView);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onMovieClicked(v,holder.getAdapterPosition());
            }
        });

    }

    @Override
    public int getItemCount() {
        return  Movies.size();
    }
}
