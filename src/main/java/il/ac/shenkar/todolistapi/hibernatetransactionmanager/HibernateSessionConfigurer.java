package il.ac.shenkar.todolistapi.hibernatetransactionmanager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateSessionConfigurer {

    private static final SessionFactory sessionFactory;
    private static HibernateSessionConfigurer hibernateSessionConfigurer;
    private HibernateSessionConfigurer(){}

    public static HibernateSessionConfigurer getInstance( ) {
        if(hibernateSessionConfigurer == null)
            hibernateSessionConfigurer = new HibernateSessionConfigurer();
        return hibernateSessionConfigurer;
    }


    static {
        try {
            Configuration configuration = new Configuration().configure();
            sessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static Session openSession() {
        return sessionFactory.openSession();
    }
}