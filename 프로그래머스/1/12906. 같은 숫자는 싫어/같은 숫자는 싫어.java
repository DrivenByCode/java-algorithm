import java.util.*;

public class Solution {
    public int[] solution(int[] arr) {        
        Stack<Integer> numbers = new Stack<>();
        
        for(int a : arr) {
            if(numbers.isEmpty()) numbers.push(a);
            else if(numbers.peek() != a) numbers.push(a);
        }
        
        int idx = 0;
        
        int[] answer = new int[numbers.size()];
        
        for(int n : numbers) {
            answer[idx++] = n;
        }

        return answer;
    }
}