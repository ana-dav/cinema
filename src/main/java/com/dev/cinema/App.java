package com.dev.cinema;

import com.dev.cinema.lib.Injector;
import com.dev.cinema.model.CinemaHall;
import com.dev.cinema.model.Movie;
import com.dev.cinema.model.MovieSession;
import com.dev.cinema.model.ShoppingCart;
import com.dev.cinema.model.User;
import com.dev.cinema.security.AuthenticationService;
import com.dev.cinema.service.CinemaHallService;
import com.dev.cinema.service.MovieService;
import com.dev.cinema.service.MovieSessionService;
import com.dev.cinema.service.ShoppingCartService;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import javax.security.sasl.AuthenticationException;

public class App {
    private static final Injector INJECTOR = Injector.getInstance("com.dev.cinema");
    private static MovieService movieService = (MovieService)
            INJECTOR.getInstance(MovieService.class);
    private static CinemaHallService cinemaHallService = (CinemaHallService)
            INJECTOR.getInstance(CinemaHallService.class);
    private static MovieSessionService movieSessionService = (MovieSessionService)
            INJECTOR.getInstance(MovieSessionService.class);
    private static AuthenticationService authenticationService = (AuthenticationService)
            INJECTOR.getInstance(AuthenticationService.class);
    private static ShoppingCartService shoppingCartService = (ShoppingCartService)
            INJECTOR.getInstance(ShoppingCartService.class);

    public static void main(String[] args) throws AuthenticationException {
        Movie movie = new Movie();
        movie.setTitle("Platform");
        movie.setDescription("The best netfix spanish thriller");
        movieService.add(movie);
        movieService.getAll().forEach(System.out::println);

        CinemaHall cinemaHall = new CinemaHall();
        cinemaHall.setCapacity(100);
        cinemaHall.setDescription("Red Hall");
        cinemaHallService.add(cinemaHall);
        cinemaHallService.getAll().forEach(System.out::println);

        MovieSession movieSession = new MovieSession();
        movieSession.setMovie(movie);
        movieSession.setShowTime(LocalDateTime.of(LocalDate.now(), LocalTime.of(19, 30)));
        movieSession.setCinemaHall(cinemaHall);

        movieSessionService.add(movieSession);
        movieSessionService.findAvailableSessions(movie.getId(), LocalDate.now())
                .forEach(System.out::println);

        User user = new User();
        user.setEmail("lok@gmail.com");
        user.setPassword("1234");
        user.setSalt("Here is some salt".getBytes());
        User register = authenticationService.register(user.getEmail(), "1234");
        authenticationService.login("lok@gmail.com", "1234");

        ShoppingCart shoppingCart = shoppingCartService.getByUser(register);
        shoppingCartService.addSession(movieSession, register);
    }
}
