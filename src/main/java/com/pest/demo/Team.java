package com.pest.demo;
import java.util.*;


public class Team implements Subject
{
	private List<Observer> observers = new ArrayList<Observer>();
	private Position state;
	
	@Override
	public void setState(Position state) 
	{
		this.state = state;	
		notifyAllObservers(state);
	}

	@Override
	public void attach(Observer observer)
	{
		observers.add(observer);			
	}

	@Override
	public void notifyAllObservers(Position pos) 
	{
		for (Observer observer : observers) 
		{
	         observer.update(pos);
		}
	}

}
