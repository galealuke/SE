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

	 public Map(int sizeInput, char[][] squareInput) //this constructor is needed to copy another map
	 {

		 square = new char[sizeInput][sizeInput];
		 for (int i = 0; i < sizeInput; i++)
		 {
			 for (int j = 0; j < sizeInput; j++)
			 {
				 square[i][j]=squareInput[i][j];
			 }
		 }
		 size = sizeInput;
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
		Random rand = new Random();
		for (int i = 0; i < size; i++) //loop for the rows
        {
            for (int j = 0; j < size; j++) //loop for the columns
            {
                square[i][j] = randomChar(); //each cell is filled with water or grass
            }
        }
		square[rand.nextInt(size)][rand.nextInt(size)] = 'Z';  //in the end, a random cell is changed to treasure
    }




	public char randomChar()  //function to generate a random character
	{
		Random rand = new Random();
		int n = rand.nextInt(4); //pick random number from 0 to 1
		switch (n){
			case 0:
				return 'X';
			case 1:
				return 'Y';
			default:
				return 'X';
		}
    }
	
	public Position randomGrassTile()  //method to return a random starting position on grass
    {
        Random rand = new Random();
        Position randomPos;
		
        int x = rand.nextInt(size);
        int y = rand.nextInt(size);
        if (getTileType(x, y)=='X')
        { 
            randomPos = new Position(x,y);
			uncover(x,y);
        }
        else
        { 
            randomPos=randomGrassTile();      
        }
        return randomPos;
    } 
	
	public char uncover(int x, int y)
	{
		char letter= 'A';
		if (square[x][y] == 'X'){
			square[x][y] = 'A'; 
			 letter ='A';
		}
		if (square[x][y] == 'Y'){
			square[x][y] = 'B'; 
			letter ='B';
		}
		if (square[x][y] == 'Z'){
			square[x][y] = 'C';
			letter ='C';
		}
		return letter;
	}

	public Position randomGrassTile()  //method to return a random starting position on grass
    {
        Random rand = new Random();
        Position randomPos;

        int x = rand.nextInt(size);
        int y = rand.nextInt(size);
        if (getTileType(x, y)=='X')
        {
            randomPos = new Position(x,y);
			uncover(x,y);
        }
        else
        {
            randomPos=randomGrassTile();
        }
        return randomPos;
    }

	public char uncover(int x, int y)
	{
		char letter= 'A';
		if (square[x][y] == 'X'){
			square[x][y] = 'A';
			 letter ='A';
		}
		if (square[x][y] == 'Y'){
			square[x][y] = 'B';
			letter ='B';
		}
		if (square[x][y] == 'Z'){
			square[x][y] = 'C';
			letter ='C';
		}
		return letter;
	}
}
