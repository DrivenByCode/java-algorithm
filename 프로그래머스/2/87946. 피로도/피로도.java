import java.util.*;
class Solution {
    private static int[][] dungeons;
    private static int n;
    private static int answer;
    private static boolean[] visited;
    private static void dfs(int level, int k, int cnt) {
        if(level == n) {
            answer = Math.max(cnt, answer);
        }
        
        for(int i = 0; i < n; i++) {
            if(!visited[i]) {
                visited[i] = true;
                if(k >= dungeons[i][0]) {
                    dfs(level + 1, k - dungeons[i][1], cnt + 1);
                } else {
                    dfs(level + 1, k, cnt);
                }
                visited[i] = false;
            }
        }
    }
    
    public int solution(int k, int[][] dungeons) {        
        this.dungeons = dungeons;
        
        n = dungeons.length;
        
        visited = new boolean[n];
        
        dfs(0, k, 0);
        
        return answer;
    }
}