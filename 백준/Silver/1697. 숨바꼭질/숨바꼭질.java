import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static final int MAX = 100000;
    private static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        visited = new boolean[MAX + 1];

        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        int answer = bfs(start, end);

        System.out.println(answer);
    }

    private static int bfs(int start, int end) {
        Queue<Integer> queue = new LinkedList<>();
        int moves = 0;

        if (start == end) {
            return moves;
        }
        queue.offer(start);

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int current = queue.poll();

                if (current == end) {
                    return moves;
                }

                int[] nextMoves = {current * 2, current + 1, current - 1};
                for (int next : nextMoves) {
                    if (0 <= next && next <= MAX && !visited[next]) {
                        visited[next] = true;
                        queue.offer(next);
                    }
                }
            }
            moves++;
        }

        return moves;
    }
}
