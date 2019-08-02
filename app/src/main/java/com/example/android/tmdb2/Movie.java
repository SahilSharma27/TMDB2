package com.example.android.tmdb2;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Movie {
    @SerializedName("dates")
    private Dates mDates;
    @SerializedName("page")
    private Long mPage;
    @SerializedName("results")
    private ArrayList<MovieResult> mResults;
    @SerializedName("total_pages")
    private Long mTotalPages;
    @SerializedName("total_results")
    private Long mTotalResults;

    public Dates getDates() {
        return mDates;
    }

    public void setDates(Dates dates) {
        mDates = dates;
    }

    public Long getPage() {
        return mPage;
    }

    public void setPage(Long page) {
        mPage = page;
    }

    public List<MovieResult> getResults() {
        return mResults;
    }

    public void setResults(ArrayList<MovieResult> results) {
        mResults = results;
    }

    public Long getTotalPages() {
        return mTotalPages;
    }

    public void setTotalPages(Long totalPages) {
        mTotalPages = totalPages;
    }

    public Long getTotalResults() {
        return mTotalResults;
    }

    public void setTotalResults(Long totalResults) {
        mTotalResults = totalResults;
    }
}
