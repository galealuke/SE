package com.pest.demo;

public class MapFactory {

	public Map getMap(int level, int tiles)
	{

		if(level == 1)
		{
			return SafeMap.getInstance(tiles);
		}
		if(level == 2)
		{
			return HazardousMap.getInstance(tiles);
		}
		else return SafeMap.getInstance(tiles);
	}
}
