package com.dev.cinema.dao.interfaces;

import com.dev.cinema.model.CinemaHall;
import java.util.List;

public interface CinemaHallDao {
    public CinemaHall add(CinemaHall cinemaHall);

    public List<CinemaHall> getAll();

    CinemaHall getById(Long id);
}
