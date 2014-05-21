package com.pest.demo;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class GameTest
{

	Game game;

	SafeMap newMap = SafeMap.getInstance(50);

	Player player,player1;
	Team team = new Team();


	Position position1,position2,position3,position4,position5,position6,position7,position8;

	@Before
	public void setUp() throws Exception
	{
		game= new Game();
		
		position1=new Position(7,40);
		position2=new Position(0,0);
		position3=new Position(25,-1);
		position4=new Position(4,21);
		player = new Player(position1,team,1);
		
		game.noOfPlayers=3;
		game.noOfTeams = 2;
		game.teamArray = new Team[game.noOfTeams];
		for(int count =0;count<game.noOfTeams; count++ )
			game.teamArray[count]=new Team();
		game.createPlayers(newMap,3);
		
		newMap.square[7][40]='Z';
		newMap.square[0][0]='Y';
		newMap.square[4][21]='X';

	}

	@Test	
	public void createPlayersTest() 
	{

		assertEquals(true, game.createPlayers(newMap,4));
		
	}


	@Test
	public void moveChecksTest()
	{		
		assertEquals(true, game.moveChecks(position1,player, newMap));
		player.setPosition(position2);
		assertEquals(false, game.moveChecks(position2,player, newMap));
	}

	@Test
	public void outOfMapTest()
	{
		assertEquals(true, game.outOfMap(position1, newMap));
		assertEquals(false, game.outOfMap(position3, newMap));
	}


	@Test
	public void inWaterTest()
	{
		assertEquals(false, game.inWater(position2, newMap));
		assertEquals(true, game.inWater(position1, newMap));
		assertEquals(true, game.inWater(position4, newMap));
	}
	
	@Test
	public void selectMapTest()
	{
		assertEquals(true, game.selectMapCheck(1));
		assertEquals(true, game.selectMapCheck(2));
		assertEquals(false, game.selectMapCheck(4));
	}


	@Test
	public void setNumPlayersTest()
	{
		assertEquals(true, game.setNumPlayers(2));
		assertEquals(true, game.setNumPlayers(5));
		assertEquals(true, game.setNumPlayers(8));
		assertEquals(false, game.setNumPlayers(1));
		assertEquals(false, game.setNumPlayers(9));
		assertEquals(false, game.setNumPlayers(20));
	}

	@Test
	public void noOfTileTest()
	{
		assertEquals(false, game.noOfTiles(4, 2));
		assertEquals(true, game.noOfTiles(7, 2));
		assertEquals(true, game.noOfTiles(8, 5));
		assertEquals(false, game.noOfTiles(7, 8));
		assertEquals(true, game.noOfTiles(8, 8));
		assertEquals(true, game.noOfTiles(50, 8));
		assertEquals(false, game.noOfTiles(51, 8));
	}
	
	@Test
	public void setNumTeamsTest()
	{
		assertEquals(true, game.setNumTeams(1));
		assertEquals(false, game.setNumTeams(4));
	}
	
	@Test
	public void HTMLTest()
	{
		assertEquals("<td style = background-color:gray></td>", game.HTMLtile(player, newMap,4,4));
		player.addPositionVisited(position4);
		assertEquals("<td style = background-color:green></td>", game.HTMLtile(player, newMap,4,21));
		player.addPositionVisited(position1);
		player.setPosition(position4);
		assertEquals("<td style = background-color:yellow></td>", game.HTMLtile(player, newMap,7,40));
		player.addPositionVisited(position2);
		assertEquals("<td style = background-color:blue></td>", game.HTMLtile(player, newMap,0,0));
		assertEquals("<td style = background-color:green><img src = \"http://s1.postimg.org/6kjevoygr/player.png\"></td>", game.HTMLtile(player, newMap,4,21));
		
		
	}

}
