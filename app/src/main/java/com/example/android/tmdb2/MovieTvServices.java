package com.example.android.tmdb2;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieTvServices {
    @GET("movie/popular?api_key=2f532ba110a3df2d734ee5d96abb504b&language=en-US&page=1")
    Call<Movie> getPopularMovies();
    @GET("movie/upcoming?api_key=2f532ba110a3df2d734ee5d96abb504b&language=en-US&page=1")
    Call<Movie>getUpcomingMovies();
    @GET("movie/top_rated?api_key=2f532ba110a3df2d734ee5d96abb504b&language=en-US&page=1")
    Call<Movie>getTopRatedMovies();
    @GET("movie/{movie_id}/videos?api_key=2f532ba110a3df2d734ee5d96abb504b&language=en-US")
    Call<Trailer>getMovieTrailer(@Path("movie_id") Long id);
    @GET("movie/{movie_id}/credits?api_key=2f532ba110a3df2d734ee5d96abb504b")
    Call<CastObject>getCredits(@Path("movie_id") Long id);
    @GET("movie/{movie_id}/similar?api_key=2f532ba110a3df2d734ee5d96abb504b&language=en-US&page=1")
    Call<Movie>getSimilarMovies(@Path("movie_id") Long id);
    @GET("movie/{type}?api_key=2f532ba110a3df2d734ee5d96abb504b&language=en-US")
    Call<Movie>getALLMovies(@Path("type") String type,@Query("page") long pg);
}
