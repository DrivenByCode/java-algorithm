import java.util.*;

class Solution {
    private static class TruckInfo {
        private final int leftTime;
        private final int truckWeight;
        
        private TruckInfo(final int leftTime, final int truckWeight) {
            this.leftTime = leftTime;
            this.truckWeight = truckWeight;
        }
    }
    
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        Queue<Integer> waiting = new LinkedList<>();
        Queue<TruckInfo> moving = new LinkedList<>();
        
        for (int t : truck_weights) {
            waiting.offer(t);
        }
        
        int time = 0;
        int totalWeight = 0;
        
        while (!moving.isEmpty() || !waiting.isEmpty()) {
            time++;
            
            // 다리를 건너는 트럭이 존재하고 다리를 지남 처리 해야할 때
            if (!moving.isEmpty() && moving.peek().leftTime == time) {
                totalWeight -= moving.poll().truckWeight;
            }
            
            // 기다리는 트럭이 존재하고, 트럭이 더 다리를 지나갈 수 있을 때
            if (!waiting.isEmpty() && totalWeight + waiting.peek() <= weight) {
                int truckWeight = waiting.poll();
                moving.offer(new TruckInfo(time + bridge_length, truckWeight));
                totalWeight += truckWeight;
            }
        }
        
        return time;
    }
}
