import java.util.*;
class Solution {
    public int solution(int[] nums) {
        int answer = nums.length / 2;
        Set<Integer> sets = new HashSet<>();
        
        for(int i : nums) {
            sets.add(i);
        }
        
        return Math.min(answer, sets.size());
    }
}