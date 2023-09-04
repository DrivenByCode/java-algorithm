class Solution {
    public int solution(int[][] triangle) {
        int n = triangle.length; // 삼각형의 높이
        int[][] dp = new int[n][n]; // 각 위치까지의 최대 합을 저장하는 2차원 배열

        // 삼각형의 꼭대기 값은 그대로 dp에 저장
        dp[0][0] = triangle[0][0];

        // 삼각형의 왼쪽 끝과 오른쪽 끝 값을 dp에 저장
        for (int i = 1; i < n; i++) {
            dp[i][0] = dp[i-1][0] + triangle[i][0]; // 왼쪽 끝
            dp[i][i] = dp[i-1][i-1] + triangle[i][i]; // 오른쪽 끝
        }

        // 삼각형의 나머지 위치에 대한 최대 합을 계산
        for (int i = 2; i < n; i++) {
            for (int j = 1; j < i; j++) {
                // 현재 위치의 최대 합은 위쪽의 두 위치 중 큰 값과 현재 위치의 값을 더한 것
                dp[i][j] = Math.max(dp[i-1][j-1], dp[i-1][j]) + triangle[i][j];
            }
        }

        // 삼각형의 바닥에서 최대 합을 찾음
        int max = 0;
        for (int i = 0; i < n; i++) {
            max = Math.max(max, dp[n-1][i]);
        }

        return max; // 최대 합 반환
    }
}