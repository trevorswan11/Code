public class EulersHomework {
    public static void main(String[] args){
        EulersHomework.q5calcultion();
        EulersHomework.q7calculation();
        EulersHomework.q15calculation(1);
        EulersHomework.q15calculation(0.5);
        EulersHomework.q15calculation(0.25);
        EulersHomework.q21calculation();
    }
    // question 5, non-autonomous
    public static void q5calcultion() {
        double initial_condition = 0.5;
        double yval = initial_condition;
        double step = 0.5;
        double tval = 0;
        double tfinal = 2;
        while(tval<=tfinal){
            System.out.printf("(%.2f, %.2f)\n",tval,yval);
            // define differential
            double slope = EulersHomework.question5(yval, tval);
            yval = yval + slope * step;
            tval = tval + step;
    }
        System.out.println("----------");
        }
    // question 7, exponential function
    public static void q7calculation() {
       // define all variables
        double initial_condition = 2.0;
        double yval = initial_condition;
        double step = 0.5;
        double tval = 0;
        double tfinal = 2;
        while(tval<=tfinal){
            System.out.printf("(%.2f, %.2f)\n",tval,yval);
            // define differential
            double slope = EulersHomework.question7(yval);
            yval = yval + slope * step;
            tval = tval + step;
    }
        System.out.println("----------");
        }
    // question 15, variable step size with sqrt
    public static void q15calculation(double step) {
        // define all variables
            double initial_condition = 1.0;
            double yval = initial_condition;
            double tval = 0;
            double tfinal = 4;
            String stepsize;
            if (step == 1) {
                stepsize = "Step size = 1";
            } else if (step == 0.5){
                stepsize = "Step size = 0.5";
            } else {
                stepsize = "Step size = 0.25";
            } 
                System.out.printf("%s\n",stepsize);
            while(tval<=tfinal){
                System.out.printf("(%.2f, %.2f)\n",tval,yval);
                // define differential
                double slope = EulersHomework.question15(yval);
                yval = yval + slope * step;
                tval = tval + step;
        }
            System.out.println("----------");
            }
    // question 21, trig function w/ large step size over large interval
    public static void q21calculation() {
        // define all variables
            double initial_condition = -2.0;
            double yval = initial_condition;
            double step = 1;
            double tval = 0;
            double tfinal = 10;
            while(tval<=tfinal){
                System.out.printf("(%.2f, %.2f)\n",tval,yval);
                // define differential
                double slope = EulersHomework.question21(yval, tval);
                yval = yval + slope * step;
                tval = tval + step;
        }
            System.out.println("----------");
            }
        // functions for slope
    public static double question5(double prev, double tval){
        double pred = Math.pow(prev, 2) - 4*tval;
        return pred;
    }
    public static double question7(double prev){
        double pred = Math.exp(2/prev);
        return pred;
    }
    public static double question15(double prev){
        double pred = Math.sqrt(prev);
        return pred;
    }
    public static double question21(double prev, double tval){
        double pred = (2 * Math.cos(3 * tval) - prev) / (4 * 0.5);
        return pred;
    }
}