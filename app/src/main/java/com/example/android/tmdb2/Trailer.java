package com.example.android.tmdb2;

import com.google.gson.annotations.Expose;

import java.util.List;

public class Trailer {
    @Expose
    private Long id;
    @Expose
    private List<TrailerResults> results;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<TrailerResults> getTrailerResults() {
        return results;
    }

    public void setResults(List<TrailerResults> results) {
        this.results = results;
    }

}
