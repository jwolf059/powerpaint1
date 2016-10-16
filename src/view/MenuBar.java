/*
 * Assignment 5
 * 
 * TCSS 305 Autumn 2015
 */
package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;
import java.util.Map;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.KeyStroke;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import model.ColorChooserIcon;
import model.PaintTool;

/**
 * Creates a JMenuBar and its all its MenuItems for the PowerPaintGUI. 
 * 
 * @author Jeremy Wolf
 * @version 9 November 2015
 *
 */

public class MenuBar extends JMenuBar implements PropertyChangeListener {

    
    /**
     * Static field for Property Change string Undo.
     */
    public static final String PROPERTY_UNDO = "undone";
    
    /**
     * Static field for Property Change string Grid.
     */
    public static final String PROPERTY_GRID = "GRID";
    
    /**
     * A generated serial version UID for object Serialization.
     */
    private static final long serialVersionUID = -4182211256097882913L;

    /**
     * Field to store the DrawingPane.
     */
    private final DrawingPanel myDrawingPanel;
    
    /**
     * Field to store a Collection of Buttons.
     */
    private final List<PaintTool> myToolList;
    
    /**
     * Field for the Undo ALl button.
     */
    private JMenuItem myUndoAll;
    
    /**
     * Field to store the PropertyChangeSupport Object for this class.
     */
    private final PropertyChangeSupport myPcs;

    /**
     * Constructor for a the JMenuBar in PowerPaint.
     * 
     * @param theMap a Map with PaintTool Keys and Action values.
     * @param theTools a collection of Tools.
     * @param theFrame the Main JFrame used in the GUI.
     * @param theDrawingPane the Panel to be drawn on.
     */
    public MenuBar(final Map<PaintTool, ToolAction> theMap, 
                   final List<PaintTool> theTools,
                   final JFrame theFrame,
                   final DrawingPanel theDrawingPane) {
        super();
        myDrawingPanel = theDrawingPane;
        myToolList = theTools;
        makeMenuBar(theMap, theTools, theFrame, theDrawingPane);
        myPcs = new PropertyChangeSupport(this);
        
    }
    /**
     * Creates the JMenuBar and all the Menu and MenuItems.
     * 
     * @param theMap a Map with PaintTool Keys and Action values.
     * @param theTools a collection of Tools.
     * @param theFrame the Main JFrame used in the GUI.
     * @param theDrawingPane the Panel to be drawn on.
     */
    private void makeMenuBar(final Map<PaintTool, ToolAction> theMap, 
                             final List<PaintTool> theTools,
                             final JFrame theFrame,
                             final DrawingPanel theDrawingPane) {
        
        theFrame.setJMenuBar(this);
        createFileMenu(this, theFrame);
        createOptionsMenu(this, theDrawingPane);
        createToolMenu(theMap, theTools, this);
        createHelpMenu(this);
       
    
    }
    /**
     * Creates the File Menu with assigned Mnemonic.
     * 
     * @param theMenuBar the JMenuBar that the JMenu is being added to.
     * @param theFrame the Main JFrame used in the GUI.
     */
    private void createFileMenu(final JMenuBar theMenuBar, final JFrame theFrame) {
        final JMenu file = new JMenu("File");
        file.setMnemonic(KeyEvent.VK_F);
        theMenuBar.add(file);
        
        myUndoAll = new JMenuItem("Undo All Changes");
        myUndoAll.setMnemonic(KeyEvent.VK_U);
        myUndoAll.setEnabled(false);
        myUndoAll.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent theEvt) {
                myDrawingPanel.getCollection().clearCollection();
                myDrawingPanel.repaint();
                myUndoAll.setEnabled(false);
                
                myPcs.firePropertyChange(PROPERTY_UNDO, false, true); 
            }
        });
        file.add(myUndoAll);
        
        file.addSeparator();
        
        final JMenuItem exit = new JMenuItem("Exit");
        exit.setMnemonic(KeyEvent.VK_X);
        exit.setAccelerator(KeyStroke.getKeyStroke(
                            KeyEvent.VK_X, ActionEvent.META_MASK));
        
        exit.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                theFrame.dispatchEvent(new WindowEvent(theFrame, 
                                                          WindowEvent.WINDOW_CLOSING));
            }
        });
        file.add(exit);
        theMenuBar.add(file);
        
       
    }
    /**
     * Creates the Options Menu with assigned Mnemonic.
     * 
     * @param theMenuBar the JMenuBar that the JMenu is being added to.
     * @param theDrawingPane the Panel to be drawn on.
     */
    private void createOptionsMenu(final JMenuBar theMenuBar, 
                                   final DrawingPanel theDrawingPane) {
        final JMenu options = new JMenu("Options");
        options.setMnemonic(KeyEvent.VK_O);
        theMenuBar.add(options);
        options.add(createGridMenuButton());
        options.addSeparator();
        options.add(createThickSubMenu());
        options.addSeparator();
        options.add(createColorMenu(theDrawingPane));
        theMenuBar.add(options);
        
    }
    /**
     * Creates a JCheckBox for the grid function.
     * @return a JCheckBox.
     */
    private JCheckBox createGridMenuButton() {
        final JCheckBox gridCheck = new JCheckBox("Grid");
        gridCheck.setMnemonic(KeyEvent.VK_G);
        gridCheck.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                myDrawingPanel.setGrid(!myDrawingPanel.getGrid());
            }
        });
        return gridCheck;
    }
    
    /**
     * Creates a JSlider for the Thickness function.
     * 
     * @return a JSlider with major TickMark at 5 and Minor at 1.
     */
    private JMenu createThickSubMenu() {
        final int major = 5;
        final int minor = 1;
        final JMenu thicknessMenu = new JMenu("Thickness");
        thicknessMenu.setMnemonic(KeyEvent.VK_T);
        final JSlider thickness = new JSlider(0, 20, 1);
        thicknessMenu.add(thickness);
        thickness.setMajorTickSpacing(major);
        thickness.setMinorTickSpacing(minor);
        thickness.setPaintLabels(true);
        thickness.setPaintTicks(true);
        thickness.addChangeListener(new ChangeListener() {
            
            @Override
            public void stateChanged(final ChangeEvent theEvent) {
                final int thickVal = thickness.getValue();
                for (final PaintTool pT : myToolList) {
                    pT.setThickness(thickVal);
                }
                
            }
        });

        return thicknessMenu;
    }
    
    /**
     * Creates a JMenuItem that will open a JColorChooser.
     * @param theDrawingPane the Drawing Panel to be used.
     * @return a JMenuItem.
     */
    private JMenuItem createColorMenu(final DrawingPanel theDrawingPane) {
        final JMenuItem colorChooser = new JMenuItem("Color...");
        colorChooser.setMnemonic(KeyEvent.VK_C);
        final ColorChooserIcon icon = new ColorChooserIcon(Color.BLACK);    
        colorChooser.setIcon(icon);
        colorChooser.addActionListener(new ActionListener() {

           
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                final Color result = JColorChooser.showDialog(null, "Choose A Color", null);
                if (result != null) {
                    icon.setColor(result);
                    colorChooser.setForeground(Color.BLACK);
                    for (final PaintTool pT : myToolList) {
                        pT.setColor(result);

                    }
                }
            }
        });
        return colorChooser;
        
    }
    
    /**
     * Creates the Tool Menu with assigned Mnemonic.
     * 
     * @param theMap a Map with PaintTool Keys and Action values.
     * @param theTools a collection of Tools.
     * @param theMenuBar the JMenuBar that the JMenu is being added to.
     */
    private void createToolMenu(final Map<PaintTool, ToolAction> theMap, 
                                final List<PaintTool> theTools,
                                final JMenuBar theMenuBar) {
        
        final JMenu tools = new JMenu("Tools");
        tools.setMnemonic(KeyEvent.VK_T);
        final ButtonGroup toolGroup = new ButtonGroup();
        for (final PaintTool aT : theTools) {
            aT.addPropertyChangeListener(this);
            final JRadioButton button = new JRadioButton(aT.getDescription());
            if ("Pencil".equals(button.getText())) {
                
                button.setSelected(true);
            }
            button.setAction(theMap.get(aT));
            tools.add(button);
            toolGroup.add(button);
        }
        theMenuBar.add(tools);
        
    }
    /**
     * Creates the Help Menu with assigned Mnemonic.
     * 
     * @param theMenuBar the JMenuBar that the JMenu is being added to.
     */
    private void createHelpMenu(final JMenuBar theMenuBar) {
        final JMenu help = new JMenu("Help");
        theMenuBar.add(help);
        help.add(createAboutItem());
        help.setMnemonic(KeyEvent.VK_H);
        
        theMenuBar.add(help);
    }
   /**
    * Creates the About JMenuItem that when selected opens a JOptionPane.
    * @return a JMenuItem.
    */
    private JMenuItem createAboutItem() {
        final JMenuItem about = new JMenuItem("About...");
        about.setMnemonic(KeyEvent.VK_A);
        about.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                JOptionPane.showMessageDialog(null, "TCSS 305 PowerPaint, "
                                + "Autum 2015", "About", JOptionPane.DEFAULT_OPTION);
            }
        });
        return about;
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
            myUndoAll.setEnabled(true);
    
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