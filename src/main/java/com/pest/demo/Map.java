
public interface Map
{	
	public int getSize();
	public char getTileType(int x, int y);
	public Position randomGrassTile();
	public void generate(int sizeInput);
	public void generateMap(int percentage);
	public void createTreasure(int x, int y);
	public int generatePercentage(int min, int max);
}
