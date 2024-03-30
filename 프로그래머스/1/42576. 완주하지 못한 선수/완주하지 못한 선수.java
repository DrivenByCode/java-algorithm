import java.util.*;
class Solution {
    public String solution(String[] participant, String[] completion) {
        Map<String, Integer> participants = new HashMap<>();
        
        for(String p : participant) {
            participants.put(p, participants.getOrDefault(p, 0) + 1);
        }
        
        for(String c : completion) {
            participants.put(c, participants.get(c) - 1);
            
            if(participants.get(c) <= 0) {
                participants.remove(c); 
            }
        }
        
        Set<String> keys = participants.keySet();
        
        String ans = "";
        
        for(String s : keys) {
            ans = s;
        }
        
        return ans;
    }
}