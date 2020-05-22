package com.dev.cinema.dao;

import java.util.List;
import com.dev.cinema.model.CinemaHall;

public interface CinemaHallDao {
    public CinemaHall add(CinemaHall cinemaHall);

    public List<CinemaHall> getAll();
}
