package com.nayanzin.demohistrixuserapplication.controller;

import com.nayanzin.demohistrixuserapplication.dto.Movie;
import com.nayanzin.demohistrixuserapplication.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("getMovieDetails")
    public List<Movie> getMovieRecommendations() {
        return userService.getMovieDetails();
    }
}
