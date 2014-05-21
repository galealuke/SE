package com.pest.demo;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class HazardousMapTest
{
	HazardousMap newMap = HazardousMap.getInstance(10);

	Position position;
	

	@Before
	public void setUp() throws Exception
	{
		position = newMap.randomGrassTile();
	}

	@Test
	public void testGetSize()
	{
		assertEquals((newMap.size), newMap.getSize());
	}


	@Test
	public void randomGrassTileTest()
	{
		
		assertEquals('X', newMap.getTileType(position.x,position.y));

	}

	

}