package server.dao;


import server.dao.impl.util.HibernateDaoFactory;

public abstract class DaoFactory {
    private static final DaoFactory HIBERNATE_DAO_FACTORY = new HibernateDaoFactory();

    protected DaoFactory() {
        //empty constructor, nothing more to be added
    }

    public static DaoFactory getInstance() {
        return HIBERNATE_DAO_FACTORY;
    }

    public abstract CookieDao getCookieDao();

    public abstract UserDao getUserDao();

    public abstract OrderDao getOrderDao();

    public abstract CommentDao getCommentDao();
}
