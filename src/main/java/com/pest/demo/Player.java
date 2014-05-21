//package com.pest.demo;
import java.util.*;

public class Player extends Observer
{

	
	Position position;
	
	Position startingPosition;
	
	int team;
	Subject subject;
	
	 ArrayList<Position> positionsVisited;
	 
	public Player(Position positionInput,Subject team, int teamNo)
	{
		position = positionInput;
		startingPosition = positionInput;
		positionsVisited=new ArrayList<Position>();
		//addPositionVisited(startingPosition); 
		this.team = teamNo;	
		this.subject = team;
		this.subject.attach(this);
	}

	/*public Player(int x, int y){
		position = new Position(x,y);
		startingPosition = new Position(x,y);
		positionsVisited=new ArrayList<Position>();
		addPositionVisited(startingPosition); 
	}*/
		

	public boolean setPosition(Position p)
	{
		position = p;
		return true;
	}
	
	public Position getPosition()
	{
		return position;
	}
	
	public int getTeam()
	{
		return team;
	}
	
	public void addPositionVisited(Position P)
	{
		positionsVisited.add(P);
	}
	
	public boolean ifVisitedPosition(Position P)
	{
		for (int i=0; i<positionsVisited.size(); i++)
		{
			Position newPos = positionsVisited.get(i);
			if ((P.x==newPos.x)&&(P.y==newPos.y))
				return true;
		}
		return false;
	}
	
	public void move( char direction ) 
	{
	Position newPosition;
		switch (direction)
		{
			case 'l': //when direction entered is l
				newPosition = new Position((position.x),(position.y)-1); 
				setPosition(newPosition);
				break;
						
				
			case 'r': //when direction entered is r
				newPosition = new Position((position.x),(position.y)+1);
				setPosition(newPosition);
				break;
				
			case 'u': //when direction entered is u
				newPosition = new Position((position.x-1),(position.y));
				setPosition(newPosition);
				break;
				
			case 'd': //when direction entered is d
				newPosition = new Position((position.x+1),(position.y));
				setPosition(newPosition);
				break;
								
			default:
				break;
		}
	}

	@Override
	public void update(Position pos) {
		addPositionVisited(pos);
		
	}
}
