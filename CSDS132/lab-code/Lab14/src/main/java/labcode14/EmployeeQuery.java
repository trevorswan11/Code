package labcode14;

import java.util.*;

/** A class that lets a user query data from an employee */
public class EmployeeQuery {

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
        String nextCommand;

        // until the user enters "quit", get the next input from the user, and if it
        // matches
        // a given command, get the desired information from the employee object
        do {
            System.out.print("Enter command >> ");
            nextCommand = scanner.next();
            if (nextCommand.equals("getName")) {
                System.out.println(e.getName());
            } else if (nextCommand.equals("getAddress")) {
                System.out.println(e.getAddress());
            } else if (nextCommand.equals("getCity")) {
                System.out.println(e.getCity());
            }
        } while (!nextCommand.equals("quit"));
        scanner.close();
    }
}