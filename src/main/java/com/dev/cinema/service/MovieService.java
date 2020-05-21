package com.dev.cinema.service;

import com.dev.cinema.lib.Service;
import com.dev.cinema.model.Movie;
import java.util.List;

@Service
public interface MovieService {
    Movie add(Movie movie);

    List<Movie> getAll();
}
