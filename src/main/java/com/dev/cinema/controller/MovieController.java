package com.dev.cinema.controller;

import java.util.List;
import java.util.stream.Collectors;
import com.dev.cinema.dto.MovieDto;
import com.dev.cinema.model.Movie;
import com.dev.cinema.service.interfaces.MovieService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movie")
public class MovieController {
    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @PostMapping
    public void add(@RequestBody MovieDto movieDto) {
        Movie movie = new Movie();
        movie.setTitle(movieDto.getTitle());
        movie.setDescription(movieDto.getDescription());
        movieService.add(movie);
    }

    @GetMapping("/movies")
    public List<MovieDto> getAll() {
        return movieService.getAll().stream()
                .map(this::getMovieDto)
                .collect(Collectors.toList());
    }

    private MovieDto getMovieDto(Movie movie) {
        MovieDto dto = new MovieDto();
        dto.setTitle(movie.getTitle());
        dto.setDescription(movie.getDescription());
        return dto;
    }
}
