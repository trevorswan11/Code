package labcode14;

import java.util.*;
import java.lang.reflect.*;

/** A class that lets a user query data from an employee */
public class EmployeeQuery {
    /**
     * This method attempts to find an index in an array of methods that has a name
     * matching an input string.
     * 
     * @param methods An array of Methods from a class
     * @param name    The name of the method to find
     * @return The index where the method is in the array
     * @throws NoSuchMethodException If the method does not match any of those in
     *                               the array
     */
    public static int findMethod(Method[] methods, String name) throws NoSuchMethodException {
        for (int i = 0; i < methods.length; i++) {
            String methodName = EmployeeQuery.parseMethodName(methods[i].toString());
            if (methodName.equals(name)) {
                return i;
            }
        }

        // Throw exception if nothing was found
        throw new NoSuchMethodException("No methods matched that name.");
    }

    /**
     * Parses a method name based on java's string representation of a method.
     * 
     * @param method A method as a string to be parsed
     * @return The human-readable name of the method without modifiers
     */
    public static String parseMethodName(String method) {
        // First find the first open parentheses as that is where the params start
        int opening;
        for (opening = 0; method.charAt(opening) != '('; opening++);
        opening -= 1;

        // Now find the first period behind the parentheses
        int period;
        for (period = opening; method.charAt(period) != '.'; period--);
        period += 1;

        // Now create a string equal to the characters between the indices
        StringBuilder name = new StringBuilder();
        for (int i = period; i <= opening; i++) {
            name.append(method.charAt(i));
        }
        return name.toString();
    }
    
    /**
     * Determines the human-readable param types for a method.
     * @param params A class array of parameters for a method
     * @return A string array of the parameters
     */
    public static String[] retrieveParams(Class<?>[] params) {
        String[] result = new String[params.length];
        int index = 0;
        for (int i = 0; i < params.length; i++) {
            if (EmployeeQuery.isPrimitive(params[i].toString())) {
                result[index] = params[i].toString();
            }

            else {
                int start;
                StringBuilder temp = new StringBuilder();
                for (start = params[i].toString().length() - 1; params[i].toString().charAt(start) != '.'; start--);
                start += 1;
                for (int k = start; k < params[i].toString().length(); k++) {
                    temp.append(params[i].toString().charAt(k));
                }
                result[index] = temp.toString();
            }
            index++;
        }

        return result;
    }

    /**
     * Checks to see if an input is equal to a primitive type.
     * 
     * @param type The string representation of the type
     * @return A boolean indicating if the type is primitive.
     */
    public static boolean isPrimitive(String type) {
        for (int i = 0; i < type.length(); i++) {
            if (type.charAt(i) == ' ') {
                return false;
            }
        }
        return true;
    }

    /**
     * The program has a loop that lets a user enter queries about an employee and
     * stops when
     * the user enters "quit".
     * 
     * @param args the command line arguments are ignored
     */
    public static void main(String[] args) {
        Employee e = Employee.testEmployee(); // a sample employee

        Scanner scanner = new Scanner(System.in); // to parse data the user types in
        Scanner user = new Scanner(System.in);
        String nextCommand;

        // Collect the class to be used and initialize an empty method
        Class<?> cls = e.getClass();
        Method[] mArray = cls.getMethods();
        Method m;

        // until the user enters "quit", get the next input from the user, and if it
        // matches
        // a given command, get the desired information from the employee object
        do {
            System.out.print("Enter command >> ");
            nextCommand = scanner.next();
            // Attempt to get the method
            try {
                m = mArray[EmployeeQuery.findMethod(mArray, nextCommand)];
                Class<?>[] params = m.getParameterTypes();
                if (params.length != 0) {
                    String[] parameterTypes = EmployeeQuery.retrieveParams(params);
                    Object[] inputs = new Object[parameterTypes.length];
                    for (int i = 0; i < parameterTypes.length; i++) {
                        System.out.println("Enter a " + parameterTypes[i] + ": ");
                        String thisOne = user.next();
                        switch (thisOne) {
                            case "int":
                                inputs[i] = (Integer)Integer.parseInt(thisOne);
                                break;
                            case "double":
                                inputs[i] = (Double) Double.parseDouble(thisOne);
                                break;
                            case "Object":
                                inputs[i] = (Object) thisOne;
                                break;
                            default:
                                inputs[i] = thisOne;
                                break;
                        }
                    }

                    m.invoke(e, inputs);
                }

                else {
                    System.out.println(m.invoke(e));
                }
            }

            // Print an error message if method does not exist
            catch (Exception fail) {
                System.out.println(nextCommand.equals("quit") ? "Goodbye!" : "Invalid Method Call.");
            }
        } while (!nextCommand.equals("quit"));
        scanner.close();
        user.close();
    }
}