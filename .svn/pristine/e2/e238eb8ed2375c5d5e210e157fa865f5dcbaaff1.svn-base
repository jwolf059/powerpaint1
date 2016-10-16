/*
 * Assignment 5
 * 
 * TCSS305 - Autumn 2015
 */

package model;

import java.awt.List;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Stack;

/**
 * Collection Class for all Shapes.
 * 
 * @author Jeremy Wolf
 * @version 11 November 2015
 *
 */
public class ShapeCollection extends List {
    
    /**
     * A generated serial version UID for object Serialization.
     */
    private static final long serialVersionUID = -4084938395427783248L;

    /**
     * Field to store a Stack of undo shapes.
     */
    private final Stack<EnhancedShape> myShapes;
    
    /**
     * Field to store a Stack of redo shapes.
     */
    private final Stack<EnhancedShape> myRedo;
    
    /**
     * filed to store the PropertyChange Support object.
     */
    private final PropertyChangeSupport myPcs;
    
    /**
     * Constructor for the ShapeCollection Class.
     */
    public ShapeCollection() {
        super();
        myPcs = new PropertyChangeSupport(this);
        myShapes = new Stack<>();
        myRedo = new Stack<>();
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
     * Adds the Shape to the ArrayList.
     * 
     * @param theShape the shape to be added.
     */
    public void addShape(final EnhancedShape theShape) {
        myShapes.push(theShape);
        myPcs.firePropertyChange("FIRE", false, true);
    }
    
    /**
     * Getter method for the EnchancedShape ArrayList.
     * @return an Stack of EnchancedShape
     */
    public Stack<EnhancedShape> getShapeList() {
        
        //do a deep copy later.
        return myShapes;
    }
    
    /**
     * Check to see if the myShapes Stack is empty.
     * 
     * @return a boolean true if the stack is empty.
     */
    public boolean isEmpty() {
        return myShapes.isEmpty();
    }
    
    /**
     * Check to see if the myRedo Stack is empty.
     * 
     * @return a boolean true if the stack is empty.
     */
    public boolean isRedoEmpty() {
        return myRedo.isEmpty();
    }
    /**
     * Clears the Shape Collection.
     */
    public void clearCollection() {
        myShapes.clear();
    }
    /**
     * Will Undo the drawing of a single shape each time.
     */
    public void undo() {
        final EnhancedShape eS = myShapes.pop();
        myRedo.push(eS);
    }
    
    /**
     * Will redraw a single shape each time.
     */
    public void redo() {
        if (!myRedo.isEmpty()) {
            final EnhancedShape eS = myRedo.pop();
            myShapes.push(eS);
        }
    }

}
