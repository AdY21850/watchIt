package com.example.watchit;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;
import java.util.List;

public class dashboard extends AppCompatActivity {

    LinearLayout navExplore, navLike, navCart, navProfile;
    TextView textExplore, textLike, textCart, textProfile;
    ViewPager2 viewPager;
    ProgressBar progressBar1, progressBar2, progressBar3;
    RecyclerView recyclerViewTopMovies, recyclerViewUpcomingMovies;
    TopMoviesAdapter topMoviesAdapter;
    MovieAdapter movieAdapter;
    upcoming_movie_adapter upcomingMovieAdapter;

    private List<Model_movie> bannerMovies;
    private List<Model_Movie1> movieList1;
    private static final int MULTIPLIER = 2; // Duplicate dataset for infinite scrolling
    private Handler sliderHandler = new Handler();

    private Runnable sliderRunnable = new Runnable() {
        @Override
        public void run() {
            int currentItem = viewPager.getCurrentItem();
            int nextItem = (currentItem + 1) % bannerMovies.size(); // Loop back to first item
            viewPager.setCurrentItem(nextItem, true);
            sliderHandler.postDelayed(this, 3000); // Change every 3 seconds
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dashboard);

        // Initialize navigation buttons
        navExplore = findViewById(R.id.nav_explore);
        navLike = findViewById(R.id.nav_like);
        navCart = findViewById(R.id.nav_cart);
        navProfile = findViewById(R.id.nav_profile);

        textExplore = findViewById(R.id.text_explore);
        textLike = findViewById(R.id.text_like);
        textCart = findViewById(R.id.text_cart);
        textProfile = findViewById(R.id.text_profile);

        navExplore.setOnClickListener(v -> updateNavSelection(textExplore));
        navLike.setOnClickListener(v -> updateNavSelection(textLike));
        navCart.setOnClickListener(v -> updateNavSelection(textCart));
        navProfile.setOnClickListener(v -> updateNavSelection(textProfile));

        // Initialize ViewPager2 and ProgressBars
        viewPager = findViewById(R.id.ViewPager2);
        progressBar1 = findViewById(R.id.progressBar);
        progressBar2 = findViewById(R.id.progressBarTopMovies);
        progressBar3 = findViewById(R.id.progressBarUpcomingMovies);

        // Initialize banner movies list
        bannerMovies = new ArrayList<>();
        bannerMovies.add(new Model_movie(R.drawable.wide, "Oppenheimer", "History | Thriller | Drama", "2023", "9.1"));
        bannerMovies.add(new Model_movie(R.drawable.wide1, "Avengers: Endgame", "Action | Sci-Fi", "2019", "8.4"));
        bannerMovies.add(new Model_movie(R.drawable.wide2, "Inception", "Sci-Fi | Thriller", "2010", "8.8"));
        bannerMovies.add(new Model_movie(R.drawable.wide3, "The Dark Knight", "Action | Crime", "2008", "9.0"));

        // Set up ViewPager2 with the adapter
        movieAdapter = new MovieAdapter(bannerMovies, viewPager, this);
        viewPager.setAdapter(movieAdapter);

        // Start auto-scroll
        sliderHandler.postDelayed(sliderRunnable, 3000);

        // Handle user interaction to prevent auto-scroll from interrupting manual swipes
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                sliderHandler.removeCallbacks(sliderRunnable);
                sliderHandler.postDelayed(sliderRunnable, 3000); // Reset timer after user interaction
            }
        });

        progressBar1.setVisibility(View.GONE);

        // Initialize RecyclerView for Top Movies
        recyclerViewTopMovies = findViewById(R.id.recyclerViewTopMovies);
        recyclerViewTopMovies.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        movieList1 = new ArrayList<>();
        movieList1.add(new Model_Movie1(R.drawable.godzillaxkong, "Godzilla x Kong"));
        movieList1.add(new Model_Movie1(R.drawable.kungfupanda, "Kung Fu Panda"));
        movieList1.add(new Model_Movie1(R.drawable.damaged_img, "Damaged"));
        movieList1.add(new Model_Movie1(R.drawable.no_way_up, "No Way Up"));
        movieList1.add(new Model_Movie1(R.drawable.wide1, "Wide Movie 4"));
        movieList1.add(new Model_Movie1(R.drawable.kungfupanda, "Kung Fu Panda"));
        movieList1.add(new Model_Movie1(R.drawable.wide, "Wide Movie 1"));
        movieList1.add(new Model_Movie1(R.drawable.godzillaxkong, "Godzilla x Kong"));

        // Duplicate dataset for infinite effect
        List<Model_Movie1> infiniteList = new ArrayList<>(movieList1);
        infiniteList.addAll(movieList1);

        topMoviesAdapter = new TopMoviesAdapter(infiniteList);
        recyclerViewTopMovies.setAdapter(topMoviesAdapter);
        recyclerViewTopMovies.scrollToPosition(movieList1.size()); // Start in the middle
        progressBar2.setVisibility(View.GONE);

        // Initialize RecyclerView for Upcoming Movies
        recyclerViewUpcomingMovies = findViewById(R.id.recyclerViewUpcommingMovies);
        recyclerViewUpcomingMovies.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        List<Model_Movie1> upcomingMovies = new ArrayList<>();
        upcomingMovies.add(new Model_Movie1(R.drawable.wide1, "Wide Movie 4"));
        upcomingMovies.add(new Model_Movie1(R.drawable.kungfupanda, "Kung Fu Panda"));
        upcomingMovies.add(new Model_Movie1(R.drawable.wide, "Wide Movie 1"));
        upcomingMovies.add(new Model_Movie1(R.drawable.godzillaxkong, "Godzilla x Kong"));
        upcomingMovies.add(new Model_Movie1(R.drawable.kungfupanda, "Kung Fu Panda"));
        upcomingMovies.add(new Model_Movie1(R.drawable.damaged_img, "Damaged"));
        upcomingMovies.add(new Model_Movie1(R.drawable.no_way_up, "No Way Up"));

        upcomingMovieAdapter = new upcoming_movie_adapter(upcomingMovies);
        recyclerViewUpcomingMovies.setAdapter(upcomingMovieAdapter);
        progressBar3.setVisibility(View.GONE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        sliderHandler.removeCallbacks(sliderRunnable);
    }

    private void updateNavSelection(TextView selectedTextView) {
        textExplore.setVisibility(View.GONE);
        textLike.setVisibility(View.GONE);
        textCart.setVisibility(View.GONE);
        textProfile.setVisibility(View.GONE);

        selectedTextView.setVisibility(View.VISIBLE);
    }
}
