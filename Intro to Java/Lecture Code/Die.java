// This class represents a die game
public class Die extends Object {
    // A field to represent the current value and die size
    private int currentValue = 1;
    // the final modifier makes it so that the field can never be modified
    private final int dieSize = 6;

    public static void main(String[] args) {
       Die d = new Die();
       d.getValue(); 
       d.roll();
    }

    // A method to access the current value
    public int getValue() {
        return currentValue;
    }

    // A method to change the current value with input
    public void setValue(int value) {
        if (value >= 1 && value <= dieSize){
            currentValue = value;
        }
    }

    // A method to roll a die
    public int roll() {
        setValue((int)(Math.random() * dieSize + 1));
        // you should use your methods, not directly calling the value
        return getValue();
    }
}