package server.dao.impl;


import api.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import server.dao.UserDao;
import server.dao.impl.util.HibernateUtil;

import java.util.List;

public class HibernateUserDao implements UserDao {
    private static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    @Override
    public User findById(long id) {
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        User user = currentSession.find(User.class, id);
        transaction.commit();
        return user;
    }

    @Override
    public User findByUsername(String username) {
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        Query<User> query = currentSession.createQuery("from User u where u.username=:param", User.class);
        query.setParameter("param", username);
        User user = query.getSingleResult();
        transaction.commit();
        return user;
    }

    @Override
    public void delete(User objectToDelete) {
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        currentSession.delete(objectToDelete);
        transaction.commit();
    }

    @Override
    public void update(User objectToUpdate) {
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        currentSession.update(objectToUpdate);
        transaction.commit();
    }

    @Override
    public void insert(User objectToCreate) {
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        currentSession.merge(objectToCreate);
        transaction.commit();
    }

    @Override
    public List<User> findAll() {
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        Query query = currentSession.createQuery("from User", User.class);
        List<User> users = query.list();
        transaction.commit();
        return users;
    }
}
