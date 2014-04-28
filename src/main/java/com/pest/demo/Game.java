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

        Map newMap = new Map(tiles);
        newMap.generate();

		Map [] mapArray = new Map[8];

		for(count =0;count<noOfPlayers; count++ )  //create a map for each player
		{
			mapArray[count]=new Map(newMap.size, newMap.square);
		}

		Player [] playerArray = new Player[8];

		for(count =0;count<noOfPlayers; count++ )  //create player objects
		{
			Player player = new Player(mapArray[count].randomGrassTile());
			playerArray[count]= player;
		}



		for(count =0;count<noOfPlayers; count++ )
		{

			HTMLfiles(playerArray[count],mapArray[count],count);

		}

		askForDirections(playerArray , mapArray); //ask each player for directions


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
