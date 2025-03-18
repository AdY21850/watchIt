package com.example.watchit;

public class Model_Movie1 {
    private int imageResId;
    private String movieName;

    public Model_Movie1(int imageResId, String movieName) {
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
