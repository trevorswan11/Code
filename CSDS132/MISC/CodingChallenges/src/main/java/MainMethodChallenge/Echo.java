package MainMethodChallenge;

public class Echo {
    // The main method takes the input of the string typed in when ran
    public static void main(String[] args) {
        // Create a StringBuilder to read the input
        StringBuilder input = new StringBuilder();

        // Read the entire string
        for (int i = 0; i < args.length - 1; i++) {
            // Append the next word, then a space
            input.append(args[i]);
            input.append(' ');
        }

        // Print the last word with no trailing whitespace
        if (args.length > 0) {
            input.append(args[args.length - 1]);
        }

        // Print the input to the screen as requested
        System.out.println(input.toString());
    }
}
