import java.util.*;
class Solution {
    boolean solution(String s) {
        Stack<Character> opers = new Stack<>();
        
        // 괄호가 홀수개면 올바른 괄호가 아님
        if(s.length() % 2 != 0) {
            return false;
        }
        
        for(char c : s.toCharArray()) {
            if(c == '(') {
                opers.push(c);
            } else {
                if(!opers.isEmpty()) {
                    opers.pop();
                } else {
                    return false;
                }
            }
        }
        
        // 모든 s를 순회했는데 괄호 짝이 안맞으면 올바른 괄호가 아님
        if(!opers.isEmpty()) {
            return false;
        }
        
        return true;
    }
}