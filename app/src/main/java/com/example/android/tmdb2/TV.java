package com.example.android.tmdb2;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TV {
    @SerializedName("page")
    private Long mPage;
    @SerializedName("results")
    private List<TVResult> mResults;
    @SerializedName("total_pages")
    private Long mTotalPages;
    @SerializedName("total_results")
    private Long mTotalResults;

    public Long getPage() {
        return mPage;
    }

    public void setPage(Long page) {
        mPage = page;
    }

    public List<TVResult> getResults() {
        return mResults;
    }

    public void setResults(List<TVResult> results) {
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
