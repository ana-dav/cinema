package com.dev.cinema.dao;

import java.util.List;
import com.dev.cinema.lib.Dao;
import com.dev.cinema.model.Movie;

@Dao
public interface MovieDao {
    Movie add(Movie movie);

    List<Movie> getAll();
}
