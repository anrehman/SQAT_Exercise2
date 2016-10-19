import static org.junit.Assert.*;

import org.junit.Test;

public class TestPlanetExplorer {

	@Test
	public void test_planetExplorer_setObjectCheckX() throws PlanetExplorerException {
		PlanetExplorer planetExplorer = new PlanetExplorer(100, 100, "(1,1)");
		assertEquals(100, planetExplorer.gridSize.getX());		
	}
	
	@Test
	public void test_planetExplorer_setObjectCheckY() throws PlanetExplorerException {
		PlanetExplorer planetExplorer = new PlanetExplorer(100, 100, "(1,1)");
		assertEquals(100, planetExplorer.gridSize.getY());		
	}
	
	@Test
	public void test_planetExplorer_setObjectCheckObstacles() throws PlanetExplorerException {
		PlanetExplorer planetExplorer = new PlanetExplorer(100, 100, "(1,1)");
		assertEquals(1, planetExplorer.obstaclesArray.size());		
	}
	
	@Test
	public void test_planetExplorer_moveForwardFromStart() throws PlanetExplorerException {
		PlanetExplorer planetExplorer = new PlanetExplorer(100, 100, "(1,1)");
		assertEquals("(0,1,N)", planetExplorer.executeCommand("f"));		
	}
	
	@Test
	public void test_planetExplorer_moveRightAndForward() throws PlanetExplorerException {
		PlanetExplorer planetExplorer = new PlanetExplorer(100, 100, "(2,1)");
		assertEquals("(1,0,E)", planetExplorer.executeCommand("rf"));		
	}
	
	@Test
	public void test_planetExplorer_moveRightForwardLeftForward() throws PlanetExplorerException {
		PlanetExplorer planetExplorer = new PlanetExplorer(100, 100, "(2,2)");
		assertEquals("(1,1,N)", planetExplorer.executeCommand("rflf"));		
	}
	
	@Test
	public void test_planetExplorer_moveRightForwardBackward() throws PlanetExplorerException {
		PlanetExplorer planetExplorer = new PlanetExplorer(100, 100, "(1,1)");
		assertEquals("(0,0,E)", planetExplorer.executeCommand("rfb"));		
	}
	
	@Test
	public void test_planetExplorer_moveRightForwardLeftForwardObstacleFound() throws PlanetExplorerException {
		PlanetExplorer planetExplorer = new PlanetExplorer(100, 100, "(1,1)");
		assertEquals("(1,0,N)(1,1)", planetExplorer.executeCommand("rflf"));		
	}
	
	@Test
	public void test_planetExplorer_moveForwardForwardRightForward() throws PlanetExplorerException {
		PlanetExplorer planetExplorer = new PlanetExplorer(100, 100, "(1,1)");
		assertEquals("(1,2,E)", planetExplorer.executeCommand("ffrf"));		
	}
	
	@Test
	public void test_planetExplorer_moveForwardForwardRightForwardWithObstacle() throws PlanetExplorerException {
		PlanetExplorer planetExplorer = new PlanetExplorer(100, 100, "(0,2)");
		assertEquals("(1,1,E)(0,2)", planetExplorer.executeCommand("ffrf"));		
	}
	

	@Test
	public void test_planetExplorer_moveRightForwardLeftForwardLeftForward() throws PlanetExplorerException {
		PlanetExplorer planetExplorer = new PlanetExplorer(100, 100, "(0,2)");
		assertEquals("(0,1,W)", planetExplorer.executeCommand("rflflf"));		
	}
	@Test (expected = PlanetExplorerException.class)
	public void test_planetExplorer_moveRightBackward() throws PlanetExplorerException {
		PlanetExplorer planetExplorer = new PlanetExplorer(100, 100, "(0,2)");
		 planetExplorer.executeCommand("rb");		
	}
	
	@Test (expected = PlanetExplorerException.class)
	public void test_planetExplorer_moveOutofBoundaryException() throws PlanetExplorerException {
		PlanetExplorer planetExplorer = new PlanetExplorer(2, 2, "(2,2)");
		 planetExplorer.executeCommand("ffff");		
	}
	
}
