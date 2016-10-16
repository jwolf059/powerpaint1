/*
 * Assignment 5
 * 
 * TCSS305 - Autumn 2015
 */
package model;


import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;

import javax.swing.Icon;

/**
 * Creates a ColorChooser Icon that is the color the 
 * user passed as an argument. 
 * 
 * @author Jeremy Wolf
 * @version 18 November 2015
 */
public class ColorChooserIcon implements Icon {

    /**
     * Static Field to store the Icon width.
     */
    private static final int ICON_WIDTH = 20;
    
    /**
     * Static Field to store the Icon height.
     */
    private static final int ICON_HEIGHT = 20;
    
    /**
     * Field to store the Icons color.
     */
    private Color myColor;
    
    /**
     * Constructs a new ColorChooserIcon that is the desired color.
     *
     * @param theColor the color the Icon needs to be.
     */
    public ColorChooserIcon(final Color theColor) {
        
        myColor = theColor;

    }
    
    /**
     * Setter method for the Color.
     * 
     * @param theColor the new color of the Icon.
     */
    public void setColor(final Color theColor) {
        myColor = theColor;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void paintIcon(final Component theC, final Graphics theG, 
                          final int theX, final int theY) {
        theG.setColor(Color.BLACK);
        theG.fillRect(theX - 1 , theY - 1 , ICON_WIDTH + 2, ICON_HEIGHT + 2);
        
        theG.setColor(myColor);
        theG.fillRect(theX, theY, ICON_WIDTH, ICON_HEIGHT);
        
        
    }
    
    /**
     * Getter method for the Icon width.
     * 
     * @return the Icon width.
     */
    @Override
    public int getIconWidth() {
        
        return ICON_WIDTH;
    }

    /**
     * Getter method for the Icon Height.
     * 
     * @return the Icon Height.
     */
    @Override
    public int getIconHeight() {
   
        return ICON_HEIGHT;
    }
    
}
