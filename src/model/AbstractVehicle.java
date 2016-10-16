/*
 * Assignment - 3
 * 
 * TCSS 305 Autumn 2015
 */
package model;

import java.util.Map;

/**
 * Abstract vehicle class instantiates all Vehicle interface methods except
 * canPass and chooseDirection. These two methods will be instantiated in 
 * the child classes with class specific details.
 * 
 * @author Jeremy Wolf email jwolf059@uw.edu 
 * @version 21 October 2015
 */
public abstract class AbstractVehicle implements Vehicle {
    /**
     * Field to store the number of Pokes until revive.
     */
    private int myRevive;
    
    /**
     * Field to store the DeathTime for the class.
     */
    private final int myDeathTime;
    
    /**
     * Field to store true if the vehicle is alive.
     */
    private boolean myAlive;
    /**
     * Field to store the most current X value.
     */
    private int myX;
    
    /**
     * Field to store the most current Y value.
     */
    private int myY;
    
    /**
     * Field to store the most current Direction.
     */
    private Direction myDirection;
    
    /**
     * Field to store the starting X.
     */
    private final int myStartingX;
    
    /**
     * Field to store the starting Y.
     */
    private final int myStartingY;
    
    /**
     * Field to store the starting Direction.
     */
    private final Direction myStartingDir;
    
    
    /**
     * Abstract Vehicle Constructor instantiates all class fields. 
     * 
     * @param theX the starting X coordinate.
     * @param theY the starting Y coordinate.
     * @param theDir the starting direction.
     * @param theDeathTime integer used to evaluate collide and poke.
     */
    public AbstractVehicle(final int theX, final int theY, final Direction theDir, 
                           final int theDeathTime) {
        myX = theX;
        myY = theY;
        myDirection = theDir;
        myDeathTime = theDeathTime;
        myAlive = true;
        
        // stored starting information for use with reset method.
        myStartingX = theX;
        myStartingY = theY;
        myStartingDir = theDir;
 
    }
    
    /**
     * Returns if this object may move onto the given type of
     * terrain, when the street lights are the given color.
     * 
     * @param theTerrain The terrain.
     * @param theLight The light color.
     * @return whether or not this vehicle may move onto the given type of
     *         terrain when the street lights are the given color.
     */
    @Override
    public abstract boolean canPass(Terrain theTerrain, Light theLight);
 
    /**
     * Returns the direction this vehicle would like to move, based on the given
     * map of the neighboring terrain.
     * 
     * @param theNeighbors The map of neighboring terrain.
     * @return the direction this vehicle would like to move.
     */
    @Override
    public abstract Direction chooseDirection(Map<Direction, Terrain> theNeighbors);

    /**
     * Called when there is a collision between two Vehicle.
     * 
     * @param theOther The other Vehicle.
     */
    @Override
    public void collide(final Vehicle theOther) {
        if (isAlive() && theOther.isAlive() && getDeathTime() > theOther.getDeathTime()) {
            myAlive = false;
            myRevive = 0;
        }
    }
    
    /**
     * Returns the number of updates between this vehicle's death and when it
     * should be revived.
     * 
     * @return the number of updates required.
     */
    @Override
    public int getDeathTime() {
        
        return myDeathTime;
    }
    
    /**
     * Returns the file name of the image for this Vehicle object, such as
     * "truck.gif". Will base the file name off whether or not the vehicle
     * is alive.
     * 
     * @return the file name. 
     */
    @Override
    public String getImageFileName() {
        String name = getClass().getSimpleName().toLowerCase();
        if (isAlive()) {
            name = name + ".gif";
                          
        } else {
            name = name + "_dead.gif";
        }
        
        return name;
    }
    
    /**
     * Returns this Vehicle object's direction.
     * 
     * @return the direction.
     */
    @Override
    public Direction getDirection() {
      
        return myDirection;
    }
    
    /**
     * Returns this Vehicle object's x-coordinate.
     * 
     * @return the x-coordinate.
     */
    @Override
    public int getX() {

        return myX;
    }
   /**
    * Returns this Vehicle object's y-coordinate.
    * 
    * @return the y-coordinate.
    */
    @Override
    public int getY() {

        return myY;
    }

    /**
     * Returns whether this Vehicle object is alive and should move on the map.
     * 
     * @return true if the object is alive, false otherwise.
     */
    @Override
    public boolean isAlive() {
       
        return myAlive;
    }

    /**
     * Called by the GUI to notify a dead vehicle that 1 movement round has
     * passed, so that it will become 1 move closer to revival.
     */
    @Override
    public void poke() {
        myRevive += 1;
        if (myRevive == myDeathTime) {
            myDirection = Direction.random();
            myRevive = 0;
            myAlive = true;
        }

    }

    /**
     * Moves this vehicle back to its original position.
     */
    @Override
    public void reset() {
        myRevive = 0;
        setX(myStartingX);
        setY(myStartingY);
        setDirection(myStartingDir);
        myAlive = true;

    }
    /**
     * Sets this object's direction to the given value.
     * 
     * @param theDir The new direction.
     */
    @Override
    public void setDirection(final Direction theDir) {
        myDirection = theDir;
    }
    
    /**
     * Sets this object's x-coordinate to the given value.
     * 
     * @param theX The new x-coordinate.
     */
    @Override
    public void setX(final int theX) {
        myX = theX;

    }

    /**
     * Sets this object's y-coordinate to the given value.
     * 
     * @param theY The new y-coordinate.
     */
    @Override
    public void setY(final int theY) {
        myY = theY;

    }
    /**
     * Produces a String containing the Type of vehicle and if the vehicle object is
     * dead then it will included the type of vehicle, Poke and then number of pokes 
     * done. 
     * 
     * @return a string representation of the Vehicle object.
     */
    @Override
    public String toString() {
        final StringBuilder vehicleInfoSB = new StringBuilder();
        vehicleInfoSB.append(getClass().getSimpleName().toLowerCase());
        if (!isAlive()) {
            vehicleInfoSB.append(" Poke: ");
            vehicleInfoSB.append(myRevive);
        }
        return vehicleInfoSB.toString();
    }

}
