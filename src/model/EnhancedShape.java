/*
 * Assignment 5
 * 
 * TCSS305 - Autumn 2015
 */
package model;

import java.awt.Color;
import java.awt.Shape;

/**
 * Enhanced shape stores the Shape to be drawn as well
 * as the Shapes Color and border thickness.
 * 
 * @author Jeremy Wolf
 * @version 16 November 2015
 *
 */
public class EnhancedShape {

    /**
     * A generated serial version UID for object Serialization.
     */
    private static final long serialVersionUID = -1670664614900386310L;

    /**
     * Field to store the Shapes color.
     */
    private Color myColor;
    /**
     * Field to store the Shapes line thickness.
     */
    private int myThickness;
    /**
     * Field to store the shape. 
     */
    private final Shape myShape;
    

    /**
     * Constructor for the EnchancedShape class.
     * 
     * @param theShape is the Shape used.
     * @param theColor is the Color of the Shape.
     * @param theThickness is the thickness of the shapes line thickness.
     */
    public EnhancedShape(final Shape theShape, final Color theColor, 
                          final int theThickness) {
        myShape = theShape;
        myColor = theColor;
        myThickness = theThickness;
        
    }

    /**
     * Getter method for the Color of the Shape.
     * 
     * @return the Color of the Shape.
     */
    public Color getMyColor() {
        return myColor;
    }

    /**
     * Setter method for the Color of the Shape.
     * 
     * @param theColor new color for the Shape.
     */
    public void setMyColor(final Color theColor) {
        myColor = theColor;
    }

    /**
     * Getter method for the  of the Shape.
     * 
     * @return theThickness the line thickness for the Shape.
     */
    public int getMyThickness() {
        return myThickness;
    }
    /**
     * Setter method for the line thickness of the Shape.
     * 
     * @param theThickness new line thickness for the Shape.
     */
    public void setMyThickness(final int theThickness) {
        myThickness = theThickness;
    }

    /**
     * Getter method to get the Shape stored in this EnhancedShape.
     * 
     * @return the type of Shape store in the myShapeField.
     */
    public Shape getMyShape() {
        return myShape;
    }

    
    

}
