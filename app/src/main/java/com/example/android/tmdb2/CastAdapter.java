package com.example.android.tmdb2;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CastAdapter extends RecyclerView.Adapter<castViewHolder> {
    ArrayList<Cast> Cast;
    Context context;
    CastClickListener listener;
    String baseurl="http://image.tmdb.org/t/p/w342";

    public CastAdapter(ArrayList<Cast> cast, Context context, CastClickListener listener) {
        Cast = cast;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public castViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowlayout=inflater.inflate(R.layout.cast_layout,parent,false);
        return new castViewHolder(rowlayout);
    }

    @Override
    public void onBindViewHolder(@NonNull castViewHolder holder, int position) {
        Cast clickedCast=Cast.get(position);
        holder.title.setText(clickedCast.getName()+ " / " + clickedCast.getCharacter());
        Picasso.get().load(baseurl+clickedCast.getProfilePath()).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return Cast.size();
    }
}
