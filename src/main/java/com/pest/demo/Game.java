package com.pest.demo;

import java.util.*;
import java.io.*;
public class Game {

	 int noOfPlayers; 
     int tiles;
     int noOfTeams;
     Player [] playerArray;
     Team [] teamArray;
     
	public Game()  //game constructor
	{
		noOfPlayers=0;
		tiles = 0;
	}
   
    public void startGame()
    {
       
		int count =0;
        Scanner sc= new Scanner(System.in);
        int level=0;
        do
		{
        	System.out.println("Select the Map level (1-Safe , 2 - Hazardous)");
			level = sc.nextInt(); //check the user enters 1 or 2
		}while(!(selectMapCheck(level))); 
        int input = 0;
        do
      	{
        	System.out.println("Please enter the number of players (2-8):  ");
        	input=sc.nextInt(); //check the user enters correct no of players
      	}while(!(setNumPlayers(input)));  
        do
      	{
        	System.out.println("Please enter the number of teams (for single player enter the same ammount as players)");
        	input=sc.nextInt(); //check the user enters teams less than the ammount of players
      	}while(!(setNumTeams(input)));          
        do
		{
            System.out.println("Please enter the number of tiles : ");
			input = sc.nextInt();
		}while(!(noOfTiles(input,noOfPlayers))); // to keep looping until no of tiles is entered correctly depending on the number of players
		tiles = input;
		MapFactory mf = new MapFactory();
		       
        Map newMap = mf.getMap(level, tiles);
		
		teamArray = new Team[noOfTeams]; //create array of teams
		for(count =0;count<noOfTeams; count++ )
			teamArray[count]=new Team();
		
        createPlayers(newMap, noOfPlayers);	//create the array of players
							
		for(count =0;count<noOfPlayers; count++ ) 
		{		
			HTMLfiles(playerArray[count],newMap,count);		//generate all html files
		}
		
		askForDirections(playerArray , newMap); //ask each player for directions
			
		sc.close();
    }
    
    

	public boolean selectMapCheck(int level) //check the correct input is entered..
    {
    	if ((level == 1)||(level==2))
    		return true;
    	else
    		return false;
    }
	
	
	public boolean createPlayers(Map map, int noOfPlayers)
	{
		playerArray = new Player[8];
		int count = 0;
		for(count =0;count<noOfPlayers; count++ )  //create player objects
		{
			int teamNo = randomTeam(count);
			Subject tempTeam = teamArray[teamNo];
			Player player = new Player(map.randomGrassTile(),tempTeam,teamNo); //assign random team to a player
			playerArray[count]= player;		
		}
		
		for(count =0;count<noOfPlayers; count++ ) //show the starting position to all players
		{
			teamArray[playerArray[count].getTeam()].setState(playerArray[count].position);//this should update all the other maps
		}
		return true;
	}
	
	private int randomTeam(int n)  //method to return a random team in a way that all teams are used
	{
		Random rand = new Random();
		int team;
		if (n>=noOfTeams) //if all teams have been used at least once
		{
			team = rand.nextInt(noOfTeams);
		}
		else
		{
			team = rand.nextInt(noOfTeams);
			while(checkTeams(team, n)==true)//if not all teams have been used
			{	
				team = rand.nextInt(noOfTeams);
			}
			
		}
		return team;
	}

	public boolean checkTeams(int team, int playersUsed) //returns true if
	{
		for (int count = 0; count < playersUsed; count++)
		{
			if(playerArray[count].getTeam()==team)
				return true;
		}
		return false;
	}
    
    public void askForDirections(Player [] playerArray , Map map)
    {
    	boolean win=false;
		int endGame = 0;
		int count=0;
		
		do{
			for(count =0;count<noOfPlayers; count++ ) //to move each player in turns
			{
				System.out.println("Enter a direction you want to move, Player "+count+" from team "+playerArray[count].getTeam()+ "( l/r/u/d) : " );
				win = gamePlay(playerArray[count],map); 
				if (win == true) 
				{
					System.out.println("Player "+ count + " Wins!");
					endGame ++;
				}
				
				for(int i =0;i<noOfPlayers; i++ ) 
				{		
					HTMLfiles(playerArray[i],map,i);		
				}
				
			}
			
		}while(endGame==0); //to keep moving players until player(s) win
		System.out.println("The game has been won by "+endGame+" player(s)");
    }
    

