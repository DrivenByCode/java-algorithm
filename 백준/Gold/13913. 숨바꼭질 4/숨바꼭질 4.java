import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int n, k;

    private static int getSteps(int[] parent, int target) {
        int steps = 0;
        while (parent[target] != -1) {
            target = parent[target];
            steps++;
        }
        return steps;
    }

    private static List<Integer> reconstructPath(int[] parent, int target) {
        List<Integer> path = new ArrayList<>();
        while (target != -1) {
            path.add(target);
            target = parent[target];
        }
        Collections.reverse(path); // 경로를 뒤집어 올바른 순서로 정렬
        return path;
    }

    private static void bfs() {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[100_001];
        int[] parent = new int[100_001]; // 부모 노드 정보
        Arrays.fill(parent, -1); // 초기값은 -1로 설정

        queue.add(n);
        visited[n] = true;

        while (!queue.isEmpty()) {
            int pos = queue.poll();

            // 목적지에 도달한 경우
            if (pos == k) {
                // 이동 횟수 출력
                System.out.println(getSteps(parent, k));

                // 경로 복원 및 출력
                List<Integer> path = reconstructPath(parent, k);
                for (int step : path) {
                    System.out.print(step + " ");
                }
                System.out.println();
                return; // 최단 경로이므로 바로 종료
            }

            // 다음 가능한 위치 탐색
            int[] nextPositions = {pos * 2, pos + 1, pos - 1};
            for (int next : nextPositions) {
                if (next >= 0 && next <= 100_000 && !visited[next]) {
                    visited[next] = true;
                    parent[next] = pos; // 부모 정보를 저장
                    queue.add(next);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs = br.readLine().split(" ");

        n = Integer.parseInt(inputs[0]);
        k = Integer.parseInt(inputs[1]);

        bfs();
    }
}
