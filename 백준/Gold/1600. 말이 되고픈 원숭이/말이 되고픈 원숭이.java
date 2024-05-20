import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static int[][] dHorse = {{-2, 1}, {-1, 2}, {1, 2}, {2, 1}, {2, -1}, {1, -2}, {-1, -2}, {-2, -1}};
    private static int[][] dMove = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    private static int w, h, k;
    private static boolean[][][] visited;
    private static int[][] map;

    private static class Node {
        private final int x;
        private final int y;
        private final int movement;
        private final int horseMoveCnt;

        private Node(final int x, final int y, final int movement, final int horseMoveCnt) {
            this.x = x;
            this.y = y;
            this.movement = movement;
            this.horseMoveCnt = horseMoveCnt;
        }
    }

    private static boolean isInside(int x, int y) {
        return 0 <= x && x < h && 0 <= y && y < w;
    }

    private static int bfs() {
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(0, 0, 0, 0));
        visited[0][0][0] = true;

        while (!queue.isEmpty()) {
            Node currentNode = queue.poll();
            int x = currentNode.x;
            int y = currentNode.y;
            int currentMoveMent = currentNode.movement;
            int currentHorseMoveCnt = currentNode.horseMoveCnt;

            if (x == h - 1 && y == w - 1) {
                return currentMoveMent;
            }
            
            // 그냥 움직일 때(원숭이 - 북, 동, 남, 서)
            for (int i = 0; i < 4; i++) {
                int nx = x + dMove[i][0];
                int ny = y + dMove[i][1];

                if (!isInside(nx, ny)) continue;

                if (visited[nx][ny][currentHorseMoveCnt] || map[nx][ny] == 1) continue;
                queue.offer(new Node(nx, ny, currentMoveMent + 1, currentHorseMoveCnt));
                visited[nx][ny][currentHorseMoveCnt] = true;
            }
            
            // 말 처럼 움직일 때 (k번만 가능)
            for (int i = 0; i < 8; i++) {
                int nx = x + dHorse[i][0];
                int ny = y + dHorse[i][1];

                if (currentHorseMoveCnt == k) continue;
                if (!isInside(nx, ny)) continue;

                if (visited[nx][ny][currentHorseMoveCnt + 1] || map[nx][ny] == 1) continue;
                queue.offer(new Node(nx, ny, currentMoveMent + 1, currentHorseMoveCnt + 1));
                visited[nx][ny][currentHorseMoveCnt + 1] = true;
            }
        }

        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        k = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        w = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());

        visited = new boolean[h][w][k + 1];
        map = new int[h][w];

        for (int i = 0; i < h; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < w; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(bfs());
    }
}
