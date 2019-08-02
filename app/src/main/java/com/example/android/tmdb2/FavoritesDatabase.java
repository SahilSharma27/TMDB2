package com.example.android.tmdb2;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import org.w3c.dom.Comment;


@Database(entities = {Favorites.class},version = 1)
public abstract class FavoritesDatabase extends RoomDatabase {
    abstract FavoritesDAO getFavoriteDao();
}
