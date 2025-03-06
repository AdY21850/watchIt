package com.example.watchit;

public class Model_movie {
    private int imageResId;
    private String movieName;

    public Model_movie(int imageResId, String movieName) {
        this.imageResId = imageResId;
        this.movieName = movieName;
    }

    public int getImageResId() {
        return imageResId;
    }

    public String getMovieName() {
        return movieName;
    }
}
