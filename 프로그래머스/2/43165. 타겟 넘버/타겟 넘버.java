class Solution {
    private static int count = 0;
    public static void dfs(int level, int sum, int target, int[] numbers) {
        if(level == numbers.length) {
            if(sum == target) {
                count++;
            }
            return;
        }
        

        dfs(level + 1, sum - numbers[level], target, numbers);
        dfs(level + 1, sum + numbers[level], target, numbers);
    }
    public int solution(int[] numbers, int target) {
        dfs(0, 0, target, numbers);
        return count;
    }
}