/* Trevor Swan
* Curtis Li
* Simon Eskin
* Lab 7
* 2/29/24
*/

/**
 * A collection of methods related to multi-dimensional arrays.
 */
public class Array2Lab {
    /**
     * Return whether k is in list.
     * Precondition: the elements of list are not null.
     * 
     * @param list the array to be searched.
     * @param k    the number to search for.
     * @return true if k is an element of list, and false otherwise.
     */
    public static boolean contains(Object[][] list, Object k) {
        for (int i = 0; i < list.length; i++) {
            for (int j = 0; j < list[i].length; j++) {
                if (list[i][j].equals(k)) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Create a String that has the contents of list. Each row of list should be on
     * a separate line,
     * separated by spaces, and surrounded by { and }.
     * The first line of of the output should start with an additional {, each other
     * line of the output
     * should start with a space, and the last line of the output should have an
     * additional {.
     * For example: {{1, 2}, {3, 4}} should be formatted in the String as:
     * {{1 2}
     *  {3 4}}
     * 
     * @param list the array to print.
     * @return the elements of the array, separated by spaces, and surrounded by {
     *         and }, with each row on a separate line.
     */
    public static String arrayToString(int[][] list) {
        StringBuilder builder = new StringBuilder("{");
        for (int i = 0; i < list.length; i++) {
            builder.append('{');
            for (int j = 0; j < list[i].length; j++) {
                builder.append(list[i][j]);
                if (j != list[i].length - 1) {
                    builder.append(' ');
                }
            }
            builder.append('}');
            if (i != list.length - 1) {
                builder.append("\n ");
            }
        }

        builder.append("}");
        return builder.toString();
    }

    /**
     * Create a new array that "flips" a rectangular region of matrix about the
     * diagonal. So, each row of the output should be a column of the input.
     * Precondition: The region of matrix[rowStart...rowEnd][colStart...colEnd]
     * exists.
     * 
     * @param matrix   the input two dimensional array.
     * @param rowStart the index of the first row of the rectangular region to flip.
     * @param rowEnd   the index of the last row of the rectangular region to flip.
     * @param colStart the index of the first column of the rectangular region to
     *                 flip.
     * @param colEnd   the index of the last column of the rectangular region to
     *                 flip.
     */
    public static int[][] transpose(int[][] matrix, int rowStart, int rowEnd, int colStart, int colEnd) {
        int[][] newArray = new int[colEnd - colStart + 1][rowEnd - rowStart + 1];
        for (int i = rowStart; i <= rowEnd; i++) {
            for (int j = colStart; j <= colEnd; j++) {
                newArray[j - colStart][i - rowStart] = matrix[i][j];
            }
        }

        return newArray;
    }

    /**
     * Creates a new array that is a copy of the input matrix, except that one row
     * and one column have been removed.
     * Precondition: the row index is between 0 (inclusive) and the number of rows
     * of matrix (not inclusive)
     * 
     * @param matrix the input two dimensional array
     * @param row    the index of the row to remove
     * @param col    the index of the column to remove
     */
    public static int[][] removeRowAndCol(int[][] matrix, int row, int col) {
        return null;
    }
}
