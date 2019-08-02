package com.example.android.tmdb2;


import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class MoviesFAVFragment extends Fragment {

    FavoritesDAO favoritesDAO;
    RecyclerView recyclerView;
    CustomAdapterType2 adapter;
    ArrayList<Poster>favMovies=new ArrayList<>();
    MovieResult favM;
    ProgressBar pb;
    List<Favorites> fav;
    public MoviesFAVFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View output = inflater.inflate(R.layout.fragment_movies_fav, container, false);
        pb=output.findViewById(R.id.favPB);
        recyclerView=output.findViewById(R.id.favRecyclerview);

        adapter=new CustomAdapterType2(getContext(), favMovies, new MovieTVClickListener() {
            @Override
            public void onMovieClicked(View view, int position) {
                Favorites f=fav.get(position);
                favoritesDAO.deleteMovieTv(f);
                adapter.notifyDataSetChanged();

            }
        });

        recyclerView.setAdapter(adapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        GridLayoutManager gridLayoutManager=new GridLayoutManager(getContext(),3);
        recyclerView.setLayoutManager(gridLayoutManager);


        FavoritesDatabase database = Room.databaseBuilder(getContext(), FavoritesDatabase.class, "favorites_db").allowMainThreadQueries().build();
        favoritesDAO = database.getFavoriteDao();

        fav = favoritesDAO.getFavorites();
        if (fav.size() == 0) {
            //todo
            }
            else{
            for(int i=0;i<fav.size();i++){
                fetchData(fav.get(i).getId());
            }
            pb.setVisibility(View.GONE);

        }

        return output;

    }
    void fetchData(long id){
        Call<MovieResult>call=ApiClient.getMovieTVServices().getFavMovie(id);
        call.enqueue(new Callback<MovieResult>() {
            @Override
            public void onResponse(Call<MovieResult> call, Response<MovieResult> response) {
                Log.d("fav",response.toString());
                favM=response.body();
                Poster poster=new Poster(favM.getOriginalTitle(),favM.getPosterPath());
                favMovies.add(poster);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<MovieResult> call, Throwable t) {

            }
        });




    }


}
