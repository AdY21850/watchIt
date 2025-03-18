package com.example.watchit;
public class Model_movie {
    private int imageRes;
    private String title;
    private String genre;
    private String year;
    private String rating;

    public Model_movie(int imageRes, String title, String genre, String year, String rating) {
        this.imageRes = imageRes;
        this.title = title;
        this.genre = genre;
        this.year = year;
        this.rating = rating;
    }

    public int getImageRes() { return imageRes; }
    public String getTitle() { return title; }
    public String getGenre() { return genre; }
    public String getYear() { return year; }
    public String getRating() { return rating; }
}
