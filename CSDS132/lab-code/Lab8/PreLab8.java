/* Trevor Swan
 * tcs94
 * CSDS132 - Pre Lab for Lab 8
 * 3/2/24
 */

import java.io.StringReader;
import java.io.IOException;
import java.util.Arrays;

public class PreLab8 {
    /*
     * VS code insisted that I had to include a throw statement in my main method
     * where read is called
     */
    public static void main(String[] args) throws IOException {
        // Create the input string and initialize a StringReader with it
        String input = "abcdef";
        StringReader readerTest = new StringReader(input);

        /*
         * Create an int to store the output of the read method, int because read
         * outputs ASCII values
         */
        int currentCharacter;

        // This loop showed me that the end of the String is reached when -1 is returned
        int i = 0;
        while (i < 10) {
            currentCharacter = readerTest.read();
            System.out.print(currentCharacter);
            i++;
        }

        System.out.println();
        readerTest.close();

        /*
         * Create the input and initialize a StringBuilder to do the actual assigned
         * task
         */
        input = "CSDS132";
        StringReader readerTrue = new StringReader(input);

        /*
         * This Loop prints the output of the read method as a Character until -1 is
         * returned.
         * Each loop iteration sets the currentCharacter to the read value and then
         * checks it against -1
         */
        while ((currentCharacter = readerTrue.read()) != -1) {
            // Print the character to the screen, must be typecasted first as read is ASCII
            System.out.println((char) currentCharacter);
        }

        System.out.println("'-1' was returned. No more characters to be read!\n");
        readerTrue.close();

        // Or you can do the same but store characters in an array
        StringReader readerArray = new StringReader(input);

        // Create new character and int arrays
        int[] intArray = new int[7];
        char[] charArray = new char[7];

        // Reset the index from above to index the arrays
        i = 0;
        while ((currentCharacter = readerArray.read()) != -1) {
            intArray[i] = currentCharacter;
            charArray[i] = (char) currentCharacter;
            i++;
        }

        // Print the array to the screen with the API implementation
        System.out.println(Arrays.toString(intArray)); // Shows the ASCII characters as ints
        System.out.println(Arrays.toString(charArray)); // Shows the actual characters

        readerArray.close();
        /*
         * Note: The downside to using arrays here is that you MUST know the length of
         * the string beforehand, which adds extra code is its an input
         */
    }
}
