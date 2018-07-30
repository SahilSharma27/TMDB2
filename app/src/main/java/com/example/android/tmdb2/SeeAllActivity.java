package com.example.android.tmdb2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.logging.Handler;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SeeAllActivity extends AppCompatActivity {
    RecyclerView seeAllRecycler;
    CustomAdapterType2 adapter;
    ArrayList<Poster> allMovies = new ArrayList<>();
    ArrayList<Result>allMoviesM=new ArrayList<>();
    ArrayList<TVResult>allTv=new ArrayList<>();
    Intent intent;
    Boolean isScrolling = false;
    int itemsOnScreen, totalItems, scrolledOutItems;
    StaggeredGridLayoutManager gridLayoutManager;
    Long page = 1L, totalPages;
    String type;
    ProgressBar pb;
    String MovieOrTv;

   // Movie obj;
   // String apikey="2f532ba110a3df2d734ee5d96abb504b&language=en-US";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_all);
        Toast.makeText(this, "seeALL", Toast.LENGTH_LONG).show();
        seeAllRecycler = findViewById(R.id.seeAllRecyclerView);
        pb=findViewById(R.id.seeAllPb);
        intent = getIntent();
        type = intent.getStringExtra("type");
        totalPages = intent.getLongExtra("totalPages", 1);
         MovieOrTv=intent.getStringExtra("MovieTV");
        adapter = new CustomAdapterType2(SeeAllActivity.this, allMovies, new MovieTVClickListener() {
            @Override
            public void onMovieClicked(View view, int position) {

                    Result clickedMov = allMoviesM.get(position);
                    Bundle bundle = new Bundle();
                    Long id = clickedMov.getId();
                    bundle.putLong("id", id);
                    bundle.putString("title", clickedMov.getOriginalTitle());
                    bundle.putString("backPst", clickedMov.getBackdropPath());
                    bundle.putString("overview", clickedMov.getOverview());
                    bundle.putDouble("rating", clickedMov.getVoteAverage());
                    bundle.putString("poster", clickedMov.getPosterPath());
                    bundle.putString("release_date", clickedMov.getReleaseDate());
                    bundle.putString("type", MovieOrTv);
                    Intent intent1 = new Intent(SeeAllActivity.this, DetailsActivity.class);
                    intent1.putExtras(bundle);
                    startActivity(intent1);
                }


        });
        seeAllRecycler.setAdapter(adapter);
        seeAllRecycler.setItemAnimator(new DefaultItemAnimator());
        gridLayoutManager  = new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.HORIZONTAL);
        seeAllRecycler.setLayoutManager(gridLayoutManager);
        fetchData(page) ;



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
//                public void onScrolled(RecyclerView recycler View, int dx, int dy) {
//                    super.onScrolled(recyclerView, dx, dy);
//                    itemsOnScreen=gridLayoutManager.getChildCount();
//                    totalItems=gridLayoutManager.getItemCount();
//                    scrolledOutItems=gridLayoutManager.findFirstVisibleItemPosition();
//
//                    if(isScrolling&& (itemsOnScreen+scrolledOutItems==totalItems)&& page<totalPages){
//                        page++;
//                        isScrolling=false;
//                        fetchData(page);
//                    }
//
//                }
//            });
    }
        void fetchData(Long currentpage){
            if(currentpage>10){
                return;
            }


            Call<Movie> call = ApiClient.getMovieTVServices().getALLMovies(MovieOrTv,type,currentpage);
            call.enqueue(new Callback<Movie>() {
                @Override
                public void onResponse(Call<Movie> call, Response<Movie> response) {
                    Log.d("Url generated",response.toString());
                     Movie obj = response.body();
                    for (int i = 0; i < obj.getResults().size(); i++) {

                        Poster poster = new Poster(obj.getResults().get(i).getOriginalTitle(), obj.getResults().get(i).getPosterPath());
                        allMovies.add(poster);
                    }
                    allMoviesM.addAll(obj.getResults());
                    adapter.notifyDataSetChanged();

                    pb.setVisibility(View.GONE);

                }

                @Override
                public void onFailure(Call<Movie> call, Throwable t) {

                }
            });




            fetchData(currentpage+1);
        }


    }



