public class PreLab5 {
    /* This method uses a WHILE loop to sum the first 10 positive integers */
    public int firstTenWhile() {
        int count = 0;
        int sum = 0;
        while (count <= 10) {
            sum = sum + count;   
            count = count + 1;;    
        }
        return sum;
    }

     /* This method uses a FOR loop to sum the first 10 positive integers */
    public int firstTenFor() {
        int sum = 0;
        for (int i = 0; i <= 10; i++) {
            sum = sum + i;
        }
        return sum;
    }

    public static void main(String[] args) {
        PreLab5 a = new PreLab5();
        int whileSum = a.firstTenWhile();
        int forSum = a.firstTenFor();
        System.out.printf("While Loop Sum = %d\nFor Loop Sum = %d",whileSum,forSum);
    }
}
