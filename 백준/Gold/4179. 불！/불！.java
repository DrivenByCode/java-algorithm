import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static char[][] map;
    private static final int[] dx = {0, 1, 0, -1};
    private static final int[] dy = {1, 0, -1, 0};
    private static final String FAIL = "IMPOSSIBLE";
    private static boolean[][] visited;
    private static int r, c;

    private static class Point {
        private int x;
        private int y;
        private final int movement;
        private final boolean isFire;

        private Point(final int x, final int y, final int movement, final boolean isFire) {
            this.x = x;
            this.y = y;
            this.movement = movement;
            this.isFire = isFire;
        }
    }

    private static boolean isInside(int x, int y) {
        return 0 <= x && x < r && 0 <= y && y < c;
    }

    private static int bfs(Queue<Point> queue) {
        while (!queue.isEmpty()) {
            Point currentNode = queue.poll();
            int x = currentNode.x;
            int y = currentNode.y;
            int movement = currentNode.movement;
            boolean isFire = currentNode.isFire;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (!isInside(nx, ny)) {
                    if (!isFire) {
                        return movement + 1;
                    } else {
                        continue;
                    }
                }

                if (map[nx][ny] == '#' || map[nx][ny] == 'F') continue;
                if (visited[nx][ny]) continue;

                if (isFire) {
                    map[nx][ny] = 'F';
                    queue.offer(new Point(nx, ny, movement + 1, true));
                } else {
                    if (map[nx][ny] == '.') {
                        queue.offer(new Point(nx, ny, movement + 1, false));
                    }
                }
                visited[nx][ny] = true;
            }
        }

        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        map = new char[r][c];
        visited = new boolean[r][c];
        Queue<Point> queue = new LinkedList<>();
        Point start = null;

        for (int i = 0; i < r; i++) {
            String input = br.readLine();
            for (int j = 0; j < c; j++) {
                map[i][j] = input.charAt(j);
                if (map[i][j] == 'J') {
                    start = new Point(i, j, 0, false);
                }
                if (map[i][j] == 'F') {
                    queue.offer(new Point(i, j, 0, true));
                }
            }
        }

        if (start != null) {
            queue.offer(start);
        }

        int answer = bfs(queue);
        System.out.println(answer == -1 ? FAIL : answer);
    }
}
