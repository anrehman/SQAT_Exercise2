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
}
