package com.example.dao;

import com.example.model.Match;
import com.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class MatchDao {
    public Match save(Match match){
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            session.save(match);
            transaction.commit();
            return match;
        } catch (Exception e) {
            if (transaction != null){
                transaction.rollback();
            }
            throw new RuntimeException("Error saving match", e);
        }
    }

    public List<Match> findAll(String playerName, int page, int pageSize){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            Query<Match> query = session.createQuery("FROM Match m WHERE m.player1.name = :playerName OR m.player2.name = :playerName ORDER BY m.id DESC", Match.class);
            query.setParameter("playerName", playerName);
            query.setFirstResult((page - 1) * pageSize);
            query.setMaxResults(pageSize);
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Error finding matches by player name", e);
        }
    }

    public long countAll(){
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Long> query = session.createQuery("SELECT COUNT(m) FROM Match m", Long.class);
            return query.getSingleResult();
        } catch (Exception e){
            throw new RuntimeException("Error counting matches", e);
        }
    }

    public long countByPlayerName(String playerName) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            Query<Long> query = session.createQuery(
                    "SELECT COUNT(m) FROM Match m WHERE m.player1.name = :playerName OR m.player2.name = :playerName", Long.class
            );
            query.setParameter("playerName", playerName);
            return query.getSingleResult();
        } catch (Exception e) {
            throw new RuntimeException("Error counting matches by player name", e);
        }
    }
}
