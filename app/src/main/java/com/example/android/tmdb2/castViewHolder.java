package com.example.android.tmdb2;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class castViewHolder extends RecyclerView.ViewHolder {
    TextView title;
    ImageView imageView;
    View itemView;
    public castViewHolder(View itemView) {
        super(itemView);
        this.itemView=itemView;
        title=itemView.findViewById(R.id.castName);
        imageView=itemView.findViewById(R.id.castimage);
    }
}
