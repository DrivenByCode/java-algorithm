import java.util.*;
class Solution {
    public int[] solution(int[] answers) {
        int[] scores = new int[3];
        
        final int[] first = {1, 2, 3, 4, 5};
        
        final int[] second = {2, 1, 2, 3, 2, 4, 2, 5};
        
        final int[] third = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};
                
        for(int i = 0; i < answers.length; i++) {
            if(first[i % first.length] == answers[i]) {
                scores[0]++;
            }
            
            if(second[i % second.length] == answers[i]) {
                scores[1]++;
            } 
            
            if(third[i % third.length] == answers[i]) {
                scores[2]++;
            }
        }
        
        int max = Arrays.stream(scores).max().getAsInt();
        
        List<Integer> scs = new ArrayList<>();
        
        for(int i = 0; i < scores.length; i++) {
            if(scores[i] == max) {
                scs.add(i + 1);
            }
        }
        
        int[] answer = new int[scs.size()];
        
        for(int i = 0; i < scs.size(); i++) {
            answer[i] = scs.get(i);
        }
        
        return answer;
    }   
}