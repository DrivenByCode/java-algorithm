import java.util.*;
class Solution {
        private int dfs(int level, int[] numbers, int target, int sum) {
        if(level == numbers.length) {
            return sum == target ? 1 : 0;
        }
        return dfs(level + 1, numbers, target, sum + numbers[level]) 
             + dfs(level + 1, numbers, target, sum - numbers[level]);
    }
    public int solution(int[] numbers, int target) {
        return dfs(0, numbers, target, 0);
    }
}