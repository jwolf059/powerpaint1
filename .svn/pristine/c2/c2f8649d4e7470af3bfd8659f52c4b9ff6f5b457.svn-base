/*
 * Assignment 5
 * 
 * TCSS305 Autumn 2015
 */

package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;

import model.EnhancedShape;
import model.PaintTool;
import model.Pencil;
import model.ShapeCollection;

/**
 * Creates a Drawing Panel for use in the PowerPaint GUI. 
 * 
 * @author Jeremy Wolf 
 * @version 4 November 2015
 */

public class DrawingPanel extends JPanel implements PropertyChangeListener {
    
    /**
     * Static field to store default Grid spacing.
     */
    private static final int DEFAULT_GRIDSPACING = 10;
    
    /**
     * Static field to store default Grid loop number.
     */
    private static final int DEFAULT_GRIDDRAWN = 9000;
    
    /**
     * Static field to store default Grid Line length.
     */
    private static final int DEFAULT_GRIDLENGTH = 3000;
    
    /**
     * A generated serial version UID for object Serialization.
     */
    private static final long serialVersionUID = 4485809200192843869L;
    
    /**
     * Constant field storing the Width.
     */
    private static final int WIDTH = 400;
    
    /**
     * Constant field storing the Height.
     */
    private static final int HEIGHT = 200;
    
    /**
     * Constant field storing the background color.
     */
    private static final Color PANELCOLOR = Color.WHITE;
    
    /**
     * Constant field storing the Minimum size.
     */
    private static final Dimension MIN_SIZE = new Dimension(WIDTH, HEIGHT);
    
    /**
     * Field to store a collection of shapes.
     */
    private final ShapeCollection myShapes;
    
    /**
     * Field to store the thickness of the Drawing.
     */
    private int myThickness;
    
    /**
     * Field to store the color of the Drawing.
     */
    private Color myColor;
    /**
     * Field to store the current tool being used.
     */
    private PaintTool myCurrentTool;
    
    /**
     * Field to store boolean to Draw grid.
     */
    private boolean myDrawGrid;

    
    /**
     * Constructs the Drawing Panel.
     */
    public DrawingPanel() {
        super();
        
        myShapes = new ShapeCollection();
        myThickness = 1;
        myColor = Color.BLACK;
        setSize(new Dimension(WIDTH, HEIGHT));
        setBackground(PANELCOLOR);
        setOpaque(true);
        myDrawGrid = false;
        myCurrentTool = new Pencil();
        createMouseAdapter();
        
    

    }
    /**
     * Receives the property change event and sets the drawing color
     * or the Thickness based on the type of property change.
     * 
     * @param theEvt is the property change event occurring.
     */
    @Override
    public void propertyChange(final PropertyChangeEvent theEvt) {
        if (theEvt.getPropertyName().equals(PaintTool.PROPERTY_COLOR)) {
            myColor = (Color) theEvt.getNewValue();
            repaint();

        } else if (theEvt.getPropertyName().equals(PaintTool.PROPERTY_THICKNESS)) {
            myThickness = (int) theEvt.getNewValue();
            repaint();
        } 
    } 
    
    /**
     * Method is used to allow the User to draw on the Drawing Panel.
     * 
     * @param theGraphics the Graphic to be drawn. 
     */
    @Override
    protected void paintComponent(final Graphics theGraphics) {
        super.paintComponent(theGraphics);
        myCurrentTool.addPropertyChangeListener(this);
        
        final Graphics2D g2D = (Graphics2D) theGraphics;

        
        g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                             RenderingHints.VALUE_ANTIALIAS_ON);

        if (!myShapes.isEmpty()) {
            for (final EnhancedShape eS: myShapes.getShapeList()) {
                g2D.setPaint(eS.getMyColor());
                g2D.setStroke(new BasicStroke(eS.getMyThickness()));
                g2D.draw(eS.getMyShape());
                
            }
        }
        if (myThickness > 0) {
            g2D.setPaint(myColor);
            g2D.setStroke(new BasicStroke(myThickness));
            g2D.draw(myCurrentTool.getShape());
        }
        if (myDrawGrid) {
            g2D.setPaint(Color.BLACK);
            g2D.setStroke(new BasicStroke(1));
            for (int i = 0; i < DEFAULT_GRIDDRAWN; i++) {
                if (i % DEFAULT_GRIDSPACING == 0) {
                    g2D.draw(new Line2D.Double(i, 0, i, DEFAULT_GRIDLENGTH));
                    g2D.draw(new Line2D.Double(0, i, DEFAULT_GRIDLENGTH, i));
                }
            }
        }
    }
    
    /**
     * Getter method for the Minimum Size of the drawing panel. 
     */
    @Override
    public Dimension getMinimumSize() {
        return MIN_SIZE;
    }
 
    /**
     * Getter method for the Preferred Size of the drawing panel. 
     */
    @Override
    public Dimension getPreferredSize() {
        return MIN_SIZE;
    }
    
    
    /**
     * Setter method for setting the current tool field to the current tool
     * selected in the PowerPaintGUI.
     * 
     * @param theTool the current tool selected in the GUI.
     */
    public void setPaintTool(final PaintTool theTool) {
        myCurrentTool = theTool;
 
    }
 
    /**
     * Getter method for the ShapeCollection.
     * @return 
     * 
     * @return the current ShapeCollection.
     */
    public ShapeCollection getCollection() {
        return myShapes;
    }
    
    /**
     * Setter method for the Boolean drawGrid.
     * 
     * @param theFact boolean value for drawing the grid.
     */
    public void setGrid(final boolean theFact) {
        myDrawGrid = theFact;
    }
    
    /**
     * Getter method for the Boolean drawGrid.
     */
    public boolean getGrid() {
        return myDrawGrid;
    }
    
    /**
     * Creates the MouseImputAdapter.
     */
    private void createMouseAdapter() {
        final MyMouseImputAdapter mouse = new MyMouseImputAdapter();
        addMouseListener(mouse);
        addMouseMotionListener(mouse);
    }

    /**
     * A MouseInputAdapter implementation specific to this GUI.
     * 
     * @author Jeremy Wolf  Email: Jwolf059@uw.edu
     * @version 5 November 2015
     */
    class MyMouseImputAdapter extends MouseInputAdapter {
        
        /**
         * Sets the Cursor to a Cross Hair cursor when the mouse 
         * enters the Drawing area.
         */
        @Override
        public void mouseEntered(final MouseEvent theEvent) {
            setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
            
        }
        
        /**
         * Starts drawing the shape.
         */
        @Override
        public void mousePressed(final MouseEvent theEvent) {
            
            myCurrentTool.startDrawShape(theEvent.getX(), theEvent.getY());
            repaint();
        }
        
        /**
         * Finishes drawing the shape.
         */
        @Override
        public void mouseReleased(final MouseEvent theEvent) {
            myCurrentTool.endDrawShape(theEvent.getX(), theEvent.getY());
            myShapes.addShape(myCurrentTool.getEnchancedShape());
            repaint();
            myCurrentTool.createNewShape();
        }
        
        /**
         * Moves the shape in reference to the starting point and allows the user to 
         * see the changes. 
         */
        @Override
        public void mouseDragged(final MouseEvent theEvent) {
            myCurrentTool.hoverShape(theEvent.getX(), theEvent.getY());
            repaint();
           
        }
    }
}
