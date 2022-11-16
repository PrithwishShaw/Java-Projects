package com.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.entity.Player;
import com.util.HibernateUtil;

public class PlayerDao {

	/*
    * Save Player
    * @param player
    */
	public void savePlayer(Player player) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student object
            session.save(player);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
	
	/**
     * Update Player
     * @param player
     */
	public void updatePlayer(Player player) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student object
            session.update(player);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
	
	/**
     * Delete Player
     * @param id
     */
	 public void deletePlayer(int id) {

	        Transaction transaction = null;
	        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	            // start a transaction
	            transaction = session.beginTransaction();

	 

	            // Delete a instructor object
	            Player player = session.get(Player.class, id);
	            if (player != null) {
	                session.delete(player);
	                System.out.println("player is deleted");
	            }

	 

	            // commit transaction
	            transaction.commit();
	        } catch (Exception e) {
	            if (transaction != null) {
	                transaction.rollback();
	            }
	            e.printStackTrace();
	        }
	    }
	 
	 /**
	     * Get Player By ID
	     * @param id
	     * @return
	     */
	 public Player getPlayer(int id) {

	        Transaction transaction = null;
	        Player player = null;
	        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	            // start a transaction
	            transaction = session.beginTransaction();
	            // get an instructor object
	            player = session.get(Player.class, id);
	            // commit transaction
	            transaction.commit();
	        } catch (Exception e) {
	            if (transaction != null) {
	                transaction.rollback();
	            }
	            e.printStackTrace();
	        }
	        return player;
	    }
	 
	 /**
	     * Get all Players
	     * @return
	     */
	 @SuppressWarnings("unchecked")
	    public List<Player> getAllPlayer() {

	 

	        Transaction transaction = null;
	        List<Player> listOfPlayer = null;
	        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	            // start a transaction
	            transaction = session.beginTransaction();
	            // get an instructor object
	            
	            listOfPlayer = session.createQuery("from player").getResultList();
	            
	            // commit transaction
	            transaction.commit();
	        } catch (Exception e) {
	            if (transaction != null) {
	                transaction.rollback();
	            }
	            e.printStackTrace();
	        }
	        return listOfPlayer;
	    }
}
