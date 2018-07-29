package com.example.android.tmdb2;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class MoviesFragment extends Fragment implements View.OnClickListener {
    RecyclerView recyclerView1,recyclerView2,recyclerView3;
    CustomAdapterType1 adapter1;
    CustomAdapterType2 adapter2,adapter3;
    ArrayList<Poster>upcomingMoviesPoster=new ArrayList<>();
    ArrayList<Poster>PopularMoviesPoster=new ArrayList<>();
    ArrayList<Poster>topMoviesPoster=new ArrayList<>();
    Movie uMovie,pMovie,tMovie;
    ProgressBar pb;
    Intent intent1,intent2,intent3;
    TextView uSeeAll,pSeeAll,tSeeAll;
    Long uTotalPages,pTotalPages,tTotalPages;


    public MoviesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View output= inflater.inflate(R.layout.fragment_movies, container, false);
        recyclerView1=output.findViewById(R.id.recycleViewUpcoming);
        recyclerView2=output.findViewById(R.id.popularRecycleView);
        recyclerView3=output.findViewById(R.id.TopRecycleView);
        pb=output.findViewById(R.id.pb);
        pSeeAll=output.findViewById(R.id.popularseeAll);
       // uSeeAll=output.findViewById(R.id.upcomingSeeAll);
        tSeeAll=output.findViewById(R.id.topRatedseeAll);
       // uSeeAll.setOnClickListener(this);
        pSeeAll.setOnClickListener(this);
        tSeeAll.setOnClickListener(this);



        adapter1=new CustomAdapterType1(getContext(), upcomingMoviesPoster, new MovieTVClickListener() {
            @Override
            public void onMovieClicked(View view, int position) {
                Result clickedMov=uMovie.getResults().get(position);
                Bundle bundle=new Bundle();
                bundle.putLong("id",clickedMov.getId());
                bundle.putString("title",clickedMov.getOriginalTitle());
                bundle.putString("backPst",clickedMov.getBackdropPath());
                bundle.putString("overview",clickedMov.getOverview());
                bundle.putDouble("rating",clickedMov.getVoteAverage());
                bundle.putString("poster",clickedMov.getPosterPath());
                bundle.putString("release_date",clickedMov.getReleaseDate());
                intent1=new Intent(getActivity(),DetailsActivity.class);
                intent1.putExtras(bundle);
                startActivity(intent1);
            }
        });
        recyclerView1.setAdapter(adapter1);

        recyclerView1.setItemAnimator(new DefaultItemAnimator());
        LinearLayoutManager UlayoutManager=new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView1.setLayoutManager(UlayoutManager);
        fetchData(0);



        adapter2=new CustomAdapterType2(getContext(), PopularMoviesPoster, new MovieTVClickListener() {
            @Override
            public void onMovieClicked(View view, int position) {
                Result clickedMov=pMovie.getResults().get(position);
                Bundle bundle=new Bundle();
                bundle.putLong("id",clickedMov.getId());
                bundle.putString("title",clickedMov.getOriginalTitle());
                bundle.putString("backPst",clickedMov.getBackdropPath());
                bundle.putString("overview",clickedMov.getOverview());
                bundle.putDouble("rating",clickedMov.getVoteAverage());
                bundle.putString("poster",clickedMov.getPosterPath());
                bundle.putString("release_date",clickedMov.getReleaseDate());
                intent2=new Intent(getActivity(),DetailsActivity.class);
                intent2.putExtras(bundle);
                startActivity(intent2);
            }
        });
        recyclerView2.setAdapter(adapter2);

        recyclerView2.setItemAnimator(new DefaultItemAnimator());
        LinearLayoutManager playoutManager=new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView2.setLayoutManager(playoutManager);
        fetchData(1);

        adapter3=new CustomAdapterType2(getContext(), topMoviesPoster, new MovieTVClickListener() {
            @Override
            public void onMovieClicked(View view, int position) {
                Result clickedMov=tMovie.getResults().get(position);
                Bundle bundle=new Bundle();
                bundle.putLong("id",clickedMov.getId());
                bundle.putString("title",clickedMov.getOriginalTitle());
                bundle.putString("backPst",clickedMov.getBackdropPath());
                bundle.putString("overview",clickedMov.getOverview());
                bundle.putDouble("rating",clickedMov.getVoteAverage());
                bundle.putString("poster",clickedMov.getPosterPath());
                bundle.putString("release_date",clickedMov.getReleaseDate());
                intent3=new Intent(getActivity(),DetailsActivity.class);
                intent3.putExtras(bundle);
                startActivity(intent3);


            }
        });
        recyclerView3.setAdapter(adapter3);
        recyclerView3.setItemAnimator(new DefaultItemAnimator());
        LinearLayoutManager tlinearLayoutManager=new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        recyclerView3.setLayoutManager(tlinearLayoutManager);
        fetchData(2);





        return output;
    }
    void fetchData(int code){
        if(code==0){
            Call<Movie> call=ApiClient.getMovieTVServices().getUpcomingMovies();
            call.enqueue(new Callback<Movie>() {
                @Override
                public void onResponse(Call<Movie> call, Response<Movie> response) {
                    uMovie=response.body();
                    uTotalPages=uMovie.getTotalPages();
                    for(int i=0;i<uMovie.getResults().size();i++){
                        Poster newPost=new Poster(uMovie.getResults().get(i).getOriginalTitle(),uMovie.getResults().get(i).getBackdropPath());
                        upcomingMoviesPoster.add(i,newPost);

                    }
                    adapter1.notifyDataSetChanged();



                }

                @Override
                public void onFailure(Call<Movie> call, Throwable t) {

                }
            });
        }
        if(code==1){
            Call<Movie> call=ApiClient.getMovieTVServices().getPopularMovies();
            call.enqueue(new Callback<Movie>() {
                @Override
                public void onResponse(Call<Movie> call, Response<Movie> response) {
                    pMovie=response.body();
                    pTotalPages=pMovie.getTotalPages();
                    for(int i=0;i<pMovie.getResults().size();i++){
                        Poster newPost=new Poster(pMovie.getResults().get(i).getOriginalTitle(),pMovie.getResults().get(i).getPosterPath());
                        PopularMoviesPoster.add(i,newPost);
                    }
                    adapter2.notifyDataSetChanged();

                }

                @Override
                public void onFailure(Call<Movie> call, Throwable t) {

                }
            });
        }
        if(code==2){
            Call<Movie> call=ApiClient.getMovieTVServices().getTopRatedMovies();
            call.enqueue(new Callback<Movie>() {
                @Override
                public void onResponse(Call<Movie> call, Response<Movie> response) {
                    tMovie=response.body();
                    tTotalPages=tMovie.getTotalPages();
                    for(int i=0;i<tMovie.getResults().size();i++){
                        Poster newPost=new Poster(tMovie.getResults().get(i).getOriginalTitle(),tMovie.getResults().get(i).getPosterPath());
                        topMoviesPoster.add(i,newPost);
                    }
                    adapter3.notifyDataSetChanged();

                }

                @Override
                public void onFailure(Call<Movie> call, Throwable t) {

                }
            });
        }
        pb.setVisibility(View.GONE);
    }
    void fetchALL(String type){
        Intent intent=new Intent(getContext(),SeeAllActivity.class);
        if(type =="popular"){
            intent.putExtra("type",type);
        }
        else if(type=="upcoming"){
            intent.putExtra("type",type);
            }
            else if(type=="topRated"){
            intent.putExtra("type",type);
        }
        startActivity(intent);
    }


    @Override
    public void onClick(View v) {

        Toast.makeText(getContext(),"Clicked",Toast.LENGTH_LONG).show();
        int id=v.getId();
        Intent intent=new Intent(getContext(),SeeAllActivity.class);
        String type=null;
        if(id==R.id.popularseeAll){
            type="popular";
            intent.putExtra("totalPages",pTotalPages);
        }
//        else if(id==R.id.upcomingSeeAll){
//            type="upcoming";
//            intent.putExtra("totalPages",uTotalPages);
//
//        }
           else if(id==R.id.topRatedseeAll){
            type="top_rated";
            intent.putExtra("totalPages",tTotalPages);
        }

        intent.putExtra("type",type);
        startActivity(intent);


    }
}
