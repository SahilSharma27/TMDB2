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
    @GET("{movieORtv}/{movie_id}/videos?api_key=2f532ba110a3df2d734ee5d96abb504b&language=en-US")
    Call<Trailer>getMovieTrailer(@Path("movieORtv")String mOrtv,@Path("movie_id") Long id);
    @GET("{movieORtv}/{movie_id}/credits?api_key=2f532ba110a3df2d734ee5d96abb504b")
    Call<CastObject>getCredits(@Path("movieORtv") String mOrtv,@Path("movie_id") Long id);
    @GET("{movieORtv}/{movie_id}/similar?api_key=2f532ba110a3df2d734ee5d96abb504b&language=en-US&page=1")
    Call<Movie>getSimilarMovies(@Path("movieORtv") String mOrTv,@Path("movie_id") Long id);
    @GET("{movieTV}/{type}?api_key=2f532ba110a3df2d734ee5d96abb504b&language=en-US")
    Call<Movie>getALLMovies(@Path("movieTV")String mOrTv,@Path("type") String type,@Query("page") long pg);
    @GET("tv/on_the_air?api_key=2f532ba110a3df2d734ee5d96abb504b&language=en-US&page=1")
    Call<TV>getTVonAir();
    @GET("tv/popular?api_key=2f532ba110a3df2d734ee5d96abb504b&language=en-US&page=1")
    Call<TV>getPopularTV();
    @GET("tv/top_rated?api_key=2f532ba110a3df2d734ee5d96abb504b&language=en-US&page=1")
    Call<TV>getTvTopRated();
    @GET("person/{personId}?api_key=2f532ba110a3df2d734ee5d96abb504b&language=en-US")
    Call<Person>getPersonDetails(@Path("personId") long id);
    @GET("{movieTV}/{type}?api_key=2f532ba110a3df2d734ee5d96abb504b&language=en-US")
    Call<TV>getALLTv(@Path("movieTV")String mOrTv,@Path("type") String type,@Query("page") long pg);
}
