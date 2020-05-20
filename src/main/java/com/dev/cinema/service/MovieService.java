package com.dev.cinema.service;

import java.util.List;
import com.dev.cinema.lib.Service;
import com.dev.cinema.model.Movie;

@Service
public interface MovieService {
    Movie add(Movie movie);

    List<Movie> getAll();
}
