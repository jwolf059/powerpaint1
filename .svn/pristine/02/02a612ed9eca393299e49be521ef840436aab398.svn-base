/*
 * Assignment 5
 * 
 * TCSS305 - Autumn 2015
 */

package model;

import java.awt.Color;
import java.awt.Shape;
import java.beans.PropertyChangeListener;

/**
 * PaintTool Interface.
 * 
 * @author Jeremy Wolf
 * @version 9 November 2014
 *
 */
public interface PaintTool {
    
    /**
     * Static field containing a Property Change String for the Thickness.
     */
    String PROPERTY_THICKNESS = "Change in Thickness";
    
    /**
     * Static field containing a Property Change String for the Color.
     */
    String PROPERTY_COLOR = "Change in Color";
    
    /**
     * Static field containing a Property Change String for the Ending the Shape..
     */
    String PROPERTY_END = "Ending Position";
    

    
    /**
     * Adds the PropertyChangeListener to the list of PropertyChangeListeners
     * created by objects of this class. 
     * 
     * @param thePcl the PropertyChangeListener added.
     */
    void addPropertyChangeListener(final PropertyChangeListener thePcl);
    
    /**
     * Removes a PropertyChangeListener to from the list of PropertyChangeListeners
     * created by objects of this class. 
     * 
     * @param thePcl the PropertyChangeListener to be removed.
     */
    void removePropertyChangeListener(final PropertyChangeListener thePcl);

    
    /**
     * Starts drawing the Shape on the Drawing Panel.
     * 
     * @param theX the x value used to draw.
     * @param theY the y value used to draw.
     */
    void startDrawShape(double theX, double theY);
    
    /**
     * Completes the Shape.
     * 
     * @param theX the x value used to draw.
     * @param theY the y value used to draw.
     */
    void endDrawShape(final double theX, final double theY);
    
    /**
     * Shows progress as the user drags the mouse.
     * 
     * @param theX the x value used to draw.
     * @param theY the y value used to draw.
     */
    void hoverShape(final double theX, final double theY);
    
    /**
     * Getter method for the myDescription field. 
     * 
     * @return a string representation of the tool. 
     */
    String getDescription();
    
    /**
     * Getter method for the Shape field. 
     * 
     * @return the Shape store in myShape.
     */
    Shape getShape();
    
    /**
     * Getter method for the EnhancedShape field. 
     * 
     * @return the EnhancedShape stored in myEnhancedShape.
     */
    EnhancedShape getEnchancedShape();
    
    /**
     * Creates a new Shape.
     */
    void createNewShape();
    
    /**
     * Setter method for myColor.
     * 
     * @param theColor a color for the Shape.
     */
    void setColor(final Color theColor);
    
    /**
     * Setter method for myThickness.
     * 
     * @param theInt an Integer for the Shape thickness.
     */
    void setThickness(final int theInt);
    
    /**
     * Getter method for myColor.
     * 
     * @return the Color of the Shape.
     */
    Color getColor();
    
    /**
     * Getter method for myThickness.
     * 
     * @return the thickness of the Shape.
     */
    int getThickness();
    


}
