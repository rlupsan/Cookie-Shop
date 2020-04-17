package server.dao.impl.util;


import server.dao.CommentDao;
import server.dao.CookieDao;
import server.dao.DaoFactory;
import server.dao.OrderDao;
import server.dao.UserDao;
import server.dao.impl.HibernateCommentDao;
import server.dao.impl.HibernateCookieDao;
import server.dao.impl.HibernateOrderDao;
import server.dao.impl.HibernateUserDao;

public class HibernateDaoFactory extends DaoFactory {

    @Override
    public CookieDao getCookieDao() {
        return new HibernateCookieDao();
    }

    @Override
    public UserDao getUserDao() {
        return new HibernateUserDao();
    }

    @Override
    public OrderDao getOrderDao() {
        return new HibernateOrderDao();
    }

    @Override
    public CommentDao getCommentDao() {
        return new HibernateCommentDao();
    }
}
