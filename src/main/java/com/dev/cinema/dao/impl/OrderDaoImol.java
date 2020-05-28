package com.dev.cinema.dao.impl;

import com.dev.cinema.dao.interfaces.OrderDao;
import com.dev.cinema.exception.DataProcessException;
import com.dev.cinema.lib.Dao;
import com.dev.cinema.model.Order;
import com.dev.cinema.model.User;
import com.dev.cinema.util.HibernateUtil;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

@Dao
public class OrderDaoImol implements OrderDao {
    @Override
    public Order add(Order order) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
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
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
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
}
