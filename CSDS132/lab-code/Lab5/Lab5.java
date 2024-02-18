/* Trevor Swan
 * Simon Eskin
 * Curtis Li
 * 2/15/24 Lab 5
 */

public class Lab5 {
    public static int countSpaces(String s) {
        int count = 0;
        int i = 0;
        while (s.length() > i) {
            if (s.charAt(i) == ' '){
                count = count + 1;
            }
            i = i + 1;
        }
        return count;
    }

    public static String removeSpaces(String s) {
        int i = 0;

        StringBuilder newString = new StringBuilder("");
        while (s.length() > i) {
            if (s.charAt(i) != ' ') {
                newString.append(s.charAt(i));
            }
            i = i + 1;
        }

        return newString.toString();
    } 

    public static String everyNthChar(String s, int n) {
        s = s.trim();
        StringBuilder nthCharacters = new StringBuilder();
        for (int i = 0; i < s.length(); i = i + n) {
            nthCharacters.append(s.charAt(i));
            if (i + n < s.length()) {
                nthCharacters.append(' ');
            }
        }
        return nthCharacters.toString();
    }

    public static void main(String[] args) {
        System.out.println(Lab5.countSpaces(" h e l l o   9 "));
        System.out.println(Lab5.removeSpaces("     d a d    90 jk e 4 "));
        System.out.println(Lab5.everyNthChar("abcdefg", 2));
    }
}
