package com.dev.cinema.dao.impl;

import com.dev.cinema.dao.interfaces.OrderDao;
import com.dev.cinema.exception.DataProcessException;
import com.dev.cinema.model.Order;
import com.dev.cinema.model.User;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDaoImol implements OrderDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Order add(Order order) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(order);
            transaction.commit();
            return order;
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
    public List<Order> getOrderHistory(User user) {
        try (Session session = sessionFactory.openSession()) {
            Query<Order> query = session.createQuery(
                    "select distinct o from Order o "
                            + "left join fetch o.tickets Ticket "
                            + "where o.user =: user", Order.class);
            query.setParameter("user", user);
            return query.getResultList();
        } catch (Exception e) {
            throw new DataProcessException("There was an error getting order by user ", e);
        }
    }

    @Override
    public Order getOrderById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Order> criteriaQuery = criteriaBuilder
                    .createQuery(Order.class);
            Root<Order> sessionRoot = criteriaQuery.from(Order.class);
            Predicate predicate = criteriaBuilder
                    .equal(sessionRoot.get("id"), id);
            criteriaQuery.select(sessionRoot).where(predicate);
            return session.createQuery(criteriaQuery).uniqueResult();
        } catch (Exception e) {
            throw new DataProcessException(
                    "An Error Occurred While Retrieving Order by Id! " + id, e);
        }
    }
}
