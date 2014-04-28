//package com.pest.demo;

public class Player
{


	Position position;

	Position startingPosition;

	public Player(Position positionInput){
		position = positionInput;
		startingPosition = positionInput;
	}

	public Player(int x, int y){
		position = new Position(x,y);
		startingPosition = new Position(x,y);
	}


	public boolean setPosition(Position p)
	{
		position = p;
		return true;
	}

	public Position getPosition()
	{
		return position;
	}

}
