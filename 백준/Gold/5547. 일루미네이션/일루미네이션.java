import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static int[][] oddDir = {{0, -1}, {0, 1}, {1, 0}, {1, 1}, {-1, 1}, {-1, 0}};
    private static int[][] evenDir = {{0, -1}, {0, 1}, {1, 0}, {1, -1}, {-1, -1}, {-1, 0}};
    private static int w;
    private static int h;
    private static int[][] map;
    private static boolean[][] visited;

    private static class Node {
        private final int x;
        private final int y;

        private Node(final int x, final int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static boolean isInside(int x, int y) {
        return x >= 0 && x < h + 2 && y >= 0 && y < w + 2;
    }

    private static boolean isOutside(int x, int y) {
        return !isInside(x, y);
    }

    private static int bfs() {
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(0, 0));
        visited[0][0] = true;
        int count = 0;

        while (!queue.isEmpty()) {
            Node currentNode = queue.poll();

            int x = currentNode.x;
            int y = currentNode.y;
            int nx;
            int ny;

            for (int i = 0; i < 6; i++) {
                if (x % 2 == 1) {
                    nx = x + oddDir[i][0];
                    ny = y + oddDir[i][1];
                } else {
                    nx = x + evenDir[i][0];
                    ny = y + evenDir[i][1];
                }

                if (isOutside(nx, ny)) continue;
                if (visited[nx][ny]) continue;

                if (map[nx][ny] == 1) {
                    count++;
                    continue;
                }

                visited[nx][ny] = true;
                queue.offer(new Node(nx, ny));
            }
        }

        return count;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        w = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());

        map = new int[h + 2][w + 2];
        visited = new boolean[h + 2][w + 2];

        for (int i = 1; i <= h; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= w; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = bfs();

        System.out.println(answer);
    }
}
