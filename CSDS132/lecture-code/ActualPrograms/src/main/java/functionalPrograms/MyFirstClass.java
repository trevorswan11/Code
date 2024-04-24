package functionalPrograms;

/* This is our first example*/
public class MyFirstClass {
    // This is instance field
    public int myInstanceField;
    // this is class (static) field
    public static double myClassField;

    // This method multiplies two numbers
    public int myInstanceMethod(int x, int y) {
        int temp = x * y;
        return temp;
    }

    // This method adds two numbers
    public static double myClassMethod(double x, double y) {
        return x + y;
    }
}
