package com.example.util;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

public class AppInitializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Application starting up...");
        // Инициализируем Hibernate при запуске приложения
        HibernateUtil.getSessionFactory();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Application shutting down...");
        // Закрываем Hibernate при остановке приложения
        HibernateUtil.shutdown();
    }
}