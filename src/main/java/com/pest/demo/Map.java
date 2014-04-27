package com.pest.demo;

import java.util.*;
public class Map
{
    int size;
    char[][] square;
	
	public Map(int sizeInput) //constructor
    {
        size = sizeInput;
		square = new char[sizeInput][sizeInput];
		
    }
	
	 
	public int getSize()
    {               
        return size;
    }
    
    public char getTileType(int x, int y)
    {
		return square[x][y];
    }
	
	 public void generate()  //random generation of the map
    { 
		//
	}
	
	
}