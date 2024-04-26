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
     * Checks to see if an input is an Employee.
     * 
     * @param type The string representation of a new Object
     * @return A boolean indicating if the type is an Employee instance.
     */
    public static boolean isEmployee(String type) {
        String modelInstance = "new Employee(first, middle, last)";
        boolean inArgs = false;
        // Check the equality up to the args of the constructor
        for (int i = 0; i < type.length() && !inArgs; i++) {
            if (type.charAt(i) != modelInstance.charAt(i)) {
                return false;
            } else if (type.charAt(i) == '(') {
                inArgs = true;
            }
        }

        // Check for 2 separate args in the constructor
        int numCommas = 0;
        int endIndex;
        for (endIndex = 12; type.charAt(endIndex) != ')'; endIndex++) {
            if (type.charAt(endIndex) == ',') {
                numCommas += 1;
            }
        }

        if (numCommas != 2) {
            return false;
        } else if (endIndex != type.length() - 1) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Creates an Employee instance based off of an input String.
     * 
     * @param request The requested Employee's information
     * @return A new employee instance with the given name
     */
    public static Employee makeEmployee(String request) {
        int startArgs;
        for (startArgs = 0; request.charAt(startArgs) != '('; startArgs++);
        startArgs += 1;
        String[] inputs = new String[3];
        int index = 0;
        while (index < 3) {
            int i;
            for (i = startArgs; request.charAt(i) != ','; i++) {
                inputs[index] += request.charAt(i);
            }
            index += 1;
            startArgs = i;
        }

        return new Employee(inputs[0], inputs[1], inputs[2]);
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
                        inputs[i] = user.nextLine();
                    }

                    // Some methods accept primitive values, and they only take one with no variance
                    if (EmployeeQuery.isPrimitive(parameterTypes[0]) || EmployeeQuery.isEmployee(inputs[0].toString())) {
                        String thisType = parameterTypes[0];
                        switch (thisType) {
                            case "int":
                                int inputInt = Integer.parseInt(inputs[0].toString());
                                m.invoke(e, inputInt);
                                break;
                            case "double":
                                double inputDouble = Double.parseDouble(inputs[0].toString());
                                m.invoke(e, inputDouble);
                                break;
                            case "Employee":
                                // This does not work as correctly with makeEmployee, but result is correct!
                                Employee e1 = EmployeeQuery.makeEmployee(inputs[0].toString());
                                System.out.println(m.invoke(e, e1));
                                break;
                        }
                    } else {
                        System.out.println(m.invoke(e, inputs));
                    }
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