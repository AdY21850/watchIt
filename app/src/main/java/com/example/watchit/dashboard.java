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
import java.util.Arrays;
import java.util.List;

public class dashboard extends AppCompatActivity {

    LinearLayout navExplore, navLike, navCart, navProfile;
    TextView textExplore, textLike, textCart, textProfile;
    ViewPager2 viewPager;
    ProgressBar progressBar1;
    ProgressBar progressBar2;
    ProgressBar progressBar3;
    RecyclerView recyclerView;
    TopMoviesAdapter topMoviesAdapter;
    private List<Model_movie> movieList;
    private final Handler sliderHandler = new Handler(); // Handler for auto-slide
    private List<Integer> bannerMovieList;
    private boolean isUserScrolling = false; // Track user scrolling

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dashboard);

        // Bottom Navigation
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

        // ViewPager2 Setup
        viewPager = findViewById(R.id.ViewPager2);
        progressBar1 = findViewById(R.id.progressBar);
        progressBar2 = findViewById(R.id.progressBarTopMovies);
        progressBar3 = findViewById(R.id.progressBarUpcomingMovies);


        // Movie list with fake first and last items for looping
        List<Integer> realMovieList = Arrays.asList(
                R.drawable.wide,
                R.drawable.wide1,
                R.drawable.wide2,
                R.drawable.wide3
        );

        // Create looping list by adding duplicates at start and end
        bannerMovieList = new ArrayList<>();
        bannerMovieList.add(realMovieList.get(realMovieList.size() - 1)); // Fake first item (last image)
        bannerMovieList.addAll(realMovieList);
        bannerMovieList.add(realMovieList.get(0)); // Fake last item (first image)

        // Set Adapter
        MovieAdapter adapter = new MovieAdapter(bannerMovieList, viewPager);
        viewPager.setAdapter(adapter);
        viewPager.setPageTransformer(new OverlapTransformer());

        // Set initial position to the first real image
        viewPager.setCurrentItem(1, false);

        // Hide ProgressBar when images are loaded
        progressBar1.setVisibility(View.GONE);

        // Auto-scroll feature
        startAutoSlide();

        // Handle looping transition without showing jump
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);

                if (state == ViewPager2.SCROLL_STATE_DRAGGING) {
                    isUserScrolling = true;
                } else if (state == ViewPager2.SCROLL_STATE_IDLE) {
                    isUserScrolling = false;
                }
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);

                if (position == 0) {
                    viewPager.post(() -> viewPager.setCurrentItem(bannerMovieList.size(), false));
                } else if (position == bannerMovieList.size() + 1) {
                    viewPager.post(() -> viewPager.setCurrentItem(1, false));
                }
            }
        });

        // RecyclerView Setup for Top Movies
        recyclerView = findViewById(R.id.recyclerViewTopMovies);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        movieList = new ArrayList<>();
        movieList.add(new Model_movie(R.drawable.godzillaxkong));
        movieList.add(new Model_movie(R.drawable.kungfupanda));
        movieList.add(new Model_movie(R.drawable.damaged_img));
        movieList.add(new Model_movie(R.drawable.no_way_up));
        movieList.add(new Model_movie(R.drawable.wide));
        movieList.add(new Model_movie(R.drawable.wide2));
        movieList.add(new Model_movie(R.drawable.wide3));
        movieList.add(new Model_movie(R.drawable.wide1));

        topMoviesAdapter = new TopMoviesAdapter(movieList);
        recyclerView.setAdapter(topMoviesAdapter);
        progressBar2.setVisibility(View.GONE);

        //recycler view setup for upcomming movies

        recyclerView = findViewById(R.id.recyclerViewUpcommingMovies);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        movieList = new ArrayList<>();
        movieList.add(new Model_movie(R.drawable.wide1));
        movieList.add(new Model_movie(R.drawable.kungfupanda));
        movieList.add(new Model_movie(R.drawable.wide));
        movieList.add(new Model_movie(R.drawable.wide2));
        movieList.add(new Model_movie(R.drawable.wide3));
        movieList.add(new Model_movie(R.drawable.no_way_up));
        movieList.add(new Model_movie(R.drawable.damaged_img));
        movieList.add(new Model_movie(R.drawable.godzillaxkong));

        topMoviesAdapter = new TopMoviesAdapter(movieList);
        recyclerView.setAdapter(topMoviesAdapter);
        progressBar3.setVisibility(View.GONE);
    }


    private void updateNavSelection(TextView selectedTextView) {
        textExplore.setVisibility(View.GONE);
        textLike.setVisibility(View.GONE);
        textCart.setVisibility(View.GONE);
        textProfile.setVisibility(View.GONE);

        selectedTextView.setVisibility(View.VISIBLE);
    }

    private void startAutoSlide() {
        sliderHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!isUserScrolling) {
                    int nextPosition = viewPager.getCurrentItem() + 1;
                    viewPager.setCurrentItem(nextPosition, true);
                }
                sliderHandler.postDelayed(this, 3000); // Change slide every 3 seconds
            }
        }, 3000);
    }
}
