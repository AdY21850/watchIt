package com.example.watchit;

public class RecommendationModel {
    private String title;
    private String posterUrl;
    private double rating;

    public RecommendationModel(String title, String posterUrl, double rating) {
        this.title = title;
        this.posterUrl = posterUrl;
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public double getRating() {
        return rating;
    }
}
