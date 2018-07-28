package com.example.android.tmdb2;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static Retrofit retrofit;
    private static MovieTvServices services;
    static Retrofit getInstance(){
        if(retrofit==null){
            Retrofit.Builder builder=new Retrofit.Builder().baseUrl("https://api.themoviedb.org/3/")
                    .addConverterFactory(GsonConverterFactory.create());
            retrofit=builder.build();
        }
        return retrofit;
    }
    static MovieTvServices getMovieTVServices(){
        if(services==null){
            services=getInstance().create(MovieTvServices.class);
        }
        return services;
    }
}
