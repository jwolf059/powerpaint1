package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.Car;
import model.Direction;
import model.Light;
import model.Terrain;

public class CarTest {
    private  Car myTestCar;
    
    @Test
    public void testCanPass() {
        myTestCar = new Car(1, 2, Direction.EAST);
        for (Terrain testTerrain: Terrain.values()) {
            for (Light testLight : Light.values()) {
                if ((testTerrain == Terrain.LIGHT && testLight != Light.RED) || testTerrain == Terrain.STREET) {
                    assertTrue("Cars should pass " + testTerrain + " with a " + testLight 
                               + " light", myTestCar.canPass(testTerrain, testLight));
                } else {
                    assertFalse("Cars should not pass " + testTerrain + " with a " 
                                    + testLight + " light", 
                                    myTestCar.canPass(testTerrain, testLight));
                }
            }
        }
    }

    @Test
    public void testChooseDirection() {
        

    }

    @Test
    public void testCar() {
        fail("Not yet implemented");
    }

}
