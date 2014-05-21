import java.util.Random;


public class HazardousMap implements Map 
{

	int size;
    char[][] square;
    
    public HazardousMap()
    {
    	/*size = sizeInput;
    	square = new char[sizeInput][sizeInput];*/
    }
    
    private static HazardousMap instance = new HazardousMap();
	 
	public static HazardousMap getInstance(int sizeInput)
	{ 
		instance.generate(sizeInput);
		return instance;
	}
	
	@Override
	public int getSize() 
	{		
		return size;
	}
	
	@Override
	public char getTileType(int x, int y)
    {
		return square[x][y];
    }

	@Override
	public Position randomGrassTile()  //method to return a random starting position on grass
    {
        Random rand = new Random();
        Position randomPos;
		
        int x = rand.nextInt(size);
        int y = rand.nextInt(size);
        if (getTileType(x, y)=='X')
        { 
            randomPos = new Position(x,y);
		//	uncover(x,y);
        }
        else
        { 
            randomPos=randomGrassTile();      
        }
        return randomPos;
    } 

	@Override
	public void generate(int sizeInput) 
	{
		size = sizeInput; //Initialise variables instead of the constructor
    	square = new char[size][size];    	
    	int percentage=0;   	    	
    	percentage = generatePercentage(25,35);
    	generateMap(percentage);		
	}

	@Override
	public void generateMap(int percentage)
	{
		for (int i = 0; i < size; i++) 
        {
            for (int j = 0; j < size; j++) 
            {
                square[i][j] = 'X'; //fill the map with grass
            }
        }
    	
    	int actualFraction = percentage * (size*size)/100;
    	Random rand = new Random();
    	for (int i = 0; i<actualFraction; i++)		//create water tiles
    	{
    		int x = rand.nextInt(size);
    		int y = rand.nextInt(size);
    				square[x][y] = 'Y';
    		
    	}
    	createTreasure(0,0); 		
	}

	@Override
	public void createTreasure(int x, int y)
	{
		{
			Random rand = new Random();
			x = rand.nextInt(size);
			y = rand.nextInt(size);
	   			 
				if (getTileType(x,y)=='X')
					square[rand.nextInt(size)][rand.nextInt(size)] = 'Z';  //in the end, a random grass cell is changed to treasure				
				else
				{
					 createTreasure(x,y);
				}
		}		
	}

	@Override
	public int generatePercentage(int min, int max)
	{
		Random rand = new Random();
    	int percentage = min + rand.nextInt(max-min);
    	return percentage;    	
	}
	
}
