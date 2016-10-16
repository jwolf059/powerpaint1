/*
 * Assignment 5
 * 
 * TCSS305 - Autumn 2015
 */

package model;

import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.GeneralPath;
import java.awt.geom.Path2D;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * Pencil Tool class that draws a GeneralPath while the mouse is being dragged. 
 * 
 * @author Jeremy Wolf
 * @version 9 November 2015
 *
 */
public class Pencil extends AbstractTool {
    
    
    
    /**
     * Field to store the Shape.
     */
    private Path2D myPencil;
    
    /**
     * Field to store the the thickness.
     */
    private int myThickness;
    
    /**
     * Field to store the Color.
     */
    private Color myColor;
    
    /**
     * Field to store the PropertyChangeSupport Object for this class.
     */
    private final PropertyChangeSupport myPcs;
    
    /**
     * Field to Store the Shape.
     */
    private EnhancedShape myEnhancedShape;
    
    /**
     * Constructor for the Pencil Tool.
     */
    public Pencil() {
        super();   
        myThickness = 1;
        myColor = Color.BLACK;
        myPencil = new GeneralPath();
        myPcs = new PropertyChangeSupport(this);
        myEnhancedShape = null;
    }
    
    /**
     * Adds the PropertyChangeListener to the list of PropertyChangeListeners
     * created by objects of this class. 
     * 
     * @param thePcl the PropertyChangeListener added.
     */
    public void addPropertyChangeListener(final PropertyChangeListener thePcl) {
        myPcs.addPropertyChangeListener(thePcl);
    }
    
    /**
     * Removes a PropertyChangeListener to from the list of PropertyChangeListeners
     * created by objects of this class. 
     * 
     * @param thePcl the PropertyChangeListener to be removed.
     */
    
    public void removePropertyChangeListener(final PropertyChangeListener thePcl) {
        myPcs.removePropertyChangeListener(thePcl);
    }
    
    /**
     * Starts drawing the Shape on the Drawing Panel.
     * 
     * @param theX the x value used to draw.
     * @param theY the y value used to draw.
     */
    @Override
    public void startDrawShape(final double theX, final double theY) {
        myPencil.moveTo(theX, theY);
        
    }

    /**
     * Completes the Shape.
     * 
     * @param theX the x value used to draw.
     * @param theY the y value used to draw.
     */
    @Override
    public void endDrawShape(final double theX, final double theY) {

        myPencil.lineTo(theX, theY);
        myEnhancedShape = new EnhancedShape(myPencil, myColor, myThickness);
        myPcs.firePropertyChange(PROPERTY_END, 0, 1);
        
    }

    /**
     * Shows progress as the user drags the mouse.
     * 
     * @param theX the x value used to draw.
     * @param theY the y value used to draw.
     */
    @Override
    public void hoverShape(final double theX, final double theY) {
        myPencil.lineTo(theX, theY);
        
    }
    /**
     * Creates a new General Path.
     */
    @Override
    public void createNewShape() {
        myPencil = new GeneralPath();
//        myEnhancedShape = new EnhancedShape(myPencil, theColor, theThickness);
        
    }

    /**
     * Getter method for the myEnhancedShape field.
     * 
     * @return the EnchancedShape stored in the field.
     */
    @Override
    public EnhancedShape getEnchancedShape() {
        
        return myEnhancedShape;
    }

    /**
     * Getter method for the Shape field.
     * 
     * @return the Shape stored in the field.
     */
    public Shape getShape() {
        
        return myPencil;
    }
    
    /**
     * Setter method for myThickness.
     * 
     * @param theInt an Integer for the Shape thickness.
     */
    @Override
    public void setThickness(final int theThickness) {
        final int old = myThickness;
        myThickness = theThickness;
        myPcs.firePropertyChange(PROPERTY_THICKNESS, old, theThickness);
    }
    
    /**
     * Setter method for myColor.
     * 
     * @param theColor a color for the Shape.
     */
    @Override
    public void setColor(final Color theColor) {
        final Color old = myColor;
        myColor = theColor;
        
        myPcs.firePropertyChange(PROPERTY_COLOR, old, theColor);
    }
    
    /**
     * Getter method for myColor.
     * 
     * @return the Color of the Shape.
     */
    @Override
    public Color getColor() {
        return myColor;
    }

    /**
     * Getter method for myThickness.
     * 
     * @return the thickness of the Shape.
     */
    @Override
    public int getThickness() {
        return myThickness;
    }
}