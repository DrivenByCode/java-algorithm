import java.util.*;
class Solution {
    private static class Job {
        private final int prog;
        private final int spd;
        private Job(final int prog, final int spd) {
            this.prog = prog;
            this.spd = spd;
        }
    }
    public int[] solution(int[] progresses, int[] speeds) {
        Queue<Job> jobs = new LinkedList<>();

        int len = progresses.length;
        
        for(int i = 0; i < len; i++) {
            jobs.offer(new Job(progresses[i], speeds[i]));
        }
        
        int day = 0;
        int cnt = 0;
        
        List<Integer> days = new ArrayList<>();
        
        while(!jobs.isEmpty()) { 
            day++;
            
            while(!jobs.isEmpty()) {
                Job job = jobs.peek();
                if((100 - job.prog) <= job.spd * day) {
                    jobs.poll();
                    cnt++;
                } else {
                    break;
                }
            }
            
            if(cnt > 0) {
                days.add(cnt);
                cnt = 0;
            }
        }
        
        return days.stream().mapToInt(i -> i).toArray();
    }
}