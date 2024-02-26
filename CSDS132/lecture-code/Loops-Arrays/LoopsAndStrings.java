import java.util.Scanner;
import java.util.Arrays;

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

    /* This is a better palindrome checker that ignores non-letter characters */
    public static boolean betterPalindrome(String s) {
        StringBuilder b = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (Character.isLetter(s.charAt(i))) {
                b.append(Character.toUpperCase(s.charAt(i)));
            }
        }
        return isPalindrome(b.toString());
    }

    public static boolean bestPalindrome(String s) {
        int front = 0;
        int back = s.length() - 1;

        while (front < back) {
            // Find the letter from front
            if (Character.isLetter(s.charAt(front)) != true) {
                front = front + 1;
            }

            // Find the letter from back
            else if (Character.isLetter(s.charAt(back)) != true) {
                back = back - 1;
            }

            // Compare
            else if (Character.toUpperCase(s.charAt(front)) == Character.toUpperCase(s.charAt(back))) {
                front = front + 1;
                back = back - 1;
            }

            else {
                return false;
            }
        }

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

    /* This method takes an array ints and reverses them */
    public static int[] reverse(int[] array) {
        for (int left = 0, right = array.length - 1; left < right; left = left + 1, right = right - 1) {
            // Remember the left
            int temp = array[left];

            // Swap
            array[left] = array[right]; 

            // place the temp
            array[right] = temp;
        }

        return array;
    }

    public static int[] betterReverse(int[] array) {
        for (int index = 0; index < array.length / 2; index++) {
            // remember the left
            int temp = array[index];

            // swap
            array[index] = array[array.length - 1 - index];

            // place the temp
            array[array.length - 1 - index] = temp;
        }
        return array;
    }

    /* This method will take an array as an it and an integer value
     * it adds the element to the back of the array adn returns the new array
     */
    public static int[] addToEnd(int[] array, int x) {
        // Create a new array which is one bigger than the input
        int[] newArray = new int[array.length + 1];

        // Place all of the elements in the input array to the newArray
        for (int i = 0; i < array.length; i++) {
            newArray[i] = array[i];
        }

        // Place the input element at the end
        newArray[newArray.length - 1] = x; 

        // return the new array
        return newArray;
    }

    /* This method is a basic linear search algorithm */
    public static int linearSearch(double x, double[] array) {
        for (int i = 0; i < array.length; i++) {
            if (x == array[i]) {
                return i;
            }
        }

        // At this point, the index of the element was not found, so return an impossible index
        return -1;
    }

    /* This is a binary search algorithm 
     * array must be sorted!
    */
    public static int binarySearch(int x, int[] array) {
        int start = 0; // smallest element index
        int end = array.length - 1; // index of the largest element

        while (start < end) {
            int mid = (start + end) / 2; // auto typecasting
            if (x < array[mid]) {
                end = mid;
            }
            else if (x > array[mid]) {
                start = mid;
            }
            else{
                return mid;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean run = true;
        System.out.println("Welcome to String Practice:\n\t1. Palindrome\n\t2. Capitalize\n\t3. Reverse Array\n\t4. Add to Array");
        System.out.println("\t5. Better Palindrome\n\t6. Best Palindrome\n\t7. Linear Search\n\t8. Quit");
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

            // Runs the array swap
            else if (input.equals("3")) {
                int[] x = {1, 2, 3, 4, 5};
                int[] y = LoopsAndStrings.reverse(x);
                int[] z = LoopsAndStrings.betterReverse(x);
                System.out.printf("Reverse: %s", Arrays.toString(y));
                System.out.println();
                System.out.printf("Better: %s", Arrays.toString(z));
                System.out.println();
            }

            // Adds an input element to an array
            else if (input.equals("4")) {
                int[] x = {1, 2, 3, 4, 5, 6};
                int y = sc.nextInt();
                int[] addition = LoopsAndStrings.addToEnd(x, y);
                System.out.println(Arrays.toString(addition));
            }

            // Asks for a string input and checks it with the better palindrome method
            else if (input.equals("5")) {
                System.out.println("What would you like to check: ");
                String palindrome = sc.next();
                System.out.println(LoopsAndStrings.betterPalindrome(palindrome));
            }

            // Asks for an input to use in the best palindrome method ever
            else if (input.equals("6")) {
                System.out.println("What would you like to check: ");
                String palindrome = sc.next();
                System.out.println(LoopsAndStrings.bestPalindrome(palindrome));
            }

            // Display the output of defined variables in main passed through method
            else if (input.equals("7")) {
                double x = 1.2;
                double[] array = {1.2, 2.3, 4.5, 2.7};
                System.out.println("Brute Force Method:");
                System.out.println(LoopsAndStrings.linearSearch(x, array));
                System.out.println("Binary Search Method:");
                System.out.println();
            }

            // Quits the program if the user doesn't want to continue
            else if (input.equals("8")) {
                System.out.println("Thank you!");
                run = false;
            }
        }
        sc.close();
    }
}
