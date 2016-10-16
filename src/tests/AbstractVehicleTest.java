/*
 * Assignment - 3
 * 
 * TCSS 305 Autumn 2015
 */
package tests;

import static org.junit.Assert.*;

import model.Atv;
import model.Car;
import model.Direction;
import model.Truck;

import org.junit.Before;
import org.junit.Test;



/**
 * Test for the AbstractVehicle Class. 
 * 
 * @author Jeremy Wolf email jwolf059@uw.edu
 * @version 22 October 2015
 */
public  class AbstractVehicleTest {
    
    /** The test fixture. The Truck object test we will use in the test. */
    private Truck myTestVehicleOne;
    
    /** The test fixture. The Car object test we will use in the test. */
    private Car myTestVehicleTwo;
    
    /** The test fixture. The Truck object test we will use in the test. */
    private Truck myTestVehicleThree;
    
    /** The test fixture. The Atv object test we will use in the test. */
    private Atv myTestVehicleFour;

    
    /** 
     * A method to initialize the test fixtures before each test.
     */
    @Before
    public void setUp() {
        myTestVehicleOne = new Truck(1, 5, Direction.WEST);
        myTestVehicleTwo = new Car(3, 5, Direction.EAST);
        myTestVehicleThree = new Truck(1, 5, Direction.WEST);
        myTestVehicleFour = new Atv(1, 4, Direction.WEST);
    }
    
    /**
     * Test method for {@link AbstractVehicle#Collide()}.
     */
    @Test
    public void testCollide() {
        myTestVehicleOne.collide(myTestVehicleTwo);
        assertTrue("Truck should have survived collision with car", 
                   myTestVehicleOne.isAlive());
        
        myTestVehicleTwo.collide(myTestVehicleOne);
        assertFalse("Car should have been killed by Truck", myTestVehicleTwo.isAlive());
        
        myTestVehicleTwo.collide(myTestVehicleFour);
        assertTrue("Atv should survive collisoin with dead car", myTestVehicleFour.isAlive());
        
        myTestVehicleOne.collide(myTestVehicleThree);
        assertTrue("Truck should have survived collision with Truck", 
                   myTestVehicleOne.isAlive());

    }
    /**
     * Test method for {@link AbstractVehicle#getDeathTime()}.
     */
    @Test
    public void testGetDeathTime() {
        assertEquals(0, myTestVehicleOne.getDeathTime());
    }

    /**
     * Test method for {@link AbstractVehicle#getImageFileName()}.
     */
    @Test
    public void testGetImageFileName() {
        myTestVehicleTwo.collide(myTestVehicleOne);
        final String alive = "truck.gif";
        assertEquals(alive, myTestVehicleOne.getImageFileName());
        final String dead = "car_dead.gif";
        assertEquals(dead, myTestVehicleTwo.getImageFileName());
    }

    /**
     * Test method for {@link AbstractVehicle#getDirection()}.
     */
    @Test
    public void testGetDirection() {
        assertEquals(Direction.WEST, myTestVehicleOne.getDirection());
    }

    /**
     * Test method for {@link AbstractVehicle#isAlive()}.
     */
    @Test
    public void testIsAlive() {
        assertTrue("Truck should be alive", myTestVehicleOne.isAlive());
        
        myTestVehicleTwo.collide(myTestVehicleOne);
        assertFalse("Car should be dead", myTestVehicleTwo.isAlive());
        
    }
    /**
     * Test method for {@link AbstractVehicle#poke()}.
     */
    @Test
    public void testPoke() {
        myTestVehicleTwo.collide(myTestVehicleOne);
        assertFalse("Car should be dead", myTestVehicleTwo.isAlive());
        for (int x = 1; x < 6; x += 1) {
            myTestVehicleTwo.poke();
        }
        assertTrue("Car should be alive after 5 pokes", myTestVehicleTwo.isAlive());
    }
    /**
     * Test method for {@link AbstractVehicle#reset()}.
     */
    @Test
    public void testReset() {

        assertEquals("Truck getX should be 1", 1, myTestVehicleOne.getX());
        assertEquals("Truck getY should be 5", 5, myTestVehicleOne.getY());
        assertEquals("Truck Direction should be WEST", Direction.WEST,
                     myTestVehicleOne.getDirection());
        
        myTestVehicleOne.setX(2);
        assertEquals("Truck getX should be 2", 2, myTestVehicleOne.getX());

        myTestVehicleOne.setY(6);
        assertEquals("Truck getY should be 6", 6, myTestVehicleOne.getY());

        myTestVehicleOne.setDirection(Direction.NORTH);
        assertEquals("Truck Direction should be NORTH", Direction.NORTH, 
                     myTestVehicleOne.getDirection());
         
        myTestVehicleOne.reset();
        assertEquals("Truck getX should be 1 after reset", 1, myTestVehicleOne.getX());
        assertEquals("Truck getY should be 5after reset", 5, myTestVehicleOne.getY());
        assertEquals("Truck Direction should be WEST after reset", Direction.WEST, 
                     myTestVehicleOne.getDirection());
    }
    /**
     * Test method for {@link AbstractVehicle#toString()}.
     */
    @Test
    public void testToString() {
        String testString = myTestVehicleTwo.toString();
        assertEquals("car", testString);
        
        myTestVehicleTwo.collide(myTestVehicleOne);
        testString = myTestVehicleTwo.toString();
        assertEquals("car Poke: 0", testString);
    }

}
