package com.example.watchit;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class Movies_Details_Activity extends AppCompatActivity {

    private TextView movieTitle, movieGenre, movieYear, movieRating, movieSummary;
    private ImageView movieImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_movies_details);

        // Initializing UI elements
        movieTitle = findViewById(R.id.tvMovieTitle);
        movieGenre = findViewById(R.id.gener1);
        movieYear = findViewById(R.id.tvMovieYear);
        movieRating = findViewById(R.id.tvMovieRating);
        movieSummary = findViewById(R.id.tvMovieSummary); // New field for summary
        movieImage = findViewById(R.id.movie_details_banner_img1);

        // Retrieving the data from the Intent
        Intent intent = getIntent();
        if (intent != null) {
            if (intent.hasExtra("movieTitle")) {
                movieTitle.setText(intent.getStringExtra("movieTitle"));
            }
            if (intent.hasExtra("movieGenre")) {
                movieGenre.setText(intent.getStringExtra("movieGenre"));
            }
            if (intent.hasExtra("movieYear")) {
                movieYear.setText(intent.getStringExtra("movieYear"));
            }
            if (intent.hasExtra("movieRating")) {
                movieRating.setText(intent.getStringExtra("movieRating"));
            }
            if (intent.hasExtra("summary")) { // Handling movie summary
                movieSummary.setText(intent.getStringExtra("summary"));
            }
            if (intent.hasExtra("movieImage")) {
                int imageResId = intent.getIntExtra("movieImage", R.drawable.rebel_moon_movie_poster);
                movieImage.setImageResource(imageResId);
            }
        }
    }
}
