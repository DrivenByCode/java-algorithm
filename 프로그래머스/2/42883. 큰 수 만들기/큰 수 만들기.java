import java.util.*;
class Solution {
    public String solution(String number, int k) {
        int len = number.length();
        
        char[] answer = new char[len - k];
        
        Deque<Character> stack = new ArrayDeque<>();
        
        for(int i = 0; i < len; i++) {
            char c = number.charAt(i);
            while(!stack.isEmpty() && stack.peek() < c && k-- > 0) {
                stack.pop();
            }
            stack.push(c);
        }
        
        for(int i = 0; i < answer.length; i++) {
            answer[i] = stack.pollLast();
        }
        
        return String.valueOf(answer);
    }
}