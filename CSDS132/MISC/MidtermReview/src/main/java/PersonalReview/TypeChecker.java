package PersonalReview;

/** This class overloads a method I wrote to determine what type an input is */
public class TypeChecker {
    // Checks if input is an int
    public static int type(int input) {
        System.out.println(input + " is an int.");
        return input;
    }

    // Checks if the input if a byte
    public static byte type(byte input) {
        System.out.println(input + " is a byte.");
        return input;
    }

    // Checks if the input is a float
    public static float type(float input) {
        System.out.println(input + " is a float.");
        return input;
    }

    // Checks if the input is a double
    public static double type(double input) {
        System.out.println(input + " is a double.");
        return input;
    }

    // Checks if the input is a String
    public static String type(String input) {
        System.out.println(input + " is a String.");
        return input;
    }

    // Checks if the input is a boolean
    public static boolean type(boolean input) {
        System.out.println(input + " is a boolean.");
        return input;
    }

    // Checks if the input is a char
    public static char type(char input) {
        System.out.println(input + " is a char.");
        return input;
    }

    // Checks if the input is a short
    public static short type(short input) {
        System.out.println(input + " is a short.");
        return input;
    }

    // Checks if the input is a long
    public static long type(long input) {
        System.out.println(input + " is a long.");
        return input;
    }

    // Checks if the input is an Object
    public static Object type(Object input) {
        System.out.println(input + " is an object.");
        return input;
    }
}
