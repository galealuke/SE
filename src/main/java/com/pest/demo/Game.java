public class Game {

	 int noOfPlayers;
     int tiles;

	public Game()  //game constructor
	{
		noOfPlayers=0;
		tiles = 0;
	}


	public void startGame()
    {
       
		int count =0;
        Scanner sc= new Scanner(System.in);
        System.out.println("Please enter the number of players (2-8):  ");
        int input=sc.nextInt();
        if (!setNumPlayers(input)) //to make sure that input is between 2 and 8 players
        {
            startGame();
        }
        else //if the no of players is entered correctly
        {
            do
			{
            	System.out.println("Please enter the number of tiles : ");
				input = sc.nextInt();
			}while(!(noOfTiles(input,noOfPlayers))); // to keep looping until no of tiles is entered correctly depending on the number of players
			tiles = input;
        } 
        Map newMap = new Map(tiles);        
        newMap.generate();
		
		Map [] mapArray = copyMaps(newMap,noOfPlayers);
						
		Player [] playerArray = createPlayers(mapArray, noOfPlayers);		
							
		for(count =0;count<noOfPlayers; count++ ) 
		{		
			HTMLfiles(playerArray[count],mapArray[count],count);		
		}
		
		askForDirections(playerArray , mapArray); //ask each player for directions
			
		
    }
	
	public Map[] copyMaps(Map newMap, int NoOfPlayers)
	{
		Map [] mapArray = new Map[8];
		int count=0;
		for(count =0;count<noOfPlayers; count++ )  //create a map for each player
		{
			mapArray[count]=new Map(newMap.size, newMap.square);
		}
		
		return mapArray;
	}
	
	public Player[] createPlayers(Map [] mapArray, int NoOfPlayers)
	{
		Player [] playerArray = new Player[8];
		int count = 0;
		for(count =0;count<noOfPlayers; count++ )  //create player objects
		{
			Player player = new Player(mapArray[count].randomGrassTile());
			playerArray[count]= player;		
		}
		return playerArray;
	}
    
    public void askForDirections(Player [] playerArray , Map [] mapArray)
    {
    	boolean win=false;
		int endGame = 0;
		int count=0;
		
		do{
			for(count =0;count<noOfPlayers; count++ ) //to move each player in turns
			{
				System.out.println("Enter a direction you want to move, Player "+count+"( l/r/u/d) : " );
				win = gamePlay(playerArray[count],mapArray[count]); 
				if (win == true) 
				{
					System.out.println("Player "+ count + " Wins!");
					endGame ++;
				}
				HTMLfiles(playerArray[count],mapArray[count],count);
			}
			
		}while(endGame==0); //to keep moving players until player(s) win
		System.out.println("The game has been won by "+endGame+" players");
    }

    //do gamePlay
	
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
	
		if (map.getTileType(i,j)=='X' || map.getTileType(i,j)=='Y' || map.getTileType(i,j)=='Z')
			print = "<td style = background-color:grey>"; //if XYZ then background gray
		if (map.getTileType(i,j)=='A')
			print = "<td style = background-color:green>";
		if (map.getTileType(i,j)=='B')
			print = "<td style = background-color:blue>";
		else if (map.getTileType(i,j)=='C')
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

		if (n < 2 || n >8)
		{
			return false;
		}
		else
		{
			noOfPlayers=n;
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
