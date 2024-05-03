package ExceptionChallenge;

public class BadDataException extends Exception {
    // Value of the computation
    private final int data;

    // Declare a new instance with specified data
    public BadDataException(int data) {
        this.data = data;
    }

    // Return the data
    public int getData() {
        return this.data;
    }
}
