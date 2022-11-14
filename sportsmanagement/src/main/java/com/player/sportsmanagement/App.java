/*
Project: Sport Management System
@Author: Prithwish Shaw
@Date: 14 November 2022 
 */

package com.player.sportsmanagement;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

//Main class
public class App 
{
	
	//Main method
    public static void main( String[] args ){
        
    	// Create Configuration
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.addAnnotatedClass(Player.class);
        
        //Create Session Factory
        SessionFactory sessionFactory = configuration.buildSessionFactory();
 
        // Initialize Session Object
        Session session = sessionFactory.openSession();
        
        //creating player object
        Player	player1= new Player();
        Player	player2= new Player();
        Player	player3= new Player();
        
        //setting the values of player1
        player1.setId(1);
        player1.setPlayerFirstName("Gautam");
        player1.setPlayerLastName("Gambhir");
        player1.setPlayerAge(41);
        player1.setPlayerAddress("New Delhi");
        player1.setPlayerCategory("Cricket");
        
        //setting the values of player2
        player2.setId(2);
        player2.setPlayerFirstName("Cristiano");
        player2.setPlayerLastName("Ronaldo");
        player2.setPlayerAge(37);
        player2.setPlayerAddress("Portugal");
        player2.setPlayerCategory("Football");
        
        //setting the values of player3
        player3.setId(3);
        player3.setPlayerFirstName("Neeraj");
        player3.setPlayerLastName("Chopra");
        player3.setPlayerAge(24);
        player3.setPlayerAddress("Haryana");
        player3.setPlayerCategory("Javeline Throw");
        
        //staring the transaction
        session.beginTransaction();
        
        //saving the values
        session.save(player1);
        session.save(player2);
        session.save(player3);
        
        //committing the transaction
        session.getTransaction().commit();
    }
}
