package CodeMISC;

import java.util.Arrays;

public class OptimizeThis {
    // SOLUTION TO OPTIMIZATION STATION
    static double maxVolume = 0;
    static double bestLength = 0;
    static double bestWidth = 0;
    static double bestHeight = 0;

    /**
     * Calculates the maximum volume of a rectangular box that can be built from
     * given surface area and total edge length.
     * 
     * @param SA         the required surface area of the box
     * @param EL         the total edge length, found by summing the length of each
     *                   side
     * @param iterations the total number of iterations to do
     */
    static void optimizationStation(double SA, double EL, int iterations) {
        // Loop through possible combinations of l, w, and h
        for (int l = 1; l < iterations; l++) {
            for (int w = 1; w < iterations; w++) {
                // EL = 4 (l * w * h), but rearrange for h
                double h = (EL / 4.0) - l - w;

                // Ensure EL calculation leads to positive height
                if (h > 0) {
                    double currentSA = 2 * (l * w + l * h + w * h);

                    // Check the SA constraint
                    if (currentSA == SA) {
                        double currentVolume = l * w * h;

                        // Check for a bigger volume, assign if found
                        if (currentVolume > maxVolume) {
                            maxVolume = currentVolume;
                            bestLength = l;
                            bestWidth = w;
                            bestHeight = h;
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        optimizationStation(1200, 180, 8000);
        System.out.println("Volume: " + maxVolume);
        System.out.println(Arrays.toString(new double[] { bestLength, bestWidth, bestHeight }));
    }
}
