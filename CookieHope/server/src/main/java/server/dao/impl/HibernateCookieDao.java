package server.dao.impl;

import api.model.Cookie;
import api.model.CookieType;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import server.dao.CookieDao;
import server.dao.impl.util.HibernateUtil;

import java.util.List;

public class HibernateCookieDao implements CookieDao {
    private static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    @Override
    public List<Cookie> findAll() {
        List cookies;
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        Query query = currentSession.createQuery("from Cookie c");
        cookies = query.list();
        transaction.commit();

        return cookies;
    }

    @Override
    public Cookie findByType(CookieType type) {
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        Cookie cookie = currentSession.get(Cookie.class, type);
        transaction.commit();

        return cookie;
    }

    @Override
    public Cookie findByPrice(double price) {
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        Cookie cookie = currentSession.get(Cookie.class, price);
        transaction.commit();

        return cookie;
    }

    @Override
    public Cookie findByQuantityOfSweeteners(double quantityOfSweeteners) {
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        Cookie cookie = currentSession.get(Cookie.class, quantityOfSweeteners);
        transaction.commit();

        return cookie;
    }

    @Override
    public Cookie findById(long id) {
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        Cookie cookie = currentSession.get(Cookie.class, id);
        transaction.commit();
        return cookie;
    }

    @Override
    public void delete(Cookie objectToDelete) {
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        currentSession.delete(objectToDelete);
        transaction.commit();

    }

    @Override
    public void update(Cookie objectToUpdate) {
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        currentSession.update(objectToUpdate);
        currentSession.flush();
        transaction.commit();

    }

    @Override
    public void insert(Cookie objectToCreate) {
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        currentSession.merge(objectToCreate);
        transaction.commit();

    }
}
