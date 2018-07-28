package com.example.android.tmdb2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Toast;

import java.util.ArrayList;

public class SeeAllActivity extends AppCompatActivity {
    RecyclerView seeAllRecycler;
    CustomAdapterType2 adapter;
    ArrayList<Poster> allMovies = new ArrayList<>();
    Intent intent;
    Boolean isScrolling = false;
    int itemsOnScreen, totalItems, scrolledOutItems;
    GridLayoutManager gridLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_all);
        Toast.makeText(this,"seeALL",Toast.LENGTH_LONG).show();
//        seeAllRecycler.setAdapter(adapter);
//        seeAllRecycler.setItemAnimator(new DefaultItemAnimator());
//        gridLayoutManager=new GridLayoutManager(this,3);
//        seeAllRecycler.setLayoutManager(gridLayoutManager);
//
//        }
//        public void fetchData(String type){
//        if(type=="popular"){
//            seeAllRecycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
//                @Override
//                public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//                    super.onScrollStateChanged(recyclerView, newState);
//                    if(newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){
//                        isScrolling=true;
//                    }
//
//                }
//
//                @Override
//                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                    super.onScrolled(recyclerView, dx, dy);
//                    itemsOnScreen=gridLayoutManager.getChildCount();
//                    totalItems=gridLayoutManager.getItemCount();
//                    scrolledOutItems=gridLayoutManager.findFirstVisibleItemPosition();
//
//                    if(isScrolling&& (itemsOnScreen+scrolledOutItems==totalItems)&& page<totalPages)
//
//                }
//            });
//
//
//
//        }
//        }
    }
}
