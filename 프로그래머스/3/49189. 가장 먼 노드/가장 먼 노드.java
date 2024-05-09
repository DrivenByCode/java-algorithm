import java.util.*;

class Solution {
    private static List<Integer>[] map;
    private static boolean[] visited;
    
    private static class Node {
        private final int vertex;
        private final int depth;
        
        private Node(final int vertex, final int depth) {
            this.vertex = vertex;
            this.depth = depth;
        }
    }
    
    private static int getFarthestNodeCount(int n) {
        Queue<Node> queue = new LinkedList<>();
        int maxDepth = 0;
        int farthestNodeCount = 0;
        
        queue.offer(new Node(1, 0));
        visited[1] = true;
        
        while (!queue.isEmpty()) {
            Node currentNode = queue.poll();
            int currentDepth = currentNode.depth;
            
            if (currentDepth > maxDepth) {
                maxDepth = currentDepth;
                farthestNodeCount = 1; // 가장 먼 노드를 찾을 때마다 개수를 1로 초기화합니다.
            } else if (currentDepth == maxDepth) {
                farthestNodeCount++; // 현재 노드의 깊이가 최대 깊이와 같으면 개수를 증가시킵니다.
            }
            
            for (int nextVertex : map[currentNode.vertex]) {
                if (!visited[nextVertex]) {
                    visited[nextVertex] = true;
                    queue.offer(new Node(nextVertex, currentDepth + 1));
                }
            }
        }
        
        return farthestNodeCount;
    }
    
    public int solution(int n, int[][] edge) {
        int answer = 0;
        
        map = new ArrayList[n + 1];
        visited = new boolean[n + 1];
        
        for (int i = 1; i <= n; i++) {
            map[i] = new ArrayList<>();
        }
        
        for (int[] e : edge) {
            map[e[0]].add(e[1]);
            map[e[1]].add(e[0]);
        }
        
        answer = getFarthestNodeCount(n);

        return answer;
    }
}