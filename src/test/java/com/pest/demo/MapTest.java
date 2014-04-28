package com.pest.demo;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MapTest {

	Map map,map1,map2,map3,map4,map5,map6;

	Position position, position1, position2, position3, position4, position5, position6;

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
		
		map5 = new Map(50);
		position5=new Position(7,40);
		map5.square[7][40]='X';
		
		map6 = new Map(50);
		position6=new Position(5,4);
		map6.square[5][4]='Y';
	}

	@Test
	public void testGetSize() {
		assertEquals((map.size), map.getSize());
		assertEquals((map2.size), map2.getSize());
		assertEquals((map3.size), map3.getSize());
	}


	@Test
	public void randomGrassTileTest(){

		assertEquals('A', map.getTileType(position.x,position.y));
		assertEquals('A', map1.getTileType(position1.x,position1.y));
		assertEquals('A', map2.getTileType(position2.x,position2.y));
		assertEquals('A', map3.getTileType(position3.x,position3.y));
		assertEquals('A', map4.getTileType(position4.x,position4.y));

	}
	
	@Test
	public void uncoverTest(){

		assertEquals('A', map5.uncover(7,40));
		assertEquals('B', map6.uncover(5,4));

	}

}
