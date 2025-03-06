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
    ProgressBar progressBar1, progressBar2, progressBar3;
    RecyclerView recyclerViewTopMovies, recyclerViewUpcomingMovies;
    TopMoviesAdapter topMoviesAdapter;
    MovieAdapter MovieAdapter;
    private List<Model_movie> movieList;
    private final Handler sliderHandler = new Handler();
    private List<Integer> bannerMovieList;
    private boolean isUserScrolling = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dashboard);

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

        viewPager = findViewById(R.id.ViewPager2);
        progressBar1 = findViewById(R.id.progressBar);
        progressBar2 = findViewById(R.id.progressBarTopMovies);
        progressBar3 = findViewById(R.id.progressBarUpcomingMovies);

        List<Integer> realMovieList = Arrays.asList(
                R.drawable.wide,
                R.drawable.wide1,
                R.drawable.wide2,
                R.drawable.wide3
        );

        bannerMovieList = new ArrayList<>();
        bannerMovieList.add(realMovieList.get(realMovieList.size() - 1));
        bannerMovieList.addAll(realMovieList);
        bannerMovieList.add(realMovieList.get(0));

        MovieAdapter adapter = new MovieAdapter(bannerMovieList, viewPager);
        viewPager.setAdapter(adapter);
        viewPager.setPageTransformer(new OverlapTransformer());
        viewPager.setCurrentItem(1, false);

        progressBar1.setVisibility(View.GONE);
        startAutoSlide();

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrollStateChanged(int state) {
                if (state == ViewPager2.SCROLL_STATE_DRAGGING) {
                    isUserScrolling = true;
                } else if (state == ViewPager2.SCROLL_STATE_IDLE) {
                    isUserScrolling = false;
                }
            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    viewPager.postDelayed(() -> viewPager.setCurrentItem(bannerMovieList.size(), false), 200);
                } else if (position == bannerMovieList.size() + 1) {
                    viewPager.postDelayed(() -> viewPager.setCurrentItem(1, false), 200);
                }
            }
        });

        recyclerViewTopMovies = findViewById(R.id.recyclerViewTopMovies);
        recyclerViewTopMovies.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        movieList = new ArrayList<>();
        movieList.add(new Model_movie(R.drawable.godzillaxkong, "Godzilla x Kong"));
        movieList.add(new Model_movie(R.drawable.kungfupanda, "Kung Fu Panda"));
        movieList.add(new Model_movie(R.drawable.damaged_img, "Damaged"));
        movieList.add(new Model_movie(R.drawable.no_way_up, "No Way Up"));
        movieList.add(new Model_movie(R.drawable.wide, "Wide Movie 1"));
        movieList.add(new Model_movie(R.drawable.wide2, "Wide Movie 2"));
        movieList.add(new Model_movie(R.drawable.wide3, "Wide Movie 3"));
        movieList.add(new Model_movie(R.drawable.wide1, "Wide Movie 4"));

        topMoviesAdapter = new TopMoviesAdapter(movieList);
        recyclerViewTopMovies.setAdapter(topMoviesAdapter);
        progressBar2.setVisibility(View.GONE);

        recyclerViewUpcomingMovies = findViewById(R.id.recyclerViewUpcommingMovies);
        recyclerViewUpcomingMovies.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        movieList = new ArrayList<>();
        movieList.add(new Model_movie(R.drawable.wide1, "Wide Movie 4"));
        movieList.add(new Model_movie(R.drawable.kungfupanda, "Kung Fu Panda"));
        movieList.add(new Model_movie(R.drawable.wide, "Wide Movie 1"));
        movieList.add(new Model_movie(R.drawable.wide2, "Wide Movie 2"));
        movieList.add(new Model_movie(R.drawable.wide3, "Wide Movie 3"));
        movieList.add(new Model_movie(R.drawable.no_way_up, "No Way Up"));
        movieList.add(new Model_movie(R.drawable.damaged_img, "Damaged"));
        movieList.add(new Model_movie(R.drawable.godzillaxkong, "Godzilla x Kong"));

        topMoviesAdapter = new TopMoviesAdapter(movieList);
        recyclerViewUpcomingMovies.setAdapter(topMoviesAdapter);
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
                    if (nextPosition == bannerMovieList.size() + 1) {
                        viewPager.postDelayed(() -> viewPager.setCurrentItem(1, false), 200);
                    } else {
                        viewPager.setCurrentItem(nextPosition, true);
                    }
                }
                sliderHandler.postDelayed(this, 3000);
            }
        }, 3000);
    }
}