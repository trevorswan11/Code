public class PreLab6 {
    public static void main(String[] args) {
        // Create an int array with 1000 elements
        int[] a = new int[1000];

        // Fill the array with values 1 to 1000
        for (int i = 0; i < 1000; i++) {
        a[i] = i + 1;
        }

        // Sum up all of the even indexed integers in a
        int sumEven = 0;
        int indexEven = 0;
        while (indexEven < 1000) {
            sumEven = a[indexEven] + sumEven;
            indexEven = indexEven + 2;
        }

        // Sum up all of the odd integers
        int sumOdd = 0;
        int indexOdd = 1;
        while (indexOdd < 1000) {
            sumOdd = a[indexOdd] + sumOdd;
            indexOdd = indexOdd + 2;
        }

        // Display the results to the user
        System.out.printf("Even Index Sum = %d\n", sumEven);
        System.out.printf("Odd Index Sum = %d\n", sumOdd);
    }
}