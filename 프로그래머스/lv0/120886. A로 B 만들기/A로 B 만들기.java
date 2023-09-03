import java.util.*;
class Solution {
    public int solution(String before, String after) {
        int answer = 0;
        
        char[] beforeChars = before.toCharArray();
        char[] afterChars = after.toCharArray();
        
        Arrays.sort(beforeChars);
        Arrays.sort(afterChars);
        
        if(Arrays.equals(beforeChars, afterChars)) {
            answer = 1;
        } 
        
        return answer;
    }
}