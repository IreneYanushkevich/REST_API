package com.irinayanushkevich.restapi.repository.hib_rep_impl;

import com.irinayanushkevich.restapi.model.File;
import com.irinayanushkevich.restapi.repository.FileRepository;
import com.irinayanushkevich.restapi.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class HibFileRepositoryImpl implements FileRepository {

    private Transaction transaction;

    @Override
    public File create(File file) {
        try (Session session = HibernateUtil.openSession()) {
            transaction = session.beginTransaction();
            session.persist(file);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return file;
    }

    @Override
    public File update(File file) {
        try (Session session = HibernateUtil.openSession()) {
            transaction = session.beginTransaction();
            session.merge(file);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return file;
    }

    @Override
    public File getById(Integer id) {
        try (Session session = HibernateUtil.openSession()) {
            return session.get(File.class, id);
        }
    }

    @Override
    public void delete(Integer id) {
        try (Session session = HibernateUtil.openSession()) {
            transaction = session.beginTransaction();
            File file = session.get(File.class, id);
            session.remove(file);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public List<File> getAll() {
        try (Session session = HibernateUtil.openSession()) {
            return session.createQuery("FROM File", File.class).getResultList();
        }
    }
}
