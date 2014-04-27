public class Game {

	 int noOfPlayers; 
     int tiles;
   
	public Game()  //game constructor
	{
		noOfPlayers=0;
		tiles = 0;
	}

	
	public void startGame() //method that initializes the game
    {
	
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
	
	//create maps and player objects..
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