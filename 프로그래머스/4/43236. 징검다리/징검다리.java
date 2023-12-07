import java.util.*;
class Solution {
    private int getRemoveCount(int distance, int[] rocks, int minimumLength) {
        int removeCount = 0;
        int prev = 0;
        
        for(int i = 0; i < rocks.length; i++) {
            if(rocks[i] - prev < minimumLength) {
                removeCount++;
            } else {
                prev = rocks[i];
            }
        }
        if(distance - prev < minimumLength) {
            removeCount++;
        }
        return removeCount;
    }
    public int solution(int distance, int[] rocks, int n) {
        Arrays.sort(rocks);

        int lt = 0;
        int rt = distance + 1;
        
        while (lt < rt) {
            int mid = (lt + rt) / 2;
            if (getRemoveCount(distance, rocks, mid) <= n) {
                lt = mid + 1;
            } else {
                rt = mid;
            }
        }
        
        // upper-bound이므로 lt - 1;
        return lt - 1;
    }
}