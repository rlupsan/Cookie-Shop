package server.dao.impl;


import api.model.Comment;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import server.dao.CommentDao;
import server.dao.impl.util.HibernateUtil;

public class HibernateCommentDao implements CommentDao {
    private static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    @Override
    public void save(Comment objectToCreate) {
        Session currentSession = sessionFactory.openSession();
        Transaction transaction = currentSession.beginTransaction();
        currentSession.save(objectToCreate);
        transaction.commit();
        currentSession.close();
    }
}
