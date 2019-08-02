package com.example.android.tmdb2;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface FavoritesDAO {
    @Insert
    void addMovieTv(Favorites fav);

    @Delete
    void deleteMovieTv(Favorites fav);

    @Query("select * from favorites")
    List<Favorites> getFavorites();
}
