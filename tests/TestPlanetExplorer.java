import static org.junit.Assert.*;

import org.junit.Test;

public class TestPlanetExplorer {

	@Test
	public void test_planetExplorer_setObjectCheckX() {
		PlanetExplorer planetExplorer = new PlanetExplorer(100, 100, "(1,1)");
		assertEquals(100, planetExplorer.gridSize.getX());		
	}
	
	@Test
	public void test_planetExplorer_setObjectCheckY() {
		PlanetExplorer planetExplorer = new PlanetExplorer(100, 100, "(1,1)");
		assertEquals(100, planetExplorer.gridSize.getY());		
	}
	
	@Test
	public void test_planetExplorer_setObjectCheckObstacles() {
		PlanetExplorer planetExplorer = new PlanetExplorer(100, 100, "(1,1)");
		assertEquals(1, planetExplorer.obstaclesArray.size());		
	}
	
	@Test
	public void test_planetExplorer_moveForwardFromStart() {
		PlanetExplorer planetExplorer = new PlanetExplorer(100, 100, "(1,1)");
		assertEquals("(0,1,N)", planetExplorer.executeCommand("f"));		
	}
	
	@Test
	public void test_planetExplorer_moveRightAndForward() {
		PlanetExplorer planetExplorer = new PlanetExplorer(100, 100, "(1,1)");
		assertEquals("(1,0,E)", planetExplorer.executeCommand("rf"));		
	}
	
	@Test
	public void test_planetExplorer_moveRightForwardLeftForward() {
		PlanetExplorer planetExplorer = new PlanetExplorer(100, 100, "(1,1)");
		assertEquals("(1,1,N)", planetExplorer.executeCommand("rflf"));		
	}
}
