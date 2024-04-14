import java.util.*;

class Solution {
    public int solution(int[] people, int limit) {
        int answer = 0;
        Deque<Integer> weights = new ArrayDeque<>();
        
        Arrays.sort(people);
        
        for (int w : people) {
            weights.offerLast(w); // 사람들의 무게를 Deque에 저장
        }
        
        while (!weights.isEmpty()) {
            int current = weights.pollLast(); // 가장 무거운 사람을 먼저 꺼냄
            if (!weights.isEmpty() && current + weights.peekFirst() <= limit) {
                // 만약 가장 무거운 사람과 가장 가벼운 사람의 무게 합이 limit 이하라면
                weights.pollFirst(); // 가장 가벼운 사람도 함께 태우기
            }
            answer++; // 보트 한 척 추가
        }
        
        return answer;
    }
}
