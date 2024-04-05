import java.util.*;
class Solution {
    private static class Process {
        private final int index;
        private final int priority;
        private Process(final int index, final int priority) {
            this.index = index;
            this.priority = priority;
        }
    }
    public int solution(int[] priorities, int location) {        
        int cnt = 0;
        
        int len = priorities.length;
        
        Queue<Process> processes = new LinkedList<>();
        List<Integer> lowerPriorities = new ArrayList<>();
        
        for(int i = 0; i < len; i++) {
            processes.offer(new Process(i, priorities[i]));
            lowerPriorities.add(priorities[i]);
        }
        
        Collections.sort(lowerPriorities, Collections.reverseOrder());
        
        int idx = 0;
        
        while(!processes.isEmpty() && idx < lowerPriorities.size()) {
            Process p = processes.peek();
            int priority = lowerPriorities.get(idx);
            if(p.priority == priority) {
                processes.poll();
                cnt++;
                idx++;
                
                if(p.index == location) {
                    return cnt;
                }
            } else {
                processes.offer(processes.poll());
            }
        }
        
        return cnt;
    }
}