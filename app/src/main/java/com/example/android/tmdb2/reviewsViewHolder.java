package com.example.android.tmdb2;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class reviewsViewHolder extends RecyclerView.ViewHolder {
    TextView textView1,textView2;

    View itemView;
    public reviewsViewHolder(View itemView) {
        super(itemView);
        this.itemView=itemView;
        textView1=itemView.findViewById(R.id.authorName);
        textView2=itemView.findViewById(R.id.content);

    }
}
