/*
 * Assignment - 3
 * 
 * TCSS 305 Autumn 2015
 */
package model;

import java.util.Map;

/**
 * Creates a Human vehicle object. This class extends the AbstractVehicle class and
 * implements the methods canPass and chooseDirection. The Human moves in random 
 * directions but must always stay on terrain matching its starting terrain. If the
 * starting terrain is LIGHT or STREET it can traverse each of these. Humans ignore 
 * traffic lights. 
 * 
 * @author Jeremy Wolf email jwolf059@uw.edu
 * @version 21 October 2015
 */
public class Human extends AbstractVehicle {
    
    /**
     * Field to store the DeathTime for the Car object.
     */
    private static final int DEATHTIME = 45;
    
    /**
     * Field to store the starting terrain.
     */
    private Terrain myStartingTerrain;
    
    /**
     * Field to store if it can or cannot pass through a STREET or LIGHT.
     */
    private boolean myLightStreetPass;
    
    /**
     * Constructor for a Human vehicle object. 
     * 
     * @param theX the starting X coordinate.
     * @param theY the starting Y coordinate.
     * @param theDir the starting direction.
     * @param theTerrain the starting Terrain.
     */
    public Human(final int theX, final int theY, final Direction theDir, 
                 final Terrain theTerrain) {
        
        super(theX, theY, theDir, DEATHTIME);
        myStartingTerrain = theTerrain;
        if (theTerrain == Terrain.STREET || theTerrain == Terrain.LIGHT) {
            myLightStreetPass = true;
        } else {
            myLightStreetPass = false; 
        }

    }
    
    /**
     * Returns if or if not this Human may move onto the given type of
     * terrain, when the street lights are the given color.
     * 
     * @param theTerrain The terrain.
     * @param theLight The light color.
     * @return whether or not the Human may move onto the given type of
     *         terrain when the street lights are the given color.
     */
    @Override
    public boolean canPass(final Terrain theTerrain, final Light theLight) {
        boolean okPass = false;
        if (myLightStreetPass && theTerrain == Terrain.STREET) {
            okPass = true;
        } else if (myLightStreetPass && theTerrain == Terrain.LIGHT) {
            okPass = true;
        } else {
            okPass = theTerrain == myStartingTerrain;  
        }
        return okPass;   
    }
    /**
     * Returns the direction this vehicle would like to move, based on the given
     * map of the neighboring terrain.  The Human moves in random directions but must
     * always stay on terrain matching its starting terrain. If the starting terrain 
     * is LIGHT or STREET it can traverse each of these. Humans ignore traffic lights. 
     * 
     * @param theNeighbors The map of neighboring terrain.
     * @return the direction this vehicle would like to move.
     */
    @Override
    public Direction chooseDirection(final Map<Direction, Terrain> theNeighbors) {
        final Light colorLight = Light.GREEN;
        Boolean vaildDireciton = false;
        Direction newDirection = null; 
        while (!vaildDireciton) {
            final Direction possibleDirection = Direction.random();
            
            if (canPass(theNeighbors.get(possibleDirection), colorLight)) {
                newDirection = possibleDirection;
                vaildDireciton = true;      
            }
        }
        return newDirection;
    }
}
