import java.util.*;
class Solution {
    private static class MinPoint implements Comparable<MinPoint> {
        private int len;
        private int[] indices;
        private MinPoint(int len, int[] indices) {
            this.len = len;
            this.indices = indices;
        }
        
        @Override
        public int compareTo(MinPoint other) {
            if(this.len == other.len) {
                return this.indices[0] - other.indices[0];
            }
            return this.len - other.len;
        }
    }
    public int[] solution(int[] sequence, int k) {        
        int lt = 0;
        
        int result = 0;
        int len = 0;
        
        PriorityQueue<MinPoint> pq = new PriorityQueue<>();
        
        for(int rt = 0; rt < sequence.length; rt++) {
            result += sequence[rt];
            len++;
            if(result == k) {
                pq.offer(new MinPoint(len, new int[]{lt, rt}));
            }
            while(result > k) {
                result -= sequence[lt++];
                len--;
                if(result == k) {
                    pq.offer(new MinPoint(len, new int[]{lt, rt}));
                }
            }
        }
     
        return pq.poll().indices;
    }
}