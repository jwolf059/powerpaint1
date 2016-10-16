/*
 * Assignment - 3
 * 
 * TCSS 305 Autumn 2015
 */
package model;

import java.util.Map;

/**
 * Creates a Truck vehicle object. This class extends the AbstractVehicle class and
 * implements the methods canPass and chooseDirection. The Truck travels only on 
 * STREETS and through LIGHTS. It randomly selects to got straight, turn left, or turn
 * right. If none of those directions are valid then the truck will go in reverse. 
 * 
 * @author Jeremy Wolf email jwolf059@uw.edu
 * @version 22 October 2015
 */
public class Truck extends AbstractVehicle {
    
    /**
     * Field to store the DeathTime for the Car object.
     */
    public static final int DEATHTIME = 0;

    /**
     * Constructor for a Truck vehicle object. 
     * 
     * @param theX the starting X coordinate.
     * @param theY the starting Y coordinate.
     * @param theDir the starting direction.
     */
    public Truck(final int theX, final int theY, final Direction theDir) {
        super(theX, theY, theDir, DEATHTIME);
    } 

    /**
     * Returns if or if not this Truck may move onto the given type of
     * terrain, when the street lights are the given color.
     * 
     * @param theTerrain The terrain.
     * @param theLight The light color.
     * @return whether or not the Truck may move onto the given type of
     *         terrain when the street lights are the given color.
     */
    @Override
    public boolean canPass(final Terrain theTerrain, final Light theLight) {

        return theTerrain == Terrain.STREET || theTerrain == Terrain.LIGHT;
    }

    /**
     * Returns the direction this vehicle would like to move, based on the given
     * map of the neighboring terrain.  The Truck travels only on STREETS and through 
     * LIGHTS. It randomly selects to got straight, turn left, or turn right. If none
     * of those directions are valid then the truck will go in reverse. 
     * 
     * @param theNeighbors The map of neighboring terrain.
     * @return the direction this vehicle would like to move.
     */
    @Override
    public Direction chooseDirection(final Map<Direction, Terrain> theNeighbors) {
        
        final Light colorLight = Light.GREEN;
        final Direction leftSide = getDirection().left();
        final Direction rightSide = getDirection().right();
        final Direction straightAhead = getDirection();
        final boolean canTurnLeft = canPass(theNeighbors.get(leftSide), colorLight);
        final boolean canTurnRight = canPass(theNeighbors.get(rightSide), colorLight);
        final boolean canGoStraight = canPass(theNeighbors.get(straightAhead), colorLight);
        
        boolean validDirection = !onlyReverseDirection(canTurnLeft, canTurnRight, 
                                                      canGoStraight);
        Direction newDirection = getDirection().reverse();
        
        while (!validDirection) {
            final Direction possibleDirection = Direction.random();
            
            if (possibleDirection == leftSide && canTurnLeft) {
                newDirection = possibleDirection;
                validDirection = true;
            } else if (possibleDirection == rightSide && canTurnRight) {
                newDirection = possibleDirection;
                validDirection = true;
            } else if (possibleDirection == straightAhead && canGoStraight) {
                newDirection = possibleDirection;
                validDirection = true;
            }
        }
        return newDirection; 
    }
    
    /**
     * Private Helper method to determine if the truck has valid direction selection for 
     * right, left, or straight. If none are valid then this method will return false, 
     * meaning the truck must go in reverse. 
     * 
     * @param theTurnLeft boolean if the truck can go left.
     * @param theTurnRight boolean if the truck can go right.
     * @param theGoStraight boolean if the truck can go Straight.
     * @return false if the truck must go in reverse
     */
    private boolean onlyReverseDirection(final boolean theTurnLeft, 
                                         final boolean theTurnRight, 
                                         final boolean theGoStraight) {

        return theTurnLeft || theTurnRight || theGoStraight;
    }
    

}
