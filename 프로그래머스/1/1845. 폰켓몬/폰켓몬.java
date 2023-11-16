import java.util.*;
class Solution {
    public int solution(int[] nums) {
        HashMap<Integer, Integer> pocketmonCounts = new HashMap<>(); 
        for(int num : nums) {
            pocketmonCounts.put(num, pocketmonCounts.getOrDefault(num, 0) + 1);
        }
        
        int possibleCount = nums.length / 2;
        
        if(pocketmonCounts.size() < possibleCount) {
            return pocketmonCounts.size();
        } else {
            return possibleCount;
        }
    }
}