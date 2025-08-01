package com.example.util;


import com.example.model.Match;
import com.example.model.Player;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;


public class HibernateUtil {
    private static final SessionFactory sessionFactory;

//    static {
//        try {
//            sessionFactory = new Configuration()
//                    .configure("hibernate.cfg.xml")
//                    .addAnnotatedClass(Player.class)
//                    .addAnnotatedClass(Match.class)
//                    .buildSessionFactory();
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new ExceptionInInitializerError("Failed to create SessionFactory object." + e);
//        }
//    }

    static {
        try {
            Configuration configuration = new Configuration().configure();
            configuration.addAnnotatedClass(Player.class);
            configuration.addAnnotatedClass(Match.class);
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        } catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        // Close caches and connection pools
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}
