package com.pest.demo;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MapTest {

	Map map;
	Map map1;
	Map map2;
	Map map3;
	Map map4;
	Position position;
	Position position1;
	Position position2;
	Position position3;
	Position position4;

	@Before
	public void setUp() throws Exception {
		map= new Map(20);
		map1= new Map(30);
		map2= new Map(49);
		map3= new Map(8);
		map4= new Map(23);
		map.generate();
		map1.generate();
		map2.generate();
		map3.generate();
		map4.generate();
		position = map.randomGrassTile();
		position1 = map1.randomGrassTile();
		position2 = map2.randomGrassTile();
	    position3 = map3.randomGrassTile();
		position4 = map4.randomGrassTile();
	}



	@Test
	public void randomGrassTileTest(){

		assertEquals('A', map.getTileType(position.x,position.y));
		assertEquals('A', map1.getTileType(position1.x,position1.y));
		assertEquals('A', map2.getTileType(position2.x,position2.y));
		assertEquals('A', map3.getTileType(position3.x,position3.y));
		assertEquals('A', map4.getTileType(position4.x,position4.y));

	}

}
