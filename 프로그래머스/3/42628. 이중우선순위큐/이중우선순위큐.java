import java.util.*;

class Solution {
    public int[] solution(String[] operations) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        
        for(String op : operations) {
            String[] strs = op.split(" ");
            if(strs[0].equals("I")) {
                int num = Integer.parseInt(strs[1]);
                maxHeap.offer(num);
                minHeap.offer(num);
            } else if(strs[0].equals("D") && !maxHeap.isEmpty()) {
                if(strs[1].equals("1")) {
                    int max = maxHeap.poll();
                    minHeap.remove(max);
                } else {
                    int min = minHeap.poll();
                    maxHeap.remove(min);
                }
            }
        }
        
        int max = maxHeap.isEmpty() ? 0 : maxHeap.poll();
        int min = minHeap.isEmpty() ? 0 : minHeap.poll();
        
        return new int[]{max, min};
    }
}
