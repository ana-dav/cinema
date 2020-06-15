package com.dev.cinema.dao.impl;

import javax.persistence.criteria.*;
import com.dev.cinema.dao.interfaces.TicketDao;
import com.dev.cinema.exception.DataProcessException;
import com.dev.cinema.model.Ticket;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TicketDaoImpl implements TicketDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Ticket add(Ticket ticket) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(ticket);
            transaction.commit();
            return ticket;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessException("There was an error inserting ", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public Ticket getTicketById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Ticket> criteriaQuery = criteriaBuilder
                    .createQuery(Ticket.class);
            Root<Ticket> sessionRoot = criteriaQuery.from(Ticket.class);
            Predicate predicate = criteriaBuilder
                    .equal(sessionRoot.get("id"), id);
            sessionRoot.fetch("tickets", JoinType.LEFT);
            criteriaQuery.select(sessionRoot).where(predicate);
            return session.createQuery(criteriaQuery).getSingleResult();
        } catch (Exception e) {
            throw new DataProcessException(
                    "An Error Occurred While Retrieving ticket by Id! " + id, e);
        }
    }
}
