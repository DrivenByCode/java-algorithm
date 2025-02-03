import java.util.*;

class Solution {
    public int solution(int[] mats, String[][] park) {
        int answer = -1;
        int rows = park.length;
        int cols = park[0].length;
        
        int[][] dp = new int[rows][cols]; 
        int maxSquareSize = 0; 
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (park[i][j].equals("-1")) { 
                    if (i == 0 || j == 0) {
                        dp[i][j] = 1;
                    } else {
                        // 가로, 세로, 대각 기준 가장 작은 크기 + 1
                        dp[i][j] = Math.min(dp[i - 1][j], Math.min(dp[i][j - 1], dp[i - 1][j - 1])) + 1;
                    }
                    maxSquareSize = Math.max(maxSquareSize, dp[i][j]);
                }
            }
        }
        
        Arrays.sort(mats);
        
        // 가능한 최대 돗자리 크기 찾기
        for (int mat : mats) {
            if (mat <= maxSquareSize) {
                answer = mat;
            }
        }
        
        return answer;
    }
}
