package com.example.android.tmdb2;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class TVFragment extends Fragment implements View.OnClickListener{
    RecyclerView recyclerView1,recyclerView2,recyclerView3;
    CustomAdapterType1 adapter1;
    CustomAdapterType2 adapter2,adapter3;
    ArrayList<Poster> tvOnAir=new ArrayList<>();
    ArrayList<Poster> tvPopular=new ArrayList<>();
    ArrayList<Poster> tvTopRated=new ArrayList<>();
    TextView oSeeAll,pSeeAll,tSeeAll;
    TV oTV,pTV,tTV;



    public TVFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View output= inflater.inflate(R.layout.fragment_tv, container, false);
       recyclerView1=output.findViewById(R.id.recycleViewTVOnAir);
       recyclerView2=output.findViewById(R.id.popularTVRecycleView);
       recyclerView3=output.findViewById(R.id.topRatedTVRecycleView);
       pSeeAll=output.findViewById(R.id.popularTVseeAll);
       tSeeAll=output.findViewById(R.id.topRatedTVseeAll);
       pSeeAll.setOnClickListener(this);
       tSeeAll.setOnClickListener(this);

       adapter1=new CustomAdapterType1(getContext(), tvOnAir, new MovieTVClickListener() {
           @Override
           public void onMovieClicked(View view, int position) {
               TVResult clickedMov=oTV.getResults().get(position);
               Bundle bundle=new Bundle();
               bundle.putLong("id",clickedMov.getId());
               bundle.putString("title",clickedMov.getOriginalTitle());
               bundle.putString("backPst",clickedMov.getBackdropPath());
               bundle.putString("overview",clickedMov.getOverview());
               bundle.putDouble("rating",clickedMov.getVoteAverage());
               bundle.putString("poster",clickedMov.getPosterPath());
               bundle.putString("release_date",clickedMov.getReleaseDate());
               bundle.putString("type","tv");
               Intent intent1=new Intent(getActivity(),DetailsActivity.class);
               intent1.putExtras(bundle);
               startActivity(intent1);

           }
       });
       recyclerView1.setAdapter(adapter1);
        recyclerView1.setItemAnimator(new DefaultItemAnimator());
        SnapHelper snapHelper=new LinearSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView1);
        LinearLayoutManager olayoutManager=new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView1.setLayoutManager(olayoutManager);
        fetchData(0);

        adapter2=new CustomAdapterType2(getContext(), tvPopular, new MovieTVClickListener() {
            @Override
            public void onMovieClicked(View view, int position) {
                TVResult clickedMov=pTV.getResults().get(position);
                Bundle bundle=new Bundle();
                bundle.putLong("id",clickedMov.getId());
                bundle.putString("title",clickedMov.getOriginalTitle());
                bundle.putString("backPst",clickedMov.getBackdropPath());
                bundle.putString("overview",clickedMov.getOverview());
                bundle.putDouble("rating",clickedMov.getVoteAverage());
                bundle.putString("poster",clickedMov.getPosterPath());
                bundle.putString("release_date",clickedMov.getReleaseDate());
                bundle.putString("type","tv");
                 Intent intent1=new Intent(getActivity(),DetailsActivity.class);
                intent1.putExtras(bundle);
                startActivity(intent1);

            }
        });
        recyclerView2.setAdapter(adapter2);
        recyclerView2.setItemAnimator(new DefaultItemAnimator());
        LinearLayoutManager playoutManager=new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView2.setLayoutManager(playoutManager);
        fetchData(1);

        adapter3=new CustomAdapterType2(getContext(), tvTopRated, new MovieTVClickListener() {
            @Override
            public void onMovieClicked(View view, int position) {
                TVResult clickedMov=tTV.getResults().get(position);
                Bundle bundle=new Bundle();
                bundle.putLong("id",clickedMov.getId());
                bundle.putString("title",clickedMov.getOriginalTitle());
                bundle.putString("backPst",clickedMov.getBackdropPath());
                bundle.putString("overview",clickedMov.getOverview());
                bundle.putDouble("rating",clickedMov.getVoteAverage());
                bundle.putString("poster",clickedMov.getPosterPath());
                bundle.putString("release_date",clickedMov.getReleaseDate());
                bundle.putString("type","tv");
                Intent intent1=new Intent(getActivity(),DetailsActivity.class);
                intent1.putExtras(bundle);
                startActivity(intent1);
            }
        });
        recyclerView3.setAdapter(adapter3);
        recyclerView3.setItemAnimator(new DefaultItemAnimator());

        LinearLayoutManager tlayoutManager=new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView3.setLayoutManager(tlayoutManager);
        fetchData(2);






       return output;
    }
    void fetchData(int code){
        if(code==0) {
            Call<TV> call = ApiClient.getMovieTVServices().getTVonAir();
            call.enqueue(new Callback<TV>() {
                @Override
                public void onResponse(Call<TV> call, Response<TV> response) {
                    oTV = response.body();
                    for (int i = 0; i < oTV.getResults().size(); i++) {
                        Poster newPost = new Poster(oTV.getResults().get(i).getOriginalTitle(), oTV.getResults().get(i).getBackdropPath());
                        tvOnAir.add(i, newPost);

                    }
                    adapter1.notifyDataSetChanged();


                }

                @Override
                public void onFailure(Call<TV> call, Throwable t) {

                }
            });
        }
        else if(code==1) {
            Call<TV> call = ApiClient.getMovieTVServices().getPopularTV();
            call.enqueue(new Callback<TV>() {
                @Override
                public void onResponse(Call<TV> call, Response<TV> response) {
                    pTV = response.body();
                    for (int i = 0; i < pTV.getResults().size(); i++) {
                        Poster newPost = new Poster(pTV.getResults().get(i).getOriginalTitle(), pTV.getResults().get(i).getPosterPath());
                        tvPopular.add(i, newPost);

                    }
                    adapter2.notifyDataSetChanged();


                }

                @Override
                public void onFailure(Call<TV> call, Throwable t) {

                }
            });
        }
            else if(code==2){
            Call<TV> call = ApiClient.getMovieTVServices().getTvTopRated();
            call.enqueue(new Callback<TV>() {
                @Override
                public void onResponse(Call<TV> call, Response<TV> response) {
                    tTV = response.body();
                    for (int i = 0; i < tTV.getResults().size(); i++) {
                        Poster newPost = new Poster(tTV.getResults().get(i).getOriginalTitle(), tTV.getResults().get(i).getPosterPath());
                        tvTopRated.add(i, newPost);

                    }
                    adapter3.notifyDataSetChanged();


                }

                @Override
                public void onFailure(Call<TV> call, Throwable t) {

                }
            });


            }


    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        Intent intent=new Intent(getContext(),SeeAllActivity.class);
        String type=null;
        if(id==R.id.popularTVseeAll){
            type="popular";
           // intent.putExtra("totalPages",pTotalPages);
        }
//        else if(id==R.id.upcomingSeeAll){
//            type="upcoming";
//            intent.putExtra("totalPages",uTotalPages);
//
//        }
        else if(id==R.id.topRatedTVseeAll){
            type="top_rated";
          //  intent.putExtra("totalPages",tTotalPages);
        }

        intent.putExtra("type",type);
        intent.putExtra("MovieTV","tv");
        startActivity(intent);
    }
}
