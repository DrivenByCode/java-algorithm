import java.util.*;
class Solution {
    public int[] solution(int[] array, int[][] commands) {
        int len = commands.length;
        
        int[] answer = new int[len];
        int idx = 0;
        
        for(int[] command : commands) {
            int start = command[0] - 1;
            int end = command[1];
            int k = command[2] - 1;
            List<Integer> numbers = new ArrayList<>();
            
            for(int i = start; i < end; i++) {
                numbers.add(array[i]);
            }
            
            Collections.sort(numbers);
            
            answer[idx++] = numbers.get(k);
        }
        
        return answer;
    }
}