package com.example.watchit;

import android.content.Intent;
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
    ProgressBar progressBar1, progressBar2, progressBar3, progressBar4;
    RecyclerView recyclerViewTopMovies, recyclerViewUpcomingMovies, recyclerViewRecommendations;
    TopMoviesAdapter topMoviesAdapter;
    MovieAdapter movieAdapter;
    RecommendationAdapter recommendationAdapter;
    upcoming_movie_adapter upcomingMovieAdapter;

    private List<Model_movie> bannerMovies;
    private List<Model_Movie1> movieList1;
    private List<RecommendationModel> recommendationList;

    private Handler sliderHandler = new Handler();

    private Runnable sliderRunnable = new Runnable() {
        @Override
        public void run() {
            int currentItem = viewPager.getCurrentItem();
            int nextItem = (currentItem + 1) % bannerMovies.size();
            viewPager.setCurrentItem(nextItem, true);
            sliderHandler.postDelayed(this, 3000);
        }
    };

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
        progressBar4 = findViewById(R.id.progressBarrecomendations);

        bannerMovies = new ArrayList<>();
        bannerMovies.add(new Model_movie(R.drawable.imacculate, "Immaculate", "History | Thriller | Drama", "2023", "9.1", "A gripping tale of survival."));
        bannerMovies.add(new Model_movie(R.drawable.godzillaxkong, "Godzilla x Kong", "Action | Sci-Fi", "2019", "8.4", "Epic titan battle."));
        bannerMovies.add(new Model_movie(R.drawable.damaged_img, "Damaged", "Sci-Fi | Thriller", "2010", "8.8", "Human enhancement & AI dominance."));
        bannerMovies.add(new Model_movie(R.drawable.threemuskaters_pic, "Three Musketeers", "Action | Crime", "2008", "9.0", "Modern retelling of a classic."));

        movieAdapter = new MovieAdapter(bannerMovies, viewPager, this, (position, movie) -> {
            Intent intent = new Intent(this, Movies_Details_Activity.class);
            intent.putExtra("movieTitle", movie.getTitle());
            intent.putExtra("movieGenre", movie.getGenre());
            intent.putExtra("movieYear", movie.getYear());
            intent.putExtra("movieRating", movie.getRating());
            intent.putExtra("movieImage", movie.getImageRes());
            intent.putExtra("summary", movie.getsummary());
            startActivity(intent);
        });
        viewPager.setAdapter(movieAdapter);

        sliderHandler.postDelayed(sliderRunnable, 3000);
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                sliderHandler.removeCallbacks(sliderRunnable);
                sliderHandler.postDelayed(sliderRunnable, 3000);
            }
        });

        progressBar1.setVisibility(View.GONE);

        recyclerViewTopMovies = findViewById(R.id.recyclerViewTopMovies);
        recyclerViewTopMovies.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        movieList1 = new ArrayList<>();
        movieList1.add(new Model_Movie1(R.drawable.godzillaxkong, "Godzilla x Kong"));
        movieList1.add(new Model_Movie1(R.drawable.kungfupanda, "Kung Fu Panda"));
        movieList1.add(new Model_Movie1(R.drawable.damaged_img, "Damaged"));
        movieList1.add(new Model_Movie1(R.drawable.no_way_up, "No Way Up"));

        topMoviesAdapter = new TopMoviesAdapter(movieList1);
        recyclerViewTopMovies.setAdapter(topMoviesAdapter);
        progressBar2.setVisibility(View.GONE);

        recyclerViewUpcomingMovies = findViewById(R.id.recyclerViewUpcommingMovies);
        recyclerViewUpcomingMovies.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        List<Model_Movie1> upcomingMovies = new ArrayList<>();
        upcomingMovies.add(new Model_Movie1(R.drawable.kungfupanda, "Kung Fu Panda"));
        upcomingMovies.add(new Model_Movie1(R.drawable.damaged_img, "Damaged"));
        upcomingMovies.add(new Model_Movie1(R.drawable.no_way_up, "No Way Up"));

        upcomingMovieAdapter = new upcoming_movie_adapter(upcomingMovies);
        recyclerViewUpcomingMovies.setAdapter(upcomingMovieAdapter);
        progressBar3.setVisibility(View.GONE);

        // Recommendations RecyclerView
        recyclerViewRecommendations = findViewById(R.id.recyclerViewrecomendations);
        recyclerViewRecommendations.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        recommendationList = new ArrayList<>();
        recommendationList.add(new RecommendationModel("Emakku Thozhil Romance", "https://res.cloudinary.com/dorc5p2jg/image/upload/fl_preserve_transparency/v1742670398/images_qiquuz.jpg?_s=public-apps", 8.4));
        recommendationList.add(new RecommendationModel("The Witch Revenge", "https://res.cloudinary.com/dorc5p2jg/image/upload/fl_preserve_transparency/v1742670416/images_dnn8vx.jpg?_s=public-apps", 8.7));
        recommendationList.add(new RecommendationModel("Ouija Castle", "https://res.cloudinary.com/dorc5p2jg/image/upload/fl_preserve_transparency/v1742670383/images_gogtnz.jpg?_s=public-apps", 7.9));
        recommendationList.add(new RecommendationModel("Nosferatu", "https://res.cloudinary.com/dorc5p2jg/image/upload/fl_preserve_transparency/v1742670347/images_tb7amm.jpg?_s=public-apps", 8.2));
        recommendationList.add(new RecommendationModel("Pushpa 2", "https://res.cloudinary.com/dorc5p2jg/image/upload/fl_preserve_transparency/v1742670318/hekl4hx4hjxpz54zdpig.jpg?_s=public-apps", 6.3));
        recommendationList.add(new RecommendationModel("Dhoom Dhaam", "https://res.cloudinary.com/dorc5p2jg/image/upload/fl_preserve_transparency/v1742670303/tzab5335mxoygalse4ck.jpg?_s=public-apps", 4.5));
        recommendationList.add(new RecommendationModel("Baby John", "https://res.cloudinary.com/dorc5p2jg/image/upload/fl_preserve_transparency/v1742670231/tbeiiyfnqdslip1p8sab.jpg?_s=public-apps", 9.2));
        recommendationList.add(new RecommendationModel("Marked Men: Rule + Shaw", "https://res.cloudinary.com/dorc5p2jg/image/upload/fl_preserve_transparency/v1742674634/evzvh4d5svntdyjdglak.jpg?_s=public-apps", 9.2));

        List<RecommendationModel> infiniteList = new ArrayList<>(recommendationList);
        infiniteList.addAll(recommendationList);

        recommendationAdapter = new RecommendationAdapter(this, infiniteList);
        recyclerViewRecommendations.setAdapter(recommendationAdapter);
        recyclerViewRecommendations.scrollToPosition(recommendationList.size());
        progressBar4.setVisibility(View.GONE);
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
