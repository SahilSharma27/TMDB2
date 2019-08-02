package com.example.android.tmdb2;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.PrimaryKey;




@Entity(tableName = "favorites")
public class Favorites {
    @PrimaryKey(autoGenerate = true)
    private int uid;

    @ColumnInfo(name = "movieTvId")
    private long Id;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }
}
