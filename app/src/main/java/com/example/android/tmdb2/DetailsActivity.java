package com.example.android.tmdb2;

import android.arch.persistence.room.Room;
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
    RecyclerView castRecyclerView, similarRecyclerView,reviewsRecycleView;
    CastAdapter adapter;
    ArrayList<Cast> cast = new ArrayList<>();
    ArrayList<Poster> similarMovie = new ArrayList<>();
    Movie sMovie;
    CustomAdapterType2 adapter2;
    ReviewsAdapter adapter3;
    CastObject obj;
    String movieOrTv;
    ArrayList<Result> reviews=new ArrayList<>();
    Reviews review;
    FavoritesDAO favoritesDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        castRecyclerView = findViewById(R.id.castRecyclerView);
        similarRecyclerView = findViewById(R.id.similarRecyclerView);
        reviewsRecycleView=findViewById(R.id.reviewRecyclerView);
        ratingtextView = findViewById(R.id.rating);

        iview = findViewById(R.id.iMg);
        iview1 = findViewById(R.id.Img);
        OverView = findViewById(R.id.overviewId);
        DateView = findViewById(R.id.date);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fab.setImageResource(R.drawable.ic_favorite_black_24dp);
                Favorites favorite=new Favorites();
                favorite.setId(movieId);

                FavoritesDatabase database = Room.databaseBuilder(getApplicationContext(),FavoritesDatabase.class,"favorites_db").allowMainThreadQueries().build();
                favoritesDAO = database.getFavoriteDao();
                favoritesDAO.addMovieTv(favorite);
                Snackbar.make(view, "Added to favourites.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        FloatingActionButton fab1 = findViewById(R.id.fab1);
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Playing Trailer", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                openTrailer();
            }
        });
        fab1.setImageResource(R.drawable.ic_play_arrow_black_24dp);

        final Intent intent = getIntent();
        Bundle b = intent.getExtras();
        movieId = b.getLong("id");
        String title = b.getString("title");
        String backPosterUrl = b.getString("backPst");
        String mainPosterUrl = b.getString("poster");
        String overview = b.getString("overview");
        Double rating = b.getDouble("rating");
        String date = b.getString("release_date");
        movieOrTv=b.getString("type");
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
                Toast.makeText(getApplicationContext(),cast.get(position).getId()+"",Toast.LENGTH_LONG).show();
                Intent intent1=new Intent(getApplicationContext(),CastDescriptionActivity.class);
                intent1.putExtra("castId",obj.getCast().get(position).getId());
                startActivity(intent1);
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
                //todo

            }
        });
        similarRecyclerView.setAdapter(adapter2);
        similarRecyclerView.setItemAnimator(new DefaultItemAnimator());
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        similarRecyclerView.setLayoutManager(linearLayoutManager1);
        fetchData(1);

        adapter3=new ReviewsAdapter(this, reviews, new reviewClickListener() {
            @Override
            public void onMovieClicked(View view, int position) {
                //todo;
            }
        });
        reviewsRecycleView.setAdapter(adapter3);
        reviewsRecycleView.setItemAnimator(new DefaultItemAnimator());
        LinearLayoutManager linearLayoutManager2=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        reviewsRecycleView.setLayoutManager(linearLayoutManager2);
        fetchData(2);

    }

    public void openTrailer() {
        Call<Trailer> call = ApiClient.getMovieTVServices().getMovieTrailer(movieOrTv,movieId);
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
            Call<CastObject> call = ApiClient.getMovieTVServices().getCredits(movieOrTv,movieId);
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
            Call <Movie> call=ApiClient.getMovieTVServices().getSimilarMovies(movieOrTv,movieId);
            call.enqueue(new Callback<Movie>() {
                @Override
                public void onResponse(Call<Movie> call, Response<Movie> response) {
                    sMovie=response.body();
                    for (int i = 0; i < sMovie.getResults().size(); i++) {
                        Poster poster=new Poster(sMovie.getResults().get(i).getOriginalTitle(),sMovie.getResults().get(i).getPosterPath());
                        similarMovie.add(poster);

                    }
                    adapter2.notifyDataSetChanged();

                }

                @Override
                public void onFailure(Call<Movie> call, Throwable t) {

                }
            });


        }
        else if(code==2){
            int id=movieId.intValue();
            Call<Reviews> call=ApiClient.getMovieTVServices().getReviews(movieOrTv,id);
            call.enqueue(new Callback<Reviews>() {
                @Override
                public void onResponse(Call<Reviews> call, Response<Reviews> response) {
                    review=response.body();

                    reviews.addAll(review.getResults());
//                    for(int i=0;i<review.getResults().size();i++){
//                        Result result=review.getResults().get(i);
//                        reviews.add(result);
//                    }
                    adapter3.notifyDataSetChanged();
                }

                @Override
                public void onFailure(Call<Reviews> call, Throwable t) {

                }
            });

        }

    }
}
