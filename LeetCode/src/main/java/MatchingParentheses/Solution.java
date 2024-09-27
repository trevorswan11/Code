package MatchingParentheses;

import java.util.Stack;

public class Solution {
    public boolean isValid(String s) {
        Stack<Character> paren = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(' || s.charAt(i) == '{' || s.charAt(i) == '[') {
                paren.push(s.charAt(i));
            } else if (s.charAt(i) == ')' && !paren.isEmpty() && paren.peek() == '(') {
                paren.pop();
            } else if (s.charAt(i) == '}' && !paren.isEmpty() && paren.peek() == '{') {
                paren.pop();
            } else if (s.charAt(i) == ']' && !paren.isEmpty() && paren.peek() == '[') {
                paren.pop();
            } else {
                return false;
            }
        }
        return paren.isEmpty();
    }
}
