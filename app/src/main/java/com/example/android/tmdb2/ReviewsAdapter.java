package com.example.android.tmdb2;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class ReviewsAdapter extends RecyclerView.Adapter<reviewsViewHolder> {
    Context context;
    ArrayList<Result> list;
    reviewClickListener listener;

    public ReviewsAdapter(Context context,ArrayList<Result> list,reviewClickListener listener){
        this.listener=listener;
        this.context=context;
        this.list=list;

    }
    @NonNull
    @Override
    public reviewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowlayout=inflater.inflate(R.layout.layout_type3,parent,false);
        return new reviewsViewHolder(rowlayout);
    }

    @Override
    public void onBindViewHolder(@NonNull final reviewsViewHolder holder, int position) {
        Result review=list.get(position);
        holder.textView1.setText(review.getAuthor());
        holder.textView2.setText(review.getContent());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onMovieClicked(v,holder.getAdapterPosition());
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
