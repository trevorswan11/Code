package AbstractChallenge;

import org.junit.*;
import static org.junit.Assert.*;

public class ShapeTest {
    // Create test objects
    Ellipse ellipseOne = new Ellipse(3, 2);
    Ellipse ellipseTwo = new Ellipse(10, 20);
    Circle circle = new Circle(2);
    
    @Test
    public void smallEllipseTest() {
        assertEquals(3, ellipseOne.getMajorAxis(), 0);
        assertEquals(2, ellipseOne.getMinorAxis(), 0);
        assertEquals(6 * Math.PI, ellipseOne.getArea(), 0);
    }

    @Test
    public void largeEllipseTest() {
        assertEquals(10, ellipseTwo.getMinorAxis(), 0);
        assertEquals(20, ellipseTwo.getMajorAxis(), 0);
        assertEquals(200 * Math.PI, ellipseTwo.getArea(), 0);
    }

    @Test
    public void circleTest() {
        assertEquals(2, circle.getRadius(), 0);
        assertEquals(2, circle.getMinorAxis(), 0);
        assertEquals(2, circle.getMajorAxis(), 0);
        assertEquals(4 * Math.PI, circle.getArea(), 0);
    }
}
