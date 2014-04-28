//package com.pest.demo;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class GameTest {

	Game game;

	Map map,map1,map2,map3,map4;

	Player player,player1;


	Position position1,position2,position3,position4,position5,position6,position7,position8;

	@Before
	public void setUp() throws Exception {
		game= new Game();
		map = new Map(20);
		position1=new Position(5,10);
		position2=new Position(0,0);
		position3=new Position(25,-1);
		position4=new Position(4,21);

		map1 = new Map(40);
		position5=new Position(4,4);
		map1.square[4][4]='B';

		map2 = new Map(50);
		position6=new Position(7,40);
		map2.square[7][40]='A';

		map3 = new Map(40);
		player=new Player(2,4);
		map3.square[2][4]='Z';
		player1=new Player(2,4);

	}


	@Test	
	public void gamePlayTest() 
	{
		//assertEquals(true, game.gamePlay(player, map3));
		//assertEquals(false, game.gamePlay(player, map4));


	}


	@Test	
	public void moveChecksTest() 
	{
		assertEquals(true, game.moveChecks(position1,player1, map3));
		assertEquals(false, game.moveChecks(position1,player1, map2));

	}
	
	@Test	
	public void outOfMapTest() 
	{
		assertEquals(true, game.outOfMap(position1, map));
		assertEquals(true, game.outOfMap(position2, map));
		assertEquals(false, game.outOfMap(position3, map));
		assertEquals(false, game.outOfMap(position4, map));

	}


	@Test	
	public void inWaterTest() 
	{
		assertEquals(false, game.inWater(position5, map1));
		assertEquals(true, game.inWater(position6, map2));

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
	public void HTMLTest() 
	{
		assertEquals("<td style = background-color:blue></td>", game.HTMLtile(player, map1,4,4));
		assertEquals("<td style = background-color:green></td>", game.HTMLtile(player, map2,7,40));
		assertEquals("<td style = background-color:gray><img src = \"http://s1.postimg.org/6kjevoygr/player.png\"></td>", game.HTMLtile(player, map3,2,4));
	} 

}