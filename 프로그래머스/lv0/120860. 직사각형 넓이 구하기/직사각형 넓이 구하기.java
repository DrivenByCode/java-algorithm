class Solution {
    public int solution(int[][] dots) {
        int width = 0;
        int height = 0;
        for(int i = 0; i < 3; i++) {
            width = Math.max(width, Math.abs(dots[i][0] - dots[i + 1][0]));
            height = Math.max(height, Math.abs(dots[i][1] - dots[i + 1][1]));
        }
        int answer = width * height;
        return answer;
    }
}