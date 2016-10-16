/*
 * Assignment - 3
 * 
 * TCSS 305 Autumn 2015
 */
package model;

import java.util.Map;

/**
 * Creates a Atv vehicle object. This class extends the AbstractVehicle class and
 * implements the methods canPass and chooseDirection. The Atv can travel on all
 * terrain expect walls and it randomly selects its direction, but will never go in reverse. 
 * Atv vehicles ignore light colors. 
 * 
 * @author Jeremy Wolf email jwolf059@uw.edu
 * @version 21 October 2015
 */

public class Atv extends AbstractVehicle {
    
    /**
     * Field to store the DeathTime for the Atv object.
     */
    public static final int DEATHTIME = 15;
    
    
    /**
     * Constructor for a Atv vehicle object. 
     * 
     * @param theX the starting X coordinate.
     * @param theY the starting Y coordinate.
     * @param theDir the starting direction.
     */
    public Atv(final int theX, final int theY, final Direction theDir) {
        
        super(theX, theY, theDir, DEATHTIME);

    }
    
    /**
     * Returns if or if not this Atv may move onto the given type of
     * terrain, when the street lights are the given color.
     * 
     * @param theTerrain The terrain.
     * @param theLight The light color.
     * @return whether or not the Atv may move onto the given type of
     *         terrain when the street lights are the given color.
     */
    @Override
    public boolean canPass(final Terrain theTerrain, final Light theLight) {
        
        return theTerrain != Terrain.WALL;
    }

    /**
     * Returns the direction this vehicle would like to move, based on the given
     * map of the neighboring terrain. The Atv can move on all terrain expect a wall.
     * The direction is selected randomly and can never be in the reverse direction. 
     * 
     * @param theNeighbors The map of neighboring terrain.
     * @return the direction this vehicle would like to move.
     */
    @Override
    public Direction chooseDirection(final Map<Direction, Terrain> theNeighbors) {
        final Light colorLight = Light.GREEN;
        boolean vaildDirection = false;
        Direction newDirection = null; 
        
        
        while (!vaildDirection) {
            newDirection = Direction.random();
            vaildDirection = newDirection != getDirection().reverse() 
                           || canPass(theNeighbors.get(newDirection), colorLight);
        }
        return newDirection;
    }

}
