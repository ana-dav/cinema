package com.dev.cinema.service.impl;

import com.dev.cinema.dao.interfaces.CinemaHallDao;
import com.dev.cinema.model.CinemaHall;
import com.dev.cinema.service.interfaces.CinemaHallService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CinemaHallServiceImpl implements CinemaHallService {
    @Autowired
    private CinemaHallDao cinemaHallDao;

    @Override
    public CinemaHall add(CinemaHall cinemaHall) {
        return cinemaHallDao.add(cinemaHall);
    }

    @Override
    public List<CinemaHall> getAll() {
        return cinemaHallDao.getAll();
    }

    @Override
    public CinemaHall getCinemaHallById(Long id) {
        return cinemaHallDao.getCinemaHallById(id);
    }
}
