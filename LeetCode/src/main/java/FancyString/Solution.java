package FancyString;

public class Solution {
    public String makeFancyString(String s) {
        StringBuilder ans = new StringBuilder();
        ans.append(s.charAt(0));
        int n = s.length(), cnt = 1;
        for (int i = 1; i < n; i++) {
            if (s.charAt(i) == ans.charAt(ans.length() - 1)) {
                cnt++;
                if (cnt < 3)
                    ans.append(s.charAt(i));
            } else {
                cnt = 1;
                ans.append(s.charAt(i));
            }
        }
        return ans.toString();
    }

    public static void test(String[] strs) {
        java.util.Arrays.stream(strs)
                        .map(new Solution()::makeFancyString)
                        .forEach(System.out::println);
    }

    public static void main(String[] args) {
        String[] tests = {
            "leeetcode",
            "aaabaaaa",
            "aab"
        };
        test(tests);
    }
}
