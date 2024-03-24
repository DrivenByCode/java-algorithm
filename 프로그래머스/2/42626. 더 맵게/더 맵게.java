import java.util.*;
class Solution {
    public int solution(int[] scoville, int K) {
        PriorityQueue<Integer> scovilles = new PriorityQueue<>();
        
        for(int i : scoville) {
            scovilles.offer(i);
        }
                
        int count = 0;
        
        while(!scovilles.isEmpty()) {
            int spicyLevel = 0;

            if(scovilles.peek() >= K) {
                return count;
            }
            
            spicyLevel += scovilles.poll();
            
            if(scovilles.isEmpty()) {
                return -1;
            }
            spicyLevel += scovilles.poll() * 2;
            
            scovilles.offer(spicyLevel);
            count++;
        }
        
        return count;
    }
}