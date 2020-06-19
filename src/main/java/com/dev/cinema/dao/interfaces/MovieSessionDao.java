package com.dev.cinema.dao.interfaces;

import com.dev.cinema.model.MovieSession;
import java.time.LocalDate;
import java.util.List;

public interface MovieSessionDao {
    public MovieSession add(MovieSession session);

    public List<MovieSession> findAvailableSessions(Long movieId, LocalDate date);

    MovieSession getById(Long id);
}
