package server.dao.impl;


import api.model.Order;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import server.dao.OrderDao;
import server.dao.impl.util.HibernateUtil;

public class HibernateOrderDao implements OrderDao {
    private static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    @Override
    public void save(Order objectToCreate) {
        Session currentSession = sessionFactory.openSession();
        Transaction transaction = currentSession.beginTransaction();
        currentSession.save(objectToCreate);
        transaction.commit();
        currentSession.close();
    }

    @Override
    public void update(Order order) {
        Session currentSession = sessionFactory.openSession();
        Transaction transaction = currentSession.beginTransaction();
        currentSession.update(order);
        transaction.commit();
        currentSession.close();
    }

    @Override
    public Order findById(long id) {
        Session currentSession = sessionFactory.openSession();
        Transaction transaction = currentSession.beginTransaction();
        Order order = currentSession.find(Order.class, id);
        transaction.commit();
        currentSession.close();
        return order;
    }
}
