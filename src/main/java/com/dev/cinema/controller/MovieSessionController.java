package com.dev.cinema.controller;

import com.dev.cinema.mappers.MovieSessionMapper;
import com.dev.cinema.model.MovieSession;
import com.dev.cinema.model.dto.MovieSessionRequestDto;
import com.dev.cinema.model.dto.MovieSessionResponseDto;
import com.dev.cinema.service.interfaces.MovieSessionService;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movie-sessions")
public class MovieSessionController {
    private final MovieSessionService movieSessionService;
    private final MovieSessionMapper movieSessionMapper;

    public MovieSessionController(MovieSessionService movieSessionService,
                                  MovieSessionMapper movieSessionDtoMapper) {
        this.movieSessionService = movieSessionService;
        this.movieSessionMapper = movieSessionDtoMapper;
    }

    @PostMapping
    public void addMovieSession(@RequestBody MovieSessionRequestDto movieSessionRequestDto) {
        movieSessionService.add(movieSessionMapper.dtoToMovieSession(movieSessionRequestDto));
    }

    @GetMapping("/available")
    public List<MovieSessionResponseDto> getAvailableSession(@RequestParam Long movieId,
                                                             @RequestParam
                                                             @DateTimeFormat(iso
                                                                     = DateTimeFormat.ISO.DATE)
                                                                     LocalDate sessionTime) {
        List<MovieSession> movieSessions =
                movieSessionService.findAvailableSessions(movieId, sessionTime);

        return movieSessions.stream()
                .map(movieSessionMapper::movieSessionToDto)
                .collect(Collectors.toList());
    }
}
