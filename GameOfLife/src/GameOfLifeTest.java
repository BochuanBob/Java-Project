import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests GameOfLife.
 * 
 * @author lub, verlaqd. Created Sept. 29, 2015.
 */
public class GameOfLifeTest {
	/**
	 * Test method for {@link GameOfLife#GameOfLife(java.util.ArrayList)} and
	 * {@link GameOfLife#isOccupied(int, int)}.
	 */
	// from the TestThisClassTest
	private GameOfLife game;
	
	
	@Before
	public void setUp() throws Exception {
		ArrayList<Coordinates> coordList = new ArrayList<Coordinates>();
		coordList.add(new Coordinates(0, 0));
		coordList.add(new Coordinates(0, 1));
		coordList.add(new Coordinates(0, 89));
		this.game = new GameOfLife(coordList);
	}

	@After
	public void tearDown() throws Exception {
		this.game=null;
	}

	@Test
	public void testGameOfLife() {
		// Test constructor and isOccupied()

		/*
		 * Create a board with known points Ensure that two of the points are
		 * occupied Check two other random points, make sure those are empty
		 */
		

		assertTrue(this.game.isOccupied(0, 0));
		assertTrue(this.game.isOccupied(0, 1));
		assertTrue(this.game.isOccupied(0, 89));
		assertFalse(this.game.isOccupied(0, 10));
		assertFalse(this.game.isOccupied(50, 10));

	}

	/**
	 * Test method for {@link GameOfLife#getNeighborCount(int, int)}.
	 */
	@Test
	public void testGetNeighborCount() {
		// Test getNeighborCount

		// Create a board, check a known point with a known number of neighbors

		assertEquals(2, this.game.getNeighborCount(0, 0));
		assertEquals(1, this.game.getNeighborCount(0, 1));
		assertEquals(1, this.game.getNeighborCount(0, 89));
		assertEquals(3, this.game.getNeighborCount(89, 0));
		assertEquals(3, this.game.getNeighborCount(1, 0));
		assertEquals(2, this.game.getNeighborCount(89, 1));
	}

	/**
	 * Test method for {@link GameOfLife#nextGeneration()}.
	 */
	@Test
	public void testNextGen() {
		// Test nextGen

		/*
		 * Start with a known board. Call game.nextGeneration() to move to the
		 * next 'step'. Test the (known) next generation by seeing if the
		 * expected points are occupied and have the expected number of
		 * neighbors.
		 */

		this.game.nextGeneration();

		assertTrue(this.game.isOccupied(0, 0));
		assertTrue(this.game.isOccupied(1, 0));
		assertTrue(this.game.isOccupied(89, 0));
		assertFalse(this.game.isOccupied(0, 1));
		assertFalse(this.game.isOccupied(0, 89));
		assertFalse(this.game.isOccupied(0, 10));
		assertFalse(this.game.isOccupied(50, 10));

		assertEquals(2, this.game.getNeighborCount(0, 0));
		assertEquals(3, this.game.getNeighborCount(0, 1));
		assertEquals(3, this.game.getNeighborCount(0, 89));
		assertEquals(1, this.game.getNeighborCount(89, 0));
		assertEquals(1, this.game.getNeighborCount(1, 0));
		assertEquals(2, this.game.getNeighborCount(89, 1));

	}

}