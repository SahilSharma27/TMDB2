package com.example.android.tmdb2;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailsActivity extends AppCompatActivity {

    TextView ratingtextView, OverView, DateView;
    String baseurl = "http://image.tmdb.org/t/p/w780";
    String Base2url = "http://image.tmdb.org/t/p/w342/";
    ImageView iview, iview1;
    Long movieId;
    Trailer trailer;
    RecyclerView castRecyclerView, similarRecyclerView;
    CastAdapter adapter;
    ArrayList<Cast> cast = new ArrayList<>();
    ArrayList<Poster> similarMovie = new ArrayList<>();
    Movie sMovie;
    CustomAdapterType2 adapter2;
    CastObject obj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        castRecyclerView = findViewById(R.id.castRecyclerView);
        similarRecyclerView = findViewById(R.id.similarRecyclerView);
        ratingtextView = (TextView) findViewById(R.id.rating);
        iview = findViewById(R.id.iMg);
        iview1 = findViewById(R.id.Img);
        OverView = findViewById(R.id.overviewId);
        DateView = findViewById(R.id.date);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Added to favourites.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        fab.setImageResource(R.drawable.ic_favorite_black_24dp);
        FloatingActionButton fab1 = (FloatingActionButton) findViewById(R.id.fab1);
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Playing Trailer", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                openTrailer();
            }
        });
        fab1.setImageResource(R.drawable.ic_play_arrow_black_24dp);

        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        movieId = b.getLong("id");
        String title = b.getString("title");
        String backPosterUrl = b.getString("backPst");
        String mainPosterUrl = b.getString("poster");
        String overview = b.getString("overview");
        Double rating = b.getDouble("rating");
        String date = b.getString("release_date");
        getSupportActionBar().setTitle(title);
        ratingtextView.setText(rating + "/10");
        OverView.setText(overview);
        DateView.setText("Release date :" + date);

        Picasso.get().load(baseurl + backPosterUrl).into(iview);
        Picasso.get().load(Base2url + mainPosterUrl).into(iview1);


        Toast.makeText(this, title, Toast.LENGTH_LONG).show();
        adapter = new CastAdapter(cast, this, new CastClickListener() {
            @Override
            public void onCastClicked(View view, int position) {
                //todo
            }
        });
        castRecyclerView.setAdapter(adapter);
        castRecyclerView.setItemAnimator(new DefaultItemAnimator());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        castRecyclerView.setLayoutManager(linearLayoutManager);
        fetchData(0);

        adapter2 = new CustomAdapterType2(this, similarMovie, new MovieTVClickListener() {
            @Override
            public void onMovieClicked(View view, int position) {

            }
        });
        similarRecyclerView.setAdapter(adapter2);
        similarRecyclerView.setItemAnimator(new DefaultItemAnimator());
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        similarRecyclerView.setLayoutManager(linearLayoutManager1);
        fetchData(1);
    }

    public void openTrailer() {
        Call<Trailer> call = ApiClient.getMovieTVServices().getMovieTrailer(movieId);
        call.enqueue(new Callback<Trailer>() {
            @Override
            public void onResponse(Call<Trailer> call, Response<Trailer> response) {
                trailer = response.body();
                String id = trailer.getTrailerResults().get(0).getKey();
                watchYoutubeVideo(getApplicationContext(), id);

            }

            @Override
            public void onFailure(Call<Trailer> call, Throwable t) {

            }
        });

    }

    public static void watchYoutubeVideo(Context context, String id) {
        Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + id));
        Intent webIntent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("http://www.youtube.com/watch?v=" + id));
        try {
            context.startActivity(appIntent);
        } catch (ActivityNotFoundException ex) {
            context.startActivity(webIntent);
        }
    }

    public void fetchData(int code) {
        if (code == 0) {
            Call<CastObject> call = ApiClient.getMovieTVServices().getCredits(movieId);
            call.enqueue(new Callback<CastObject>() {
                @Override
                public void onResponse(Call<CastObject> call, Response<CastObject> response) {
                    obj = response.body();
                    for (int i = 0; i < obj.getCast().size(); i++) {
                        cast.add(i, obj.getCast().get(i));
                    }
                    adapter.notifyDataSetChanged();
                }

                @Override
                public void onFailure(Call<CastObject> call, Throwable t) {

                }
            });
        } else if (code == 1) {
            Call <Movie> call=ApiClient.getMovieTVServices().getSimilarMovies(movieId);
            call.enqueue(new Callback<Movie>() {
                @Override
                public void onResponse(Call<Movie> call, Response<Movie> response) {
                    sMovie=response.body();
                    for (int i = 0; i < sMovie.getResults().size(); i++) {
                        Poster poster=new Poster(sMovie.getResults().get(i).getOriginalTitle(),sMovie.getResults().get(i).getBackdropPath());
                        similarMovie.add(poster);

                    }
                    adapter2.notifyDataSetChanged();

                }

                @Override
                public void onFailure(Call<Movie> call, Throwable t) {

                }
            });


        }

    }
}
