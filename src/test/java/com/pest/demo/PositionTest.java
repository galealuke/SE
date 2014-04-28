import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class PositionTest {

	
	Player player;
	Position position;
	
	
	@Before
	public void setUp() throws Exception {
		player= new Player(0,0);
		position = new Position(1,5);
		
	}
	
	@Test
	public void testPosition() {
		player.setPosition(position);
		assertEquals(1, player.getPosition().x);
	}

}
