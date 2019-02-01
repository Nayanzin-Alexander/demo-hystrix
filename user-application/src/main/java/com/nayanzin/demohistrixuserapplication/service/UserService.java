package com.nayanzin.demohistrixuserapplication.service;

import com.nayanzin.demohistrixuserapplication.dto.Movie;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.http.HttpMethod.GET;

@Service
@RequiredArgsConstructor
public class UserService {

    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);
    private static final ParameterizedTypeReference<List<Movie>> MOVIES_LIST_PTR = new ParameterizedTypeReference<List<Movie>>() {
    };

    private static List<Movie> fallBackUserRecommendationMovies = new ArrayList<>();

    static {
        fallBackUserRecommendationMovies.addAll(Arrays.asList(
                new Movie("Sherlock Holmes", "Fiction"),
                new Movie("Iron Man", "Action")));
    }

    private final RestTemplate restTemplate;

    @Value("${external.user-recommendation.url}")
    private String userRecommendationUrl;

    @HystrixCommand(fallbackMethod = "callMovieDetailsFallBack")
    public List<Movie> getMovieDetails() {
        return restTemplate.exchange(userRecommendationUrl, GET, null, MOVIES_LIST_PTR).getBody();
    }

    public List<Movie> callMovieDetailsFallBack() {
        LOG.warn("User Recommendation Service is down returning general recommendation movies");
        return fallBackUserRecommendationMovies;
    }
}
