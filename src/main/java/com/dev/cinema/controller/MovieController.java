package com.dev.cinema.controller;

import com.dev.cinema.mappers.MovieMapper;
import com.dev.cinema.model.Movie;
import com.dev.cinema.model.dto.MovieRequestDto;
import com.dev.cinema.model.dto.MovieResponseDto;
import com.dev.cinema.service.interfaces.MovieService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movies")
public class MovieController {
    private final MovieService movieService;
    private final MovieMapper movieMapper;

    public MovieController(MovieService movieService, MovieMapper movieDtoMapper) {
        this.movieService = movieService;
        this.movieMapper = movieDtoMapper;
    }

    @GetMapping
    public List<MovieResponseDto> getMovies() {
        List<Movie> movies = movieService.getAll();
        return movies.stream()
                .map(movieMapper::movieToDto)
                .collect(Collectors.toList());
    }

    @PostMapping
    public void addMovie(@RequestBody MovieRequestDto requestDto) {
        movieService.add(movieMapper.dtoToMovie(requestDto));
    }
}
