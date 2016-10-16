/*
 * Assignment - 3
 * 
 * TCSS 305 Autumn 2015
 */
package model;

import java.util.Map;
/**
 * Creates a Bicycle vehicle object. This class extends the AbstractVehicle class and
 * implements the methods canPass and chooseDirection. The Bicycle can go on streets and 
 * through lights, but prefers to traverse trails when available. If the bike is unable 
 * go straight, left, or right it will go in reverse. The Bicycle stops on yellow and 
 * red lights.  
 * 
 * @author Jeremy Wolf email jwolf059@uw.edu
 * @version 21 October 2015
 */
public class Bicycle extends AbstractVehicle {
    /**
     * Field to store the DeathTime for the Bicycle object.
     */
    public static final int DEATHTIME = 25;
    
    /**
     * Constructor for a Bicycle vehicle object. 
     * 
     * @param theX the starting X coordinate.
     * @param theY the starting Y coordinate.
     * @param theDir the starting direction.
     */
    public Bicycle(final int theX, final int theY, final Direction theDir) {
        super(theX, theY, theDir, DEATHTIME);
        
    }

    /**
     * Returns if or if not this Bicycle may move onto the given type of
     * terrain, when the street lights are the given color.
     * 
     * @param theTerrain The terrain.
     * @param theLight The light color.
     * @return whether or not the Bicycle may move onto the given type of
     *         terrain when the street lights are the given color.
     */
    @Override
    public boolean canPass(final Terrain theTerrain, final Light theLight) {
        final boolean goodTerrain = theTerrain == Terrain.TRAIL 
                        || theTerrain == Terrain.STREET 
                        || theTerrain == Terrain.LIGHT && theLight == Light.GREEN;
        

         
        return goodTerrain; 
    }

    /**
     * Returns the direction this vehicle would like to move, based on the given
     * map of the neighboring terrain. The Bicycle can move on streets and 
     * through lights, but prefers to traverse trails when available. If the bike is unable 
     * go straight, left, or right it will go in reverse. The Bicycle stops on yellow and 
     * red lights.   
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
        
        if (theNeighbors.get(getDirection()) == Terrain.TRAIL) {
            newDirection = getDirection();
            
        } else if (theNeighbors.get(leftSide) == Terrain.TRAIL) {
            newDirection = leftSide;
            
        } else if (theNeighbors.get(rightSide) == Terrain.TRAIL) {
            newDirection = rightSide;
            
        } else
            if (canTurnLeft) {
                newDirection = leftSide;
            } else if (canTurnRight) {
                newDirection = rightSide;
            } else if (canGoStraight) {
                newDirection = getDirection();
            } else {
                newDirection = getDirection().reverse();
            }
        
        return newDirection;
    }

}
