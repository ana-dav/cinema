package com.dev.cinema.service.interfaces;

import com.dev.cinema.model.Movie;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface MovieService {
    Movie add(Movie movie);

    List<Movie> getAll();
}
