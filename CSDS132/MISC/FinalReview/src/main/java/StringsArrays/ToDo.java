package StringsArrays;

/**
 * A class to organize and operate on a hypothetical ToDo-list.
 * 
 * @author Trevor Swan
 * @version CSDS132 - Final Review
 */
public class ToDo {
    // A field to store an array of tasks
    private String[] tasks;

    /**
     * Creates a ToDo list based on an inputted String array.
     * 
     * @param tasks A String array of tasks 
     */
    public ToDo(String[] tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a description to each task in the array.
     * 
     * @param descToAdd the desired String description
     */
    public void addToTask(String descToAdd) {
        for (int i = 0; i < tasks.length; i++) {
            tasks[i] += descToAdd;
        }
    }

    /**
     * Attempts to add a description to tasks [start, end]
     * 
     * @param descToAdd the desired String description
     * @param start the first index to add to
     * @param end the last index to add to
     */
    public void addToTask(String descToAdd, int start, int end) {
        // Try with the given indices
        try {
            for (int i = start; i <= end; i++) {
                tasks[i] += descToAdd;
            }
        }

        // If the indices are out of bounds, inform the user
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Invalid Start or End index");
        }
    }

    /**
     * Changes all periods to exclamations at the inputted index.
     * 
     * @param index the int index of the array
     */
    public void makeExcited(int index) {
        // Try with the given indices
        try {
            // Grab the string at the index
            String s = tasks[index];

            // Create a StringBuilder to handle the changes
            StringBuilder b = new StringBuilder();

            // Loop through the string and change all periods
            for (int i = 0; i < s.length(); i++) {
                // If the character is a period, change it, otherwise preserve
                b.append(s.charAt(i) == '.' ? "!" : s.charAt(i));
            }
            tasks[index] = b.toString();
        }
        
        // If the indices are out of bounds, inform the user
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Invalid index");
        }
    }

    /**
     * Loops through the array and determines if the input is equal to the stored task.
     * 
     * @param task the task to compare
     * @return true if the task is present in the array
     */
    public boolean taskExists(String task) {
        for (String s : tasks) {
            if (s.equals(task)) {
                return true;
            }
        }
        return false;
    }
}
