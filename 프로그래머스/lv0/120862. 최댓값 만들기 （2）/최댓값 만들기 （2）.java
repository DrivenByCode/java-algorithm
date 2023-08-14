import java.util.*;
class Solution {
    public int solution(int[] numbers) {
        int answer = Integer.MIN_VALUE;
        int n = numbers.length;
        Arrays.sort(numbers);
        
        int tmp = 0;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(i != j) {
                    tmp = numbers[i] * numbers[j];
                    answer = Math.max(tmp, answer);
                }
            }
        }
        
        return answer;
    }
}