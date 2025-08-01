package com.example.dao;

import com.example.model.Player;
import com.example.util.HibernateUtil;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.Optional;

public class PlayerDao {

    public Player save(Player player) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(player);
            transaction.commit();
            return player;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Error saving player", e);
        }
    }

    public Optional<Player> findByName(String name) {
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Query<Player> query = session.createQuery("FROM Player p WHERE p.name = :name", Player.class);
            query.setParameter("name", name);
            return query.uniqueResultOptional();
//            transaction.commit();
//            return player;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw new RuntimeException("Error finding player by name", e);
        }
    }
}
