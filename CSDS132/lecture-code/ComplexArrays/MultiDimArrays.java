import java.util.Arrays;

public class MultiDimArrays{
    // Creates a k by k length
    public static int[][] createSquareMultiDim(int k) {
        // Creates 5 arrays of unspecified length
        int[][] grid = new int[k][];
        for (int i = 0; i < grid.length; i++) {
            grid[i] = new int[k];
        }
        return grid;
    }

    /**
     * Remove k from list[0..n-1].
     * Precondition: list[0..n-1] is sorted, and k is an element of list[0..n-1].
     * Postcondition: list[0..n-2] is sorted.
     * 
     * @param list the sorted array.
     * @param n    the number of items in list.
     * @param k    the number to remove from list.
     */
    public static void remove(int[] list, int n, int k) {
        // find the index of the element
        int i;
        for (i = 0; i < n && list[i] != k; i++);
            // the index at i is k ot i == n therefore k not in array

        // found or not found k; can be determined from i
        for ( ; i < n - 1; i++) {
            list[i] = list[i++];
        }
    }

    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 6};
        MultiDimArrays.remove(array, array.length, 4);
        System.out.println(Arrays.toString(array));
    }
}