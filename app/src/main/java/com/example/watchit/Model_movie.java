package com.example.watchit;
public class Model_movie {
    private  int imageRes;
    private String title;
    private String genre;
    private String year;
    private String rating;
    private String summary;

    public Model_movie(int imageRes, String title, String genre, String year, String rating,String Summary) {
        this.imageRes = imageRes;
        this.title = title;
        this.genre = genre;
        this.year = year;
        this.rating = rating;
        this.summary=summary;
    }

    public  int getImageRes() { return imageRes; }
    public String getTitle() { return title; }
    public String getGenre() { return genre; }
    public String getYear() { return year; }
    public String getRating() { return rating; }
    public  String getsummary(){return summary;}
}
