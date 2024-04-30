import java.util.*;
class Solution {
    private static List<Integer>[] graph;
    private static boolean[] visited;
    private static int min;
    
    // 간선 다시 추가
    private static int dfs(int v, boolean[] visited) {
        visited[v] = true;
        int cnt = 1;
        
        for(int vertex : graph[v]) {
            if(!visited[vertex]) {
                cnt += dfs(vertex, visited);
            }
        }
        
        return cnt;
    }
    
    public int solution(int n, int[][] wires) {
        min = Integer.MAX_VALUE;
        
        graph = new ArrayList[n + 1];
        
        for(int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        
        for(int[] wire : wires) {
            int v1 = wire[0];
            int v2 = wire[1];
            
            graph[v1].add(v2);
            graph[v2].add(v1);
        }
        
        for(int[] wire : wires) {
            int v1 = wire[0];
            int v2 = wire[1];
            
            visited = new boolean[n + 1];
            
            // 간선 제거
            graph[v1].remove(Integer.valueOf(v2));
            graph[v2].remove(Integer.valueOf(v1));
            
            int cnt = dfs(1, visited);
            
            int diff = Math.abs(cnt - (n - cnt));
            
            // 두 개의 전력망들의 갯수가 비슷해야 하므로 차이가 최소여야 함.
            min = Math.min(diff, min);
            
            // 간선 다시 추가
            graph[v1].add(Integer.valueOf(v2));
            graph[v2].add(Integer.valueOf(v1));
        }
        
        return min;
    }
}