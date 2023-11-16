import java.util.*;
class Solution {
    public String solution(String[] participant, String[] completion) {
        HashMap<String, Integer> participantCount = new HashMap<>();
        for(String p : participant) {
            participantCount.put(p, participantCount.getOrDefault(p, 0) + 1);
        }
        
        for(String c : completion) {
            participantCount.put(c, participantCount.get(c) - 1);
        }
        
        for(Map.Entry<String, Integer> entry : participantCount.entrySet()) {
            if(entry.getValue() > 0) {
                return entry.getKey();
            }
        }
        return "";
    }
}