import java.util.Arrays;
class Solution {
    static final int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    static final int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};
    static boolean visited[][];
    public int solution(int[][] board) {
        int answer = 0;
        int n = board[0].length;
        visited = new boolean[n][n];
        
        Loop:
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(board[i][j] == 1) {
                    visited[i][j] = true;
                    for(int k = 0; k < 8; k++) {
                        int nx = i + dx[k];
                        int ny = j + dy[k];
                            if(0 <= nx && nx < n && 0 <= ny && ny < n) {
                                if(!visited[nx][ny]) {
                                    visited[nx][ny] = true;
                                }
                            }
                    }
                }
            }
        }
        
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(!visited[i][j]) answer++;
            }
        }
        return answer;
    }
}