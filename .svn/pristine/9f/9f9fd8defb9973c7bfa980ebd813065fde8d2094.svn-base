/*
 * Assignment 5
 * 
 * TCSS305 - Autumn 2015
 */

package view;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.Objects;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.KeyStroke;

import model.PaintTool;

/**
 * Creates the Actions for each of the Tools in the PowerPaint program and
 * sets up the link between JToggleButtons on the ToolBar and JRadioButtons
 * in the Tool Menu.
 * 
 * @author Jeremy Wolf
 * @version 9 November 2015
 *
 */
class ToolAction extends AbstractAction {

    /**
     * A generated serial version UID for object Serialization.
     */
    private static final long serialVersionUID = 6216916389200515214L;
    
    /**
     * Field to store the current Tool.
     */
    private final PaintTool myTool;
    
    /**
     * Field to store the Drawing Panel.
     */
    private final DrawingPanel myDrawingPanel;


    /**
     * Constructors for the ToolAction Class.
     * 
     * @param theTool a Tool from the PaintTool Class.
     * @param theDrawingPanel the panel to be used for drawing.
     */
    ToolAction(final PaintTool theTool, final DrawingPanel theDrawingPanel) {
        super(theTool.getDescription());
        myTool = theTool;
        myDrawingPanel = theDrawingPanel;
        setMnemonic(theTool);
        setImage(theTool);
        myTool.addPropertyChangeListener(myDrawingPanel);
    }
        /**
         * Set the Mnemonic value for each Tool.
         * 
         * @param theTool that the Mnemonic is being assigned.
         */
    private void setMnemonic(final PaintTool theTool) {
        putValue(SHORT_DESCRIPTION, theTool.getDescription());
        final int keyCode = KeyStroke.getKeyStroke(theTool.getDescription().
                                                   charAt(0), 0).getKeyCode();
        putValue(MNEMONIC_KEY, keyCode);
        putValue(SELECTED_KEY, false);
    }
        
        /**
         * Set the Image for each Tool.
         * 
         * @param theTool the Tool that the Image is being assigned.
         */
    private void setImage(final PaintTool theTool) {
        final String imageLocation = "images/"
                        + theTool.getDescription()
                        + ".gif";
        final URL imgFileLocation = PowerPaintGUI.class.getResource(imageLocation);
        
        if (Objects.nonNull(imgFileLocation)) {
            putValue(SMALL_ICON, new ImageIcon(imgFileLocation, theTool.getDescription()));
        } else { 
            System.err.println("No Image Found" + imgFileLocation);
        }
    }
    
    /**
     * Action to be performed by the assigned Button.
     * 
     * @param theEvent is the event of the button be clicked.
     */
    @Override
    public void actionPerformed(final ActionEvent theEvent) {
        putValue(SELECTED_KEY, true); 
        myDrawingPanel.setPaintTool(myTool);
        myTool.createNewShape();
        

    }
    
    
}