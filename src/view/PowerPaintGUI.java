/*
 * Assignment 5
 * 
 * TCSS305 - Autumn 2015
 */
package view;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import model.Ellipse;
import model.Line;
import model.PaintTool;
import model.Pencil;
import model.Rectangle;

/**
 * Creates a PowerPaint GUI that contains a JPane that the user can draw shapes on.
 * Each Shape can be drawn by selecting the given tool.
 * 
 * @author Jeremy Wolf
 * @version 9 November 2015
 */
public class PowerPaintGUI extends JPanel {
    
    /**
     * A generated serial version UID for object Serialization.
     */
    private static final long serialVersionUID = -1162350521238926809L;
    
    /**
     * Field to store the Panel to be drawn on.
     */
    private DrawingPanel myDrawPanel;
    
    /**
     * Field to store the main JFrame.
     */
    private final JFrame myMainFrame;
    
    /**
     * A Map of Tools and the Actions Assigned to them.
     */
    private final Map<PaintTool, ToolAction> myToolActionMap;

    /**
     * Filed to store a Collection of PaintTools.
     */
    private List<PaintTool> myToolList; 

    /**
     * Constructor for the PowerPaintGUI.
     */
    public PowerPaintGUI()  {
        super(new BorderLayout());
        myToolActionMap = new HashMap<>();
        myMainFrame = new JFrame("PowerPaint");
        setUpComponents();
        
    }
    /**
     * Sets up the GUI Components.
     */
    private void setUpComponents() {

        try {
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (final UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        } catch (final IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (final InstantiationException ex) {
            ex.printStackTrace();
        } catch (final ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        /* Turn off metal's use of bold fonts */
        UIManager.put("swing.boldMetal", Boolean.FALSE);
        
        myMainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final ImageIcon imgicon = new ImageIcon(PowerPaintGUI.class.
                                                getResource("images/w.gif"));
        myMainFrame.setIconImage(imgicon.getImage());
        myDrawPanel = new DrawingPanel();
        add(myDrawPanel, BorderLayout.CENTER);
        createToolList();
        new MenuBar(myToolActionMap, myToolList, myMainFrame, myDrawPanel);
        new ToolBar(myToolActionMap, myToolList, myMainFrame, myDrawPanel);
        myMainFrame.setMinimumSize(myDrawPanel.getMinimumSize());
        myDrawPanel.setPaintTool(myToolList.get(0));
        myMainFrame.add(this, BorderLayout.CENTER);
        myMainFrame.pack();
        myMainFrame.setVisible(true);
    }
    
    /**
     * Creates all the Tool Classes and adds them to a List for Creation of
     * buttons.
     */
    private void createToolList() {
        myToolList = new ArrayList<>();
        myToolList.add(new Pencil());
        myToolList.add(new Line());
        myToolList.add(new Rectangle());
        myToolList.add(new Ellipse());
        makeActions();
        
    }
    
    /**
     * Creates a HashMap of Tools as Key and Actions as there values.
     */
    private void makeActions() {
        for (final PaintTool aT : myToolList) {
            myToolActionMap.put(aT, new ToolAction(aT, myDrawPanel));  
        }
    }


}
