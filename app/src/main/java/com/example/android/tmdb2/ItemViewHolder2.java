package com.example.android.tmdb2;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ItemViewHolder2 extends RecyclerView.ViewHolder {
    TextView title;
    ImageView imageView;
    View itemView;

    public ItemViewHolder2(@NonNull View itemView) {
        super(itemView);
        this.itemView = itemView;
        title = itemView.findViewById(R.id.movietitle);
        imageView = itemView.findViewById(R.id.imageview);
    }
}
