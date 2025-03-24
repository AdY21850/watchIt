package com.example.watchit;

public class Model_movie {
    private String imageUrl;  // Change from int to String for image URLs
    private String title;
    private String genre;
    private String year;
    private String rating;
    private String summary;

    public Model_movie(String imageUrl, String title, String genre, String year, String rating, String summary) {
        this.imageUrl = imageUrl;
        this.title = title;
        this.genre = genre;
        this.year = year;
        this.rating = rating;
        this.summary = summary;
    }

    public String getImageUrl() { return imageUrl; }
    public String getTitle() { return title; }
    public String getGenre() { return genre; }
    public String getYear() { return year; }
    public String getRating() { return rating; }
    public String getSummary() { return summary; }
}
