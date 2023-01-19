package com.irinayanushkevich.restapi.repository.hib_rep_impl;

import com.irinayanushkevich.restapi.model.User;
import com.irinayanushkevich.restapi.repository.UserRepository;
import com.irinayanushkevich.restapi.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class HibUserRepositoryImpl implements UserRepository {

    private Transaction transaction;

    @Override
    public User create(User user) {
        try (Session session = HibernateUtil.openSession()) {
            transaction = session.beginTransaction();
            session.persist(user);
            Integer id = (Integer)session.getIdentifier(user);
            user.setId(id);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public User update(User user) {
        try (Session session = HibernateUtil.openSession()) {
            transaction = session.beginTransaction();
            session.merge(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public User getById(Integer id) {
        try (Session session = HibernateUtil.openSession()) {
            return session.createQuery("FROM User u LEFT JOIN FETCH u.events WHERE u.id =:user_id", User.class)
                    .setParameter("user_id", id)
                    .getSingleResult();
        }
    }

    @Override
    public void delete(Integer id) {
        try (Session session = HibernateUtil.openSession()) {
            transaction = session.beginTransaction();
            User user = session.get(User.class, id);
            session.remove(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAll() {
        try (Session session = HibernateUtil.openSession()) {
            return session.createQuery("FROM User", User.class).getResultList();
        }
    }
}
