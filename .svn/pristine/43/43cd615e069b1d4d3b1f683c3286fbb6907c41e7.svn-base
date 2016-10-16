/*
 * Assignment - 3
 * 
 * TCSS 305 Autumn 2015
 */
package model;

import java.util.Map;
/**
 * Creates a Car vehicle object. This class extends the AbstractVehicle class and
 * implements the methods canPass and chooseDirection. The car prefers to drive straight
 * ahead on the street and through lights if it can. If it cannot go straight it will try
 * to turn left, if it cannot turn left then if possible it will turn right and as a last
 * resort it can turn around. The car only stops for red lights. 
 * 
 * @author Jeremy Wolf email jwolf059@uw.edu
 * @version 21 October 2015
 */

public class Car extends AbstractVehicle {
    
    /**
     * Field to store the DeathTime for the Car object.
     */
    public static final int DEATHTIME = 5;

    /**
     * Constructor for a Car vehicle object. 
     * 
     * @param theX the starting X coordinate.
     * @param theY the starting Y coordinate.
     * @param theDir the starting direction.
     */
    public Car(final int theX, final int theY, final Direction theDir) {
        
        super(theX, theY, theDir, DEATHTIME);
        
    }
    
    /**
     * Returns if or if not this Car may move onto the given type of
     * terrain, when the street lights are the given color.
     * 
     * @param theTerrain The terrain.
     * @param theLight The light color.
     * @return whether or not the Car may move onto the given type of
     *         terrain when the street lights are the given color.
     */
    @Override
    public boolean canPass(final Terrain theTerrain, final Light theLight) {
        final boolean goodTerrain = theTerrain == (Terrain.STREET);
        final boolean goodTerrain2 = theTerrain == Terrain.LIGHT && theLight != Light.RED;

         
        return goodTerrain || goodTerrain2; 
    }
    
    /**
     * Returns the direction this vehicle would like to move, based on the given
     * map of the neighboring terrain.  The car prefers to drive straight ahead on the 
     * street and through lights if it can. If it cannot go straight it will try
     * to turn left, if it cannot turn left then if possible it will turn right and 
     * as a last resort it can turn around. The car only stops for red lights. 
     * 
     * @param theNeighbors The map of neighboring terrain.
     * @return the direction this vehicle would like to move.
     */
    @Override
    public Direction chooseDirection(final Map<Direction, Terrain> theNeighbors) {
        
        Direction newDirection;
        final Light colorLight = Light.GREEN;
        final Direction leftSide = getDirection().left();
        final Direction rightSide = getDirection().right();
        final Direction straightAhead = getDirection();
        final boolean canTurnLeft = canPass(theNeighbors.get(leftSide), colorLight);
        final boolean canTurnRight = canPass(theNeighbors.get(rightSide), colorLight);
        final boolean canGoStraight = canPass(theNeighbors.get(straightAhead), colorLight);

        if (canGoStraight) {
            newDirection = getDirection();
            
        } else if (canTurnLeft) {
            newDirection = leftSide;
            
        } else if (canTurnRight) {
            newDirection = rightSide;
            
        } else {
            newDirection = getDirection().reverse();
        }
        return newDirection;
    }

}
