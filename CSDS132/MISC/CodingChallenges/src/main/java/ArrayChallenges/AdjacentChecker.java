package ArrayChallenges;

public class AdjacentChecker {
    // An array to store search field
    private static int[][] searchField = new int[8][2];

    /**
     * This private helper method declares the search field for a point.
     * 
     * @param row the row value of the point
     * @param col the col value of the point
     */
    private static void declareField(int row, int col) {
        searchField[0] = new int[] { row - 1, col + 1 };  
        searchField[1] = new int[] { row - 1, col - 1 }; 
        searchField[2] = new int[] { row - 1, col }; 
        searchField[3] = new int[] { row - 1, col + 1 }; 
        searchField[4] = new int[] { row, col - 1 }; 
        searchField[5] = new int[] { row, col + 1 }; 
        searchField[6] = new int[] { row + 1, col + 1 }; 
        searchField[7] = new int[] { row + 1, col }; 
    }

    /**
     * Checks around a point using its searchField and determines if there is a
     * matching number in any adjacent index.
     * 
     * @param array the array to check
     * @param row the row to check in the array
     * @param col the col to check in the array
     * @return true if there is a matching adjacent element
     */
    public static boolean hasAdjacent(int[][] array, int row, int col) {
        // Loop through the values in the field and check equality
        for (int i = 0; i < 8; i++) {
            try {
                // declare field and store position of interest
                AdjacentChecker.declareField(row, col);
                int positionValue = array[row][col];

                // try to compare the values
                boolean comparison = positionValue == array[searchField[i][0]][searchField[i][1]];
                if (comparison) {
                    return true;
                }
            } catch (ArrayIndexOutOfBoundsException e) {}
        }
        return false;
    }

    /**
     * Checks to see if the array is packed, meaning that there are no adjacent
     * cells and all cells are non-zero.
     * 
     * @param array A two dimensional array to check
     * @return true if the board is packed
     */
    public static boolean isPacked(int[][] array) {
        // check adjacency of each position in the array
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                // check for zero at the element
                if (array[i][j] == 0) {
                    return false;
                } 
                
                // check the adjacency about the element
                else if (AdjacentChecker.hasAdjacent(array, i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

     /**
     * Checks to see if the array is packed, meaning that there are no adjacent cells.
     * 
     * @param array A one dimensional array to check
     * @return true if the board is packed
     */
    public static boolean isPacked(int[] array) {
        // Treat 1D as 2D and send to overloaded method
        return AdjacentChecker.isPacked(new int[][] { array });
    }
}