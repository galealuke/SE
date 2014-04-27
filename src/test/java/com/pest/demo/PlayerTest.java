package com.pest.demo;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class PlayerTest {

	Player player;
	Position position;
	Position position0;

	@Before
	public void setUp() throws Exception {
		player= new Player(0,0);
		position = new Position(10,5);
		position0 = new Position(0,0);

	}

	@Test
	public void testSetPosition() {
		player.setPosition(position);
		assertEquals(10, player.getPosition().x);
		assertEquals(5, player.getPosition().y);
	}

	@Test
	public void testGetPosition() {
		assertEquals((player.position).x, player.getPosition().x);
	}

	@Test
	public void testMove() {
		player.move('d');
		assertEquals(1,player.getPosition().x);
	}

}
