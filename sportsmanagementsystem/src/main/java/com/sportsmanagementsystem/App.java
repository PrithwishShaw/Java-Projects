package com.sportsmanagementsystem;


import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.dao.PlayerAddressDao;
import com.dao.PlayerDao;
import com.entity.Player;
import com.entity.PlayerAddress;
import com.util.HibernateUtil;

public class App 
{
    public static void main( String[] args ){
    	
    	Player player1 = new Player("Gautam","Gambhir","Cricket");
    	PlayerAddress playerAddress1= new PlayerAddress("India","New Delhi");
    	
    	Player player2 = new Player("Cristiano","Ronaldo","Football");
    	PlayerAddress playerAddress2 = new PlayerAddress("Europe","Portugal");
 
    	PlayerDao playerDao = new PlayerDao();
    	playerDao.savePlayer(player1);
    	playerDao.savePlayer(player2);
    	
    	PlayerAddressDao playerAddressDao = new PlayerAddressDao();
    	playerAddressDao.savePlayerAddress(playerAddress1);
    	playerAddressDao.savePlayerAddress(playerAddress2);
    	
    	List<Player> list1= new ArrayList<Player>();
    	List<PlayerAddress> list2= new ArrayList<PlayerAddress>();
       
    	list1.add(player1);
    	list1.add(player2);
    	
    	list2.add(playerAddress1);
    	list2.add(playerAddress2);
    	
    	player1.setPlayerAddress(list2);
    	playerAddress1.setPlayer(list1);
    
    }
}
