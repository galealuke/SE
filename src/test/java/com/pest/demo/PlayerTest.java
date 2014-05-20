//package com.pest.demo;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class PlayerTest
{

	Player player;

	@Before
	public void setUp() throws Exception
	{
		player = new Player(new Position(10,5),new Team(), 1);
	}

	
	@Test
	public void testMove()
	{
		player.move('d');
		assertEquals(11,player.getPosition().x);
		player.move('u');
		assertEquals(10,player.getPosition().x);
		player.move('l');
		assertEquals(4,player.getPosition().y);
		player.move('r');
		assertEquals(5,player.getPosition().y);
	}

}