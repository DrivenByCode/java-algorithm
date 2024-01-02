import java.util.*;

class Solution {
    private static boolean[] visited;

    private static int bfs(int[][] computers, int startNode) {
        int n = computers.length;
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(startNode);
        visited[startNode] = true;

        while (!queue.isEmpty()) {
            int currentNode = queue.poll();
            
            // for(int nextNode : visited[currentNode]) 이 형태는 연결 여부만 나타내는 것(연결됨:1, 연결안됨:0) 따라서 사용 X
            for (int nextNode = 0; nextNode < n; nextNode++) {
                if (computers[currentNode][nextNode] == 1 && !visited[nextNode]) {
                    visited[nextNode] = true;
                    queue.offer(nextNode);
                }
            }
        }

        return 1;
    }

    public int solution(int n, int[][] computers) {
        int answer = 0;
        visited = new boolean[n];

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                answer += bfs(computers, i);
            }
        }

        return answer;
    }
}
