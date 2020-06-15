package com.dev.cinema.dao.interfaces;

import com.dev.cinema.model.Movie;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieDao {
    Movie add(Movie movie);

    List<Movie> getAll();

    Movie getMovieById(Long id);
}
