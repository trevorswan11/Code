package NestedChallenge;

import org.junit.*;
import static org.junit.Assert.*;

import java.util.Comparator;

public class Point2DTest {
    // A field to store the point comparator
    Comparator<Point2D> pointComparator;

    // Fields to store the points
    Point2D pointOne = new Point2D(0, 0);
    Point2D pointTwo = new Point2D(1, 2);
    Point2D pointThree = new Point2D(3, 4);

    // Test the manhattan static class
    @Test
    public void manhattanTest() {
        pointComparator = Point2D.getManhattanComparator();
        assertEquals(0, pointComparator.compare(pointOne, pointOne));
        assertEquals(1, pointComparator.compare(pointTwo, pointOne));
        assertEquals(-1, pointComparator.compare(pointTwo, pointThree));
    }

    // Test the euclidean non-static class
    @Test
    public void euclideanTest() {
        pointComparator = pointOne.getEuclideanComparator();
        assertEquals(0, pointComparator.compare(pointOne, pointOne));
        assertEquals(1, pointComparator.compare(pointThree, pointOne));
        assertEquals(-1, pointComparator.compare(pointTwo, pointThree));
    }

    // Test the pNorm class
    @Test
    public void pNormTest() {
        pointComparator = Point2D.compareByPNorm(2);
        assertEquals(0, pointComparator.compare(pointOne, pointOne));
        assertEquals(-1, pointComparator.compare(pointOne, pointTwo));
        assertEquals(1, pointComparator.compare(pointThree, pointTwo));
    }
}
