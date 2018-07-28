package com.example.android.tmdb2;

public class Poster {
    private String movietitle,thumbNailUrl;
    public Poster(String movietitle,String thumbNailUrl){
        this.movietitle=movietitle;
        this.thumbNailUrl=thumbNailUrl;
    }

    public String getMovietitle() {
        return movietitle;
    }

    public void setMovietitle(String movietitle) {
        this.movietitle = movietitle;
    }

    public String getThumbNailUrl() {
        return thumbNailUrl;
    }

    public void setThumbNailUrl(String thumbNailUrl) {
        this.thumbNailUrl = thumbNailUrl;
    }


}
