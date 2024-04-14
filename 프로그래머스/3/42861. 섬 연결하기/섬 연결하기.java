import java.util.*;
class Solution {
    private static int[] parent;
    private static int[] rank;
    
    private static class Edge implements Comparable<Edge> {
        private final int v1;
        private final int v2;
        private final int cost;
        
        private Edge(final int v1, final int v2, final int cost) {
            this.v1 = v1;
            this.v2 = v2;
            this.cost = cost;
        }
        
        @Override
        public int compareTo(Edge other) {
            return this.cost - other.cost;
        }
    }
    
    private static void init(int size) {
        parent = new int[size + 1];
        rank = new int[size + 1];
        
        for(int i = 0; i <= size; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }
    
    private static int find(int x) {
        if(parent[x] == x) return parent[x];
        return parent[x] = find(parent[x]);
    }
    
    private static void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        
        if(rootX == rootY) return;
        
        if(rank[rootX] > rank[rootY]) {
            parent[rootY] = rootX;
        } else {
            parent[rootX] = rootY;
            if(rank[rootX] == rank[rootY]) {
                rank[rootY]++;
            }
        }
    }
    
    public int solution(int n, int[][] costs) {
        int answer = 0;
        
        init(n);
        
        ArrayList<Edge> edges = new ArrayList<>();
        
        for(int[] cost : costs) {
            int v1 = cost[0];
            int v2 = cost[1];
            int val = cost[2];
            edges.add(new Edge(v1, v2, val));
        }
        
        Collections.sort(edges);
        
        int cnt = 0;
        
        for(Edge eg : edges) {
            int fv1 = find(eg.v1);
            int fv2 = find(eg.v2);
            if(fv1 != fv2) {
                answer += eg.cost;
                union(eg.v1, eg.v2);
                cnt++;
            }
            
            if(cnt == n - 1) break;
        }
        
        return answer;
    }
}