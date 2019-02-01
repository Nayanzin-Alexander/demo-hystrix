package com.nayanzin.demohistrixuserrecomendations.controller;

import com.nayanzin.demohistrixuserrecomendations.dto.Movie;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class UserRecommendationController {

    private static List<Movie> userRecommendationMovies = new ArrayList<>();

    static {
        userRecommendationMovies.addAll(Arrays.asList(
                new Movie("Batman Begins", "Action"),
                new Movie("Iron Man", "Action"),
                new Movie("Harry Potter", "Fiction"),
                new Movie("Lord of the Rings", "Fiction"),
                new Movie("Twilight", "Romance")));
    }

    @GetMapping("getUserMovieDetails")
    public List<Movie> getUserRecommendation() {
        return userRecommendationMovies;
    }
}
