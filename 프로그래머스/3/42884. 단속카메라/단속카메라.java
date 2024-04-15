import java.util.*;
class Solution {
    private static class Route implements Comparable<Route> {
        private final int enterTime;
        private final int outTime;
        
        private Route(final int enterTime, final int outTime) {
            this.enterTime = enterTime;
            this.outTime = outTime;
        }
        
        @Override
        public int compareTo(Route other) {
            if(this.outTime == other.outTime) {
                return this.enterTime - other.enterTime;
            }
            return this.outTime - other.outTime;
        }
    }
    public int solution(int[][] routes) {
        int answer = 0;
        
        PriorityQueue<Route> pq = new PriorityQueue<>();
        
        for(int[] route : routes) {
            pq.offer(new Route(route[0], route[1]));
        }
        
        while(!pq.isEmpty()) {
            Route route = pq.poll();
            while(!pq.isEmpty() && route.outTime >= pq.peek().enterTime) {
                pq.poll();
            }
            answer++;
        }
        
        return answer;
    }
}