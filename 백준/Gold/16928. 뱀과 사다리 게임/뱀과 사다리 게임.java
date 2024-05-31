import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static class Position {
        private int position;
        private int count;

        private Position(int position, int count) {
            this.position = position;
            this.count = count;
        }
    }

    private static int bfs(int[] board) {
        Queue<Position> queue = new LinkedList<>();
        boolean[] visited = new boolean[101];
        queue.add(new Position(1, 0));
        visited[1] = true;

        while (!queue.isEmpty()) {
            Position current = queue.poll();
            int position = current.position;
            int count = current.count;

            if (position == 100) {
                return count;
            }

            for (int i = 1; i <= 6; i++) {
                int nextPosition = position + i;

                if (nextPosition > 100) continue;

                if (board[nextPosition] != -1) {
                    nextPosition = board[nextPosition];
                }

                if (!visited[nextPosition]) {
                    visited[nextPosition] = true;
                    queue.add(new Position(nextPosition, count + 1));
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] board = new int[101];
        Arrays.fill(board, -1);

        for (int i = 0; i < n + m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            board[u] = v;
        }

        System.out.println(bfs(board));
    }
}