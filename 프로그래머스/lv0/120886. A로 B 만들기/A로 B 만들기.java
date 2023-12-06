import java.util.*;
class Solution {
    public int solution(String before, String after) {
        int answer = 0;
        
        char[] beforeChars = before.toCharArray();
        char[] afterChars = after.toCharArray();
        
        Arrays.sort(beforeChars);
        Arrays.sort(afterChars);
        
        String b = String.valueOf(beforeChars);
        String a = String.valueOf(afterChars);
        
        answer = b.equals(a) ? 1 : 0;
        
        return answer;
    }
}