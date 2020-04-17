package server.dao.impl.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.logging.Level;
import java.util.logging.Logger;

public final class HibernateUtil {

    private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private static SessionFactory sessionFactory = null;

    static {
        try {
            sessionFactory = buildSessionFactory();
        } catch (Exception e) {
            logger.log(Level.ALL, "Problem in declaring session factory.", e);
        }
    }

    private HibernateUtil() {
        //only empty
    }

    private static SessionFactory buildSessionFactory() throws Exception {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        MetadataSources sources = new MetadataSources(registry);
        Metadata metadata = sources.getMetadataBuilder().build();
        return metadata.getSessionFactoryBuilder().build();
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                sessionFactory = buildSessionFactory();
            } catch (Exception e) {
                logger.log(Level.ALL, "Problem in getting session factory", e);
            }
        }
        return sessionFactory;
    }
}