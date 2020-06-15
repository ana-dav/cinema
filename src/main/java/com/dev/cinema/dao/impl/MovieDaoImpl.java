package com.dev.cinema.dao.impl;

import com.dev.cinema.dao.interfaces.MovieDao;
import com.dev.cinema.exception.DataProcessException;
import com.dev.cinema.model.Movie;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MovieDaoImpl implements MovieDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Movie add(Movie movie) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Long movieId = (Long) session.save(movie);
            transaction.commit();
            movie.setId(movieId);
            return movie;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessException("Can't insert movie entity", e);
        }
    }

    @Override
    public List<Movie> getAll() {
        try (Session session = sessionFactory.openSession()) {
            CriteriaQuery<Movie> criteriaQuery = session.getCriteriaBuilder()
                    .createQuery(Movie.class);
            criteriaQuery.from(Movie.class);
            return session.createQuery(criteriaQuery).getResultList();
        } catch (Exception e) {
            throw new DataProcessException("Error retrieving all movies", e);
        }
    }

    @Override
    public Movie getMovieById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Movie> criteriaQuery = criteriaBuilder
                    .createQuery(Movie.class);
            Root<Movie> sessionRoot = criteriaQuery.from(Movie.class);
            Predicate predicate = criteriaBuilder
                    .equal(sessionRoot.get("id"), id);
            criteriaQuery.select(sessionRoot).where(predicate);
            return session.createQuery(criteriaQuery).uniqueResult();
        } catch (Exception e) {
            throw new DataProcessException(
                    "An Error Occurred While Retrieving Movie by Id! " + id, e);
        }
    }
}
