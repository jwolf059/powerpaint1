/*
 * Assignment 5
 * 
 * TCSS305 - Autumn 2015
 */

package model;

import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.Line2D;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * Line Tool class that draws a line from one point to another by clicking the mouse 
 * on the drawing panel and dragging until you reach the desired end point. When the user is
 * dragging the progress can be seen using the hoverShape method.
 * 
 * @author Jeremy Wolf
 * @version 9 November 2015
 *
 */

public class Line extends AbstractTool {
    
    /**
     * Static field to Store starting location. 
     */
    private static final double START_OFF_SCREEN = -20;
    
    /**
     * Field to store the first X.
     */
    private double myX;
    
    /**
     * Field to store the first Y.
     */
    private double myY;
    
    /**
     * Field to Store the Shape.
     */
    private EnhancedShape myEnhancedShape;
    
    /**
     * Field to Store the Line.
     */
    private Line2D myLine;
    
    /**
     * Field to store the PropertyChangeSupport Object for this class.
     */
    private final PropertyChangeSupport myPcs;
    
    /**
     * Field to store the Thickness.
     */
    private int myThickness;
    
    /**
     * Field to store the Color.
     */
    private Color myColor;
    


    
    /**
     * Constructs a Line Tool.
     */
    public Line() {
        super();
        
        myThickness = 1;
        myColor = Color.BLACK;
        myLine = new Line2D.Double();
        myEnhancedShape = null;
        myPcs = new PropertyChangeSupport(this);
        myX = START_OFF_SCREEN;
        myY = START_OFF_SCREEN;

                        
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
     * Starts drawing the Line on the Drawing Panel.
     */
    @Override
    public void startDrawShape(final double theX, final double theY) {
        
        myX = theX;
        myY = theY;
        myLine.setLine(myX, myY, theX, theY);
    }

    /**
     * Ends the line.
     */
    @Override
    public void endDrawShape(final double theX, final double theY) {

        myLine.setLine(myX, myY, theX, theY);
        myEnhancedShape = new EnhancedShape(myLine, myColor, myThickness);
        myPcs.firePropertyChange(PROPERTY_END, false, true);
    }
    
    /**
     * shows progress as the user drags the mouse.
     */
    @Override
    public void hoverShape(final double theX, final double theY) {
        myLine.setLine(myX, myY, theX, theY);
        
    }
    
    /**
     * Creates a new Shape.
     */
    @Override
    public void createNewShape() {
        myLine = new Line2D.Double(START_OFF_SCREEN, START_OFF_SCREEN, 
                                   START_OFF_SCREEN, START_OFF_SCREEN);
        
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
     * Getter method for the myEllipse field.
     * 
     * @return the Shape stored in the field.
     */
    public Shape getShape() {
        
        return myLine;
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
    public int getThickness() {
        return myThickness;
    }
}
