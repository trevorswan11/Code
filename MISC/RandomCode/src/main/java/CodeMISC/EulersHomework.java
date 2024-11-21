package CodeMISC;

public class EulersHomework {

    // question 5, non-autonomous
    public static void q5calculation() {
        double initial_condition = 0.5;
        double yValue = initial_condition;
        double step = 0.5;
        double tValue = 0;
        double tFinal = 2;
        while (tValue <= tFinal) {
            System.out.printf("(%.2f, %.2f)\n", tValue, yValue);
            // define differential
            double slope = question5(yValue, tValue);
            yValue = yValue + slope * step;
            tValue = tValue + step;
        }
        System.out.println("----------");
    }

    // question 7, exponential function
    public static void q7calculation() {
        // define all variables
        double initial_condition = 2.0;
        double yValue = initial_condition;
        double step = 0.5;
        double tValue = 0;
        double tFinal = 2;
        while (tValue <= tFinal) {
            System.out.printf("(%.2f, %.2f)\n", tValue, yValue);
            // define differential
            double slope = question7(yValue);
            yValue = yValue + slope * step;
            tValue = tValue + step;
        }
        System.out.println("----------");
    }

    // question 15, variable step size with sqrt
    public static void q15calculation(double step) {
        // define all variables
        double initial_condition = 1.0;
        double yValue = initial_condition;
        double tValue = 0;
        double tFinal = 4;
        String stepSize;
        if (step == 1) {
            stepSize = "Step size = 1";
        } else if (step == 0.5) {
            stepSize = "Step size = 0.5";
        } else {
            stepSize = "Step size = 0.25";
        }
        System.out.printf("%s\n", stepSize);
        while (tValue <= tFinal) {
            System.out.printf("(%.2f, %.2f)\n", tValue, yValue);
            // define differential
            double slope = question15(yValue);
            yValue = yValue + slope * step;
            tValue = tValue + step;
        }
        System.out.println("----------");
    }

    // question 21, trig function w/ large step size over large interval
    public static void q21calculation() {
        // define all variables
        double initial_condition = -2.0;
        double yValue = initial_condition;
        double step = 1;
        double tValue = 0;
        double tFinal = 10;
        while (tValue <= tFinal) {
            System.out.printf("(%.2f, %.2f)\n", tValue, yValue);
            // define differential
            double slope = question21(yValue, tValue);
            yValue = yValue + slope * step;
            tValue = tValue + step;
        }
        System.out.println("----------");
    }

    // functions for slope
    public static double question5(double prev, double tValue) {
        double pred = Math.pow(prev, 2) - 4 * tValue;
        return pred;
    }

    public static double question7(double prev) {
        double pred = Math.exp(2 / prev);
        return pred;
    }

    public static double question15(double prev) {
        double pred = Math.sqrt(prev);
        return pred;
    }

    public static double question21(double prev, double tValue) {
        double pred = (2 * Math.cos(3 * tValue) - prev) / (4 * 0.5);
        return pred;
    }

    public static void main(String[] args) {
        q5calculation();
        q7calculation();
        q15calculation(1);
        q15calculation(0.5);
        q15calculation(0.25);
        q21calculation();
    }
}
