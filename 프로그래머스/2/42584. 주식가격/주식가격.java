import java.util.*;
class Solution {
    public int[] solution(int[] prices) {
        int len = prices.length;
        int[] answer = new int[len];
        
        Queue<Integer> queue = new LinkedList<>();
        
        for(int price : prices) {
            queue.offer(price);
        }
        
        while(!queue.isEmpty()) {
            int price = queue.poll();
            
            for(int i = len - queue.size(); i < len; i++) {
                if(prices[i] - price < 0) {
                    answer[len - queue.size() - 1]++;
                    break;
                } else {
                    answer[len - queue.size() - 1]++;
                }
            }
        } 

        
        return answer;
    }
}