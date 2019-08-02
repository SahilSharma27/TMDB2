package com.example.android.tmdb2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchResultActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    CustomAdapterType2 adapter;
    ArrayList<Poster> searched=new ArrayList<>();
    String query;
    Search obj;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        Intent intent=getIntent();
        query=intent.getStringExtra("Query");
        recyclerView=findViewById(R.id.searchRecyclerView);
        textView=findViewById(R.id.results);
        adapter=new CustomAdapterType2(this, searched, new MovieTVClickListener() {
            @Override
            public void onMovieClicked(View view, int position) {
                //todo
            }
        });
        recyclerView.setAdapter(adapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        GridLayoutManager layoutManager=new GridLayoutManager(this,3);
        recyclerView.setLayoutManager(layoutManager);
        fetchData();


    }
    void fetchData(){
        Call<Search> call=ApiClient.getMovieTVServices().getSearch(query,1,false);
        call.enqueue(new Callback<Search>() {
            @Override
            public void onResponse(Call<Search> call, Response<Search> response) {
                Log.d("search",response.toString());
                obj=response.body();
                if(obj.getResults().size()==0){
                    textView.setText(" Sorry! No Results Found");
                }
                for(int i=0;i<obj.getResults().size();i++){

                    Poster poster=new Poster(obj.getResults().get(i).getOriginalTitle(),obj.getResults().get(i).getPosterPath());
                    searched.add(poster);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<Search> call, Throwable t) {

            }
        });

    }
}