	public boolean gamePlay(Player player , Map map) 
	{ 
	
		Scanner sc = new Scanner(System.in);
		char input='a';
		input = sc.next().charAt(0);
		sc.close();
		Position oldPosition = new Position((player.getPosition().x),(player.getPosition().y)); //save the old player position  
		player.move(input);  //change player position 

		return moveChecks(oldPosition, player, map);
	}
	
	public boolean moveChecks(Position oldPosition, Player player, Map map)
	{
		
		if(!(outOfMap(player.getPosition(),map))) //if player tries to move out of map  
		{
			player.setPosition(oldPosition);
			return gamePlay(player,map);
		}
		else if(!(inWater(player.getPosition(),map)))
		{	
			teamArray[player.getTeam()].setState(player.position);//this should update all the other maps
			player.setPosition(player.startingPosition); //if player falls in the water it starts again from the initial position
			return false;
		}
		else  //if it is safe to move
		{
			teamArray[player.getTeam()].setState(player.position);//this should update all the other maps
			if (map.getTileType((player.getPosition().x),(player.getPosition().y))=='Z'||map.getTileType((player.getPosition().x),(player.getPosition().y))=='C') //if player finds the treasure
			{
				return true;
			}
			else return false;
		}				
	}
	
	public boolean outOfMap(Position newPosition, Map map)
	{
			if(((newPosition.x)>map.getSize()-1)||((newPosition.x)<0)||((newPosition.y)>map.getSize()-1)||((newPosition.y)<0))
			{
				System.out.println("Error, Out of map, please enter direction again:");
				return false;
			}
			else
			{
				return true;
			}
	
	}
	
	public boolean inWater(Position newPosition, Map map)
	{
		 if (map.getTileType(newPosition.x,newPosition.y)=='Y'||map.getTileType(newPosition.x,newPosition.y)=='B')
		 {
			System.out.println("Oops! You fell in the water..");
					
			return false;
		 }
		 else
		 {
			return true;
		 }
	
	}
	
	
	public  void HTMLfiles(Player player, Map map, int number)  //HTML display 
	{
		PrintWriter out=null; 
		try
		{
			out = new PrintWriter("map__player_"+number+".html");  //linking printwriter object to output "map.html"
		}catch(Throwable exc)
		{
			System.out.println("File doesnt exist");
		}
		
		out.println("<!DOCTYPE html>" +"\n" +"<style> \ntable, th, td \n{ \nborder-collapse:collapse; \nborder:1px solid black;\n} \n td{\nheight:55px;\nwidth:55px;\n}\n</style>");  //at the start of every html document
				
		out.println("<table>"); //table start
		for (int i = 0; i < map.getSize(); i++) 
		{
		out.println("<tr>"); //for every row we print a row <tr>
			for (int j = 0; j < map.getSize(); j++) 
			{
				out.println(HTMLtile(player,map,i,j));							
			}
		out.println("</tr>");	//close each row
		}
		out.println("</table>"); //close the table
		out.close();	//save
	}
	
	public String HTMLtile(Player player, Map map, int i, int j)
	{
		String print=null;
		Position p = new Position(i,j);
		if (!(player.ifVisitedPosition(p))) 
		{
			print = "<td style = background-color:gray>"; //if XYZ then background gray
		} 	
		else if (map.getTileType(i,j)=='X')
			print = "<td style = background-color:green>";
		else if (map.getTileType(i,j)=='Y')
			print = "<td style = background-color:blue>";
		else if (map.getTileType(i,j)=='Z')
			print = "<td style = background-color:yellow>";
			
		if(((player.getPosition().x)==i)&&((player.getPosition().y)==j))
		{
			print = print+"<img src = \"http://s1.postimg.org/6kjevoygr/player.png\">";
		}
		print =print+"</td>";
		return print;
	}
	
	
	public boolean setNumPlayers(int n)
	{
		if (n < 2|| n >8)
		{
			return false;
		}
		else
		{
			noOfPlayers=n;
			return true;
		}
	
	}
	
	public boolean setNumTeams(int teams) {
		if (teams>noOfPlayers)
		{
			return false;
		}
		else
		{			
			noOfTeams=teams;
			return true;
		}
	}
    
	
	public boolean noOfTiles(int n, int noOfPlayers)
	{
		 if(noOfPlayers>1 && noOfPlayers < 5)
            {
                if (n < 5 || n >50)
					return false;
				else
					return true;
            }
			else  if(noOfPlayers>4 && noOfPlayers < 9)
            {
			
                if (n < 8 || n >50)
					return false;
				else
					return true; 
			 }     
			else
				return false;
	}
	

}