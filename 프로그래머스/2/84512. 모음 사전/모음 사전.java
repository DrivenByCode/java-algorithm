import java.util.*;
class Solution {
    private static final char[] chars = {'A', 'E', 'I', 'O', 'U'};
    private static List<String> lists = new ArrayList<>();
    
    private static void getWord(String str, int len) {
        lists.add(str);
        
        if(len == 5) return;
        
        for(int i = 0; i < 5; i++) {
            getWord(str + chars[i], len + 1);
        }
    }
    
    public int solution(String word) {
        int answer = 0;
        
        getWord("", 0);
        
        // 0번 째는 ""(빈 문자열)
        for(int i = 0; i < lists.size(); i++) {
            if(word.equals(lists.get(i))) {
                answer = i;
            }
        }
        
        return answer;
    }
}