/*
 * Assignment 5
 * 
 * TCSS 305 Autumn 2015
 */
package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;

import model.PaintTool;

/**
 * Creates a JToolBar for the PowerPaintGUI. The Tool buttons included 
 * Pencil, Line, Rectangle, and Ellipse.
 * 
 * @author Jeremy Wolf
 * @version 9 November 2015
 *
 */

public class ToolBar implements PropertyChangeListener {

    /**
     * A generated serial version UID for object Serialization.
     */
    private static final long serialVersionUID = 4268951943328073987L;
    
    /**
     * Field to store JToolBar.
     */
    private final JToolBar myToolBar;
    

    /**
     * Field to store the PropertyChangeSupport Object for this class.
     */
    private final PropertyChangeSupport myPcs;
    
    /**
     * Field to store the Undo button.
     */
    private JButton myUndo;
    
    /**
     * Field to store the redo button.
     */
    private final JMenuBar myBar;
    /**
     * Constructor for a the JToolBar in PowerPaint.
     * 
     * @param theMap a Map with PaintTool Keys and Action values.
     * @param theTools a collection of Tools.
     * @param theFrame the Main JFrame used in the GUI.
     * @param thePanel the Panel to be drawn on.
     */
    public ToolBar(final Map<PaintTool, ToolAction> theMap, 
                   final List<PaintTool> theTools,
                   final JFrame theFrame,
                   final DrawingPanel thePanel) { 

        myToolBar = new JToolBar();
        createToolButtons(theMap, theTools, theFrame);
        createUndoAndRedo(thePanel);
        myBar = theFrame.getJMenuBar();
        myBar.addPropertyChangeListener(this);
        myPcs = new PropertyChangeSupport(this);
    }
      
    /**
     * Creates all Tool Buttons for the JToolBar.
     * 
     * @param theMap a Map with PaintTool Keys and Action values.
     * @param theTools a collection of Tools.
     * @param theFrame the Main JFrame used in the GUI.
     */
    private void createToolButtons(final Map<PaintTool, ToolAction> theMap, 
                                   final List<PaintTool> theTools,
                                   final JFrame theFrame)  {
      
        final ButtonGroup toolBarGroup = new ButtonGroup();
        for (final PaintTool aT : theTools) {
            final JToggleButton button = new JToggleButton(aT.getDescription());
            toolBarGroup.add(button);
            aT.addPropertyChangeListener(this);
            button.setAction(theMap.get(aT));
            myToolBar.add(button);
            myToolBar.addSeparator();
        }
        theFrame.add(myToolBar, BorderLayout.PAGE_END);
    }
    
    /**
     * Creates the Undo and Redo Buttons with Actions.
     * 
     * @param thePanel the DrawingPanel being used.
     */
    private void createUndoAndRedo(final DrawingPanel thePanel) {
        myUndo = new JButton();
        myToolBar.add(myUndo);
        
        String imageLocation = "images/undoicon.png";
        URL imgFileLocation = PowerPaintGUI.class.getResource(imageLocation);
        
        if (Objects.nonNull(imgFileLocation)) {
            myUndo.setIcon(new ImageIcon(imgFileLocation)); 
        } 
        final JButton redo = new JButton();
        myToolBar.add(redo);
        imageLocation = "images/redoicon.png";
        imgFileLocation = PowerPaintGUI.class.getResource(imageLocation);
        
        if (Objects.nonNull(imgFileLocation)) {
            redo.setIcon(new ImageIcon(imgFileLocation)); 
        } 
        myUndo.setEnabled(false);
        myUndo.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                    thePanel.getCollection().undo();
                    redo.setEnabled(true);
                    thePanel.repaint();
                    if (thePanel.getCollection().isEmpty()) {
                        myUndo.setEnabled(false); 
                        myPcs.firePropertyChange("undo", false, true);
                    }
            }
        });
        
        
        myToolBar.add(redo);
        redo.setEnabled(false);
        redo.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent theEvt) {
                
                thePanel.getCollection().redo();
                thePanel.repaint();
                if (thePanel.getCollection().isRedoEmpty()) {
                    redo.setEnabled(false);
                }
                myUndo.setEnabled(true);
            }
        });
    }
    
    /**
     * Receives the property change event and sets the drawing color
     * or the Thickness based on the type of property change.
     * 
     * @param theEvt is the property change event occurring.
     */
    @Override
    public void propertyChange(final PropertyChangeEvent theEvt) {
        if (theEvt.getPropertyName().equals(PaintTool.PROPERTY_END)) {
            myUndo.setEnabled(true);
        }
        if ("undone".equals(theEvt.getPropertyName())) {
            myUndo.setEnabled(false);
        }
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
}
