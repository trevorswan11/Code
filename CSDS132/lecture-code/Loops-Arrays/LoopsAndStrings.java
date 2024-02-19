import java.util.Scanner;

public class LoopsAndStrings {
    
    /* This method determines if an inputted string is a palindrome or not */
    public static boolean isPalindrome(String s) {
        for (int offset = 0; offset < s.length() / 2; offset = offset + 1) {
            // s.length starts at a 1 index, unlike the rest of the language which starts at 0
            if (s.charAt(offset) != s.charAt((s.length() - 1) - offset)) {
                return false;
            }
        }

        // The entire loop completed without finding a mismatch
        return true;
    }

    /* This method capitalizes a string without the built-in function */
    public static String allCapitalized(String s) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < s.length(); i = i + 1) {
            char c = s.charAt(i);
            // Uses a common way to capitalize letters by making use of characters as integers
            if (c >= 'a' && c <= 'z') {
                c = (char)(c - 'a' + 'A');
            }
            result.append(c);
        }
        return result.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean run = true;
        System.out.println("Welcome to String Practice:\n\t1. Palindrome\n\t2. Capitalize\n\t3. Quit");
        while (run) {
            System.out.print("What would you like to do: ");
            String input = sc.next();
            // Runs if the user wants to check palindrome
            if (input.equals("1")) {
                System.out.print("What would you like to Check: ");
                String s = sc.next();
                System.out.println(LoopsAndStrings.isPalindrome(s));
            }
            // Runs if the user wants to capitalize a String
            else if (input.equals("2")) {
                System.out.print("What would you like to Capitalize: ");
                String s = sc.next();
                System.out.println(LoopsAndStrings.allCapitalized(s));
            }
            // Quits the program if the user doesn't want to continue
            else if (input.equals("3")) {
                System.out.println("Thank you!");
                run = false;
            }
        }
        sc.close();
    }
}
