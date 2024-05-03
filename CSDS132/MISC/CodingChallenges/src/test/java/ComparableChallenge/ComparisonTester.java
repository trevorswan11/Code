package ComparableChallenge;

import org.junit.*;
import static org.junit.Assert.*;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class ComparisonTester {
    // Tests the comparison implementation in measurement
    @Test
    public void measurementTester() {
        Measurement m1 = new Measurement(1);
        Measurement m2 = new Measurement(2);
        Measurement m3 = new Measurement(3);
        Measurement m4 = new Measurement(3);

        assertEquals(1, m2.compareTo(m1));
        assertEquals(0, m3.compareTo(m4));
        assertEquals(-1, m1.compareTo(m3));
    }

    // Tests the maximum value in a list method
    @Test
    public void listMaxTester() {
        // Try integers
        LinkedList<Integer> l1 = new LinkedList<Integer>();
        for (int i = 0; i < 10; i++) l1.add(i);
        assertEquals(9, CompareList.maxListValue(l1).intValue());

        // Try Doubles
        LinkedList<Double> l2 = new LinkedList<Double>();
        for (double i = 0; i < 1.34; i += 0.1) l2.add(i);
        assertEquals(1.3, CompareList.maxListValue(l2).doubleValue(), 0);

        // Try measurements
        LinkedList<Measurement> l3 = new LinkedList<Measurement>();
        for (double i = 0; i < 100; i += 2.78) l3.add(new Measurement(i));
        assertEquals(97.3, CompareList.maxListValue(l3).getQuantity(), 0.0001);

        // Test an empty list 
        LinkedList<Integer> l4 = new LinkedList<Integer>();
        try {
            CompareList.maxListValue(l4);
        } catch (NoSuchElementException e) {
            assertTrue(true);
        } catch (Exception e) {
            fail("Unhandled Exception Thrown: " + e.getClass());
        }
    }

    // Tests the Person class with method reference
    @Test
    public void personTester() {
        Person p1 = new Person(0);
        Person p2 = new Person(4);
        Person p3 = new Person(9);

        assertEquals(0, Person.ageDifference(p1, p1));
        assertEquals(5, Person.ageDifference(p3, p2));
        assertEquals(-5, Person.ageDifference(p2, p3));

        Comparator<Person> personComparator = Person.getComparator();
        assertEquals(0, personComparator.compare(p1, p1));
        assertEquals(-5, personComparator.compare(p2, p3));
        assertEquals(5, personComparator.compare(p3, p2));
    }
}
