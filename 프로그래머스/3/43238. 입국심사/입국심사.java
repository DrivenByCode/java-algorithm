import java.util.*;
class Solution {
    public long solution(int n, int[] times) {
        Arrays.sort(times);
        int timesSize = times.length;
        long maxTime = (long) times[timesSize - 1] + 1;
        
        long lt = 0;
        long rt = maxTime * n;
        long sum;
        
        while(lt < rt) {
            long mid = lt + (rt - lt) / 2;
            sum = 0;
            
            for(int time : times) {
                sum += mid / time;
            }
            
            if(sum >= n) {
                rt = mid; 
            } else {
                lt = mid + 1;
            }
        }
        
        return lt;
    }
}