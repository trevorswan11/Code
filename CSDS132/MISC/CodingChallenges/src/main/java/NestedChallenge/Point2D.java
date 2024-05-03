package NestedChallenge;

import java.util.Comparator;

public class Point2D {
    // values to store instance coordinates
    private double x;
    private double y;

    // A constructor to make a 2D point
    public Point2D(double x, double y) {
        this.setX(x);
        this.setY(y);
    }

    // Getter for x
    public double getX() {
        return this.x;
    }

    // Setter for x
    public void setX(double x) {
        this.x = x;
    }

    // Getter for y
    public double getY() {
        return this.y;
    }

    // Setter for y
    public void setY(double y) {
        this.y = y;
    }

    // Return a manhattan comparator instance
    public static Comparator<Point2D> getManhattanComparator() {
        return new ManhattanComparison();
    }

    // Static nested class to handle comparisons
    private static class ManhattanComparison implements Comparator<Point2D> {
        private static double manhattanDistance(Point2D point) {
            return Math.abs(point.getX()) + Math.abs(point.getY());
        }

        public int compare(Point2D point1, Point2D point2) {
            double point1Dist = ManhattanComparison.manhattanDistance(point1);
            double point2Dist = ManhattanComparison.manhattanDistance(point2);

            return (point1Dist - point2Dist != 0 ? (point1Dist > point2Dist ? 1 : -1) : 0);
        }
    }

    // Return a euclidean comparator instance
    public Comparator<Point2D> getEuclideanComparator() {
        return new EuclideanComparison();
    }

    // Non-static nested class to handle euclidean comparisons
    private class EuclideanComparison implements Comparator<Point2D> {
        private double euclideanDistance(Point2D pointA, Point2D pointB) {
            return Math.sqrt(Math.pow(pointA.getX() - pointB.getX(), 2) +
                   Math.pow(pointA.getY() - pointB.getY(), 2));
        }

        public int compare(Point2D point1, Point2D point2) {
            double distToOne = this.euclideanDistance(point1, Point2D.this);
            double distToTwo = this.euclideanDistance(point2, Point2D.this);

            return (distToOne - distToTwo != 0 ? (distToOne > distToTwo ? 1 : -1) : 0);
        }
    }
    
    public static Comparator<Point2D> compareByPNorm(int p) {
        return new Comparator<Point2D>() {
            private double getPNorm(Point2D point) {
                return Math.pow(Math.abs(point.getX()), p) + Math.pow(Math.abs(point.getY()), p);
            }

            public int compare(Point2D point1, Point2D point2) {
                double point1P = this.getPNorm(point1);
                double point2P = this.getPNorm(point2);

                return (point1P - point2P != 0 ? (point1P > point2P ? 1 : -1) : 0);
            }
        };
    }
}
