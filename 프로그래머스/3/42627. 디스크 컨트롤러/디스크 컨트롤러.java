import java.util.*;
class Solution {
    public int solution(int[][] jobs) {
		PriorityQueue<int[]> sortedJobs = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
        
        int totalTime = 0;
        int endTime = 0;
        int count = 0;
        int index = 0;
        
        Arrays.sort(jobs, (o1, o2) -> (o1[0] - o2[0]));
        final int jobsLength = jobs.length;
        
        while(count < jobsLength) {
            // 작업 요청되는 시간이 종료시간 이전일 떄 큐에 삽입
            while(index < jobsLength && jobs[index][0] <= endTime) {
                sortedJobs.offer(jobs[index++]);
            }
            
            // 큐가 비었을 때
            if(sortedJobs.isEmpty()) {
                endTime = jobs[index][0];
            } else {
            // 큐가 차 있을 때
                int[] temp = sortedJobs.poll();
				totalTime += (temp[1] + endTime) - temp[0];
				endTime += temp[1];
                // 모든 jobs가 계산 됐을 때
				count++;
            }
        }
        
        return totalTime / count;
    }
}