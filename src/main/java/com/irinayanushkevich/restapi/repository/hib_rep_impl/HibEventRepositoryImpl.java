package com.irinayanushkevich.restapi.repository.hib_rep_impl;

import com.irinayanushkevich.restapi.model.Event;
import com.irinayanushkevich.restapi.repository.EventRepository;
import com.irinayanushkevich.restapi.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class HibEventRepositoryImpl implements EventRepository {

    private Transaction transaction;

    @Override
    public Event create(Event event) {
        try (Session session = HibernateUtil.openSession()) {
            transaction = session.beginTransaction();
            session.persist(event);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return event;
    }

    @Override
    public Event update(Event event) {
        try (Session session = HibernateUtil.openSession()) {
            transaction = session.beginTransaction();
            session.merge(event);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return event;
    }

    @Override
    public Event getById(Integer id) {
        try (Session session = HibernateUtil.openSession()) {
            return session.get(Event.class, id);
        }
    }

    @Override
    public void delete(Integer id) {
        try (Session session = HibernateUtil.openSession()) {
            transaction = session.beginTransaction();
            Event event = session.get(Event.class, id);
            session.remove(event);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public List<Event> getAll() {
        try (Session session = HibernateUtil.openSession()) {
            return session.createQuery("FROM Event", Event.class).getResultList();
        }
    }
}
