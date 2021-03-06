/*
 * Assignment - 3
 * 
 * TCSS 305 Autumn 2015
 */
package tests;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import model.Direction;
import model.Light;
import model.Terrain;
import model.Truck; 
import org.junit.Test;



/**
 * Test for the Truck Class. 
 * 
 * @author Jeremy Wolf email jwolf059@uw.edu
 * @version 22 October 2015
 */
public class TruckTest {

    
    /**
     * The number of times to repeat a test to ensure that the probability
     * of exploring all random options is sufficient.
     */
    private static final int RANDOMNESS_TRIES = 50;

    /**
     * Test method for {@link Truck#canPass(Terrain, Light)}.
     */
    @Test
    public void testCanPass() {

        final Truck canPassTruck = new Truck(6, 2, Direction.EAST);
        for (final Terrain testTerrain: Terrain.values()) {
            for (final Light testLight : Light.values()) {
                if (testTerrain == Terrain.LIGHT || testTerrain == Terrain.STREET) {
                    assertTrue("Trucks should pass " + testTerrain + " with a " + testLight 
                               + " light", canPassTruck.canPass(testTerrain, testLight));
                } else {
                    assertFalse("Trucks should not pass " + testTerrain + " with a " 
                                    + testLight + " light", 
                                    canPassTruck.canPass(testTerrain, testLight));
                }
            } 
        }
    }
    
    /**
     * Test method for {@link Truck#chooseDirection(Map)}.
     */
    @Test
    public void testChooseDirection() {
        final Map<Direction, Terrain> testNeighbors = new HashMap<Direction, Terrain>();
        testNeighbors.put(Direction.NORTH, Terrain.STREET);
        testNeighbors.put(Direction.WEST, Terrain.LIGHT);
        testNeighbors.put(Direction.SOUTH, Terrain.STREET);
        testNeighbors.put(Direction.EAST, Terrain.STREET);
        
        // Test with Three valid Directions.
        //   S
        // L   S
        //   S
        
        final Truck testTruck = new Truck(10, 10, Direction.EAST);

        boolean selectedSouth = false;
        boolean selectedNorth = false;
        boolean selectedEast = false;
        int tries = 0;
        while (tries < RANDOMNESS_TRIES) {
            tries += 1;
            final Direction testDir = testTruck.chooseDirection(testNeighbors);
            
            assertTrue("Should randomly choose NORTH, EAST, or SOUTH, but not WEST", 
                       testDir == Direction.NORTH || testDir == Direction.SOUTH 
                       || testDir == Direction.EAST);
            selectedSouth = selectedSouth || testDir == Direction.SOUTH;
            selectedNorth = selectedNorth || testDir == Direction.NORTH;
            selectedEast = selectedEast || testDir == Direction.EAST;
        }
        assertTrue("Truck randomness for Left, Right, Straight direction selection", 
                   selectedSouth && selectedNorth && selectedEast);
         
        // Test with two valid Directions.
        //   S
        // L   T
        //   S
        
        testNeighbors.put(Direction.EAST, Terrain.TRAIL); 
        int triesTest2 = 0;
        while (triesTest2 < RANDOMNESS_TRIES) {
            triesTest2 += 1;
            final Direction testDir = testTruck.chooseDirection(testNeighbors);
            
            assertTrue("Should randomly choose NORTH or SOUTH, but not EAST or WEST", 
                       testDir == Direction.NORTH || testDir == Direction.SOUTH); 
                       
            selectedSouth = selectedSouth || testDir == Direction.SOUTH;
            selectedNorth = selectedNorth || testDir == Direction.NORTH;
        }
        assertTrue("Truck randomness for Left or Right, but not Straight direction selection", 
                   selectedSouth && selectedNorth);
        
        // Test with one valid Directions.
        //   W
        // L   T
        //   S
        
        testNeighbors.put(Direction.NORTH, Terrain.WALL); 
        final Direction testDir = testTruck.chooseDirection(testNeighbors);
            
        assertSame("Should choose SOUTH, but not NORTH, EAST or WEST", 
                       testDir, Direction.SOUTH); 
        
        // Test with only Reverse being a valid selection.
        //   W
        // L   T
        //   G
        
        testNeighbors.put(Direction.SOUTH, Terrain.GRASS); 
        final Direction testDir2 = testTruck.chooseDirection(testNeighbors);
            
        assertSame("Should choose reverse direciton of WEST", 
                       testDir2, Direction.WEST); 

    }
}
   
