package ExceptionChallenge;

public class Averager {
    /**
     * Computes the integer average of a list of strings that represent numbers.
     * 
     * @param args an array of strings that represent numbers
     * @return the integer average of the numbers in the array
     * @throws BadDataException    if there are non-numeric values in the array
     * @throws ArithmeticException if there were no numeric values in the array
     */
    public static int average(String[] args) throws BadDataException, ArithmeticException {
        int count = 0;
        int sum = 0;

        // Loop through all the args
        for (int i = 0; i < args.length; i++) {
            try {
                sum += (int) Double.parseDouble(args[i]);
                count++;
            } catch (Exception e) {}
        }

        // Throw or return based on number of args read correctly
        if (count != args.length) {
            throw new BadDataException(sum / count);
        } else if (count == 0) {
            throw new ArithmeticException();
        }  else {
            return sum / count;
        }
    }

    /**
     * The method to launch the program.
     * 
     * @param args numbers to average
     */
    public static void main(String[] args) {
        try {
            System.out.println("Input Average: " + Averager.average(args));
        } catch (BadDataException e) {
            System.out.println("You entered some poor data! Average: " + e.getData());
        } catch (ArithmeticException e) {
            System.out.println("Unusable data!");
        } catch (Exception e) {
            System.out.println("Unhandled Exception Thrown!");
        }
    }
}
