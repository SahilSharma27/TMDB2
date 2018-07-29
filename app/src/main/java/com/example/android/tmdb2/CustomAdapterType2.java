package com.example.android.tmdb2;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CustomAdapterType2 extends RecyclerView.Adapter<ItemViewHolder2> {
    ArrayList<Poster> Movies;
    Context context;
    MovieTVClickListener listener;
    String baseurl="http://image.tmdb.org/t/p/w342";
    public CustomAdapterType2(Context context, ArrayList<Poster>Movies,MovieTVClickListener movieClickListener){
        this.listener=movieClickListener;
        this.context=context;
        this.Movies=Movies;

    }
    @NonNull
    @Override
    public ItemViewHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowlayout=inflater.inflate(R.layout.layout_type2,parent,false);
        return new ItemViewHolder2(rowlayout);
    }

    @Override
    public void onBindViewHolder(@NonNull final ItemViewHolder2 holder, int position) {
        Poster postr=Movies.get(position);
       // holder.title.setText(postr.getMovietitle());
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
        return Movies.size();
    }
}
