class Solution {
    public int solution(int[][] triangle) {
        int answer = 0;
        int rowLength = triangle.length;
        
        int[][] dp = new int[rowLength + 1][rowLength + 1];
        
        dp[0][0] = triangle[0][0];
                
        for(int i = 1; i < rowLength; i++) {
            for(int j = 0; j <= i; j++) {
                // 해당 행의 가장 첫 번째
                if(j == 0) {
                    dp[i][j] = dp[i - 1][j] + triangle[i][j];
                // 해당 행의 가장 마지막
                } else if(i == j) {
                    dp[i][j] = dp[i - 1][j - 1] + triangle[i][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j - 1], dp[i - 1][j]) + triangle[i][j];
                }
            }
        }
        
        for(int i = 0; i <= rowLength; i++) {
            answer = Math.max(dp[rowLength - 1][i], answer);
        }
        
        return answer;
    }
}