package com.sportsmanagementsystem;

import java.util.List;

import com.dao.PlayerDao;
import com.entity.Player;
import com.entity.PlayerAddress;

public class App 
{
    public static void main( String[] args ){
    	
    	Player player1 = new Player("Gautam","Gambhir","Cricket");
    	PlayerAddress playerAddress1= new PlayerAddress("India","New Delhi");
    	playerAddress1.setPlayer(player1);
    	player1.setPlayerAddress(playerAddress1);
    	
    	Player player2 = new Player("Cristiano","Ronaldo","Football");
    	PlayerAddress playerAddress2 = new PlayerAddress("Europe","Portugal");
    	playerAddress2.setPlayer(player2);
    	player2.setPlayerAddress(playerAddress2);
 
    	PlayerDao playerDao = new PlayerDao();
    	playerDao.savePlayer(player1);
    	playerDao.savePlayer(player2);
    	
    	// Get all players
    	List<Player> players = playerDao.getAllPlayer();
        players.forEach(playerTemp -> System.out.println(playerTemp.getFirstName()));
       
    }
}
