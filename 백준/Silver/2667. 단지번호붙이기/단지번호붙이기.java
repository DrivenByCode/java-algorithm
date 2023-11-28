import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    private static int[] dx = {0, 1, 0, -1}; // 위에서 부터 시계 방향
    private static int[] dy = {1, 0, -1, 0}; // 위에서 부터 시계 방향
    private static int[][] map;
    private static boolean[][] visited;
    private static int n;

    private static class Point {
        private final int x;
        private final int y;

        private Point(final int x, final int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static boolean isInBound(final int x, final int y) {
        return 0 <= x && x < n && 0 <= y && y < n;
    }

    private static int bfs(final int x, final int y) {
        final Point startPoint = new Point(x, y);
        int count = 1;
        final Queue<Point> queue = new LinkedList<>();
        queue.offer(startPoint);
        while (!queue.isEmpty()) {
            Point currentNode = queue.poll();
            int currentX = currentNode.x;
            int currentY = currentNode.y;
            for (int i = 0; i < 4; i++) {
                int nextX = currentX + dx[i];
                int nextY = currentY + dy[i];
                if (isInBound(nextX, nextY) && !visited[nextX][nextY]) {
                    if (map[nextX][nextY] == 1) {
                        visited[nextX][nextY] = true;
                        count++;
                        queue.offer(new Point(nextX, nextY));
                    }
                }
            }
        }

        return count;
    }

    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        map = new int[n][n];
        visited = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            String[] input = br.readLine().split("");
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(input[j]);
            }
        }

        br.close();

        final ArrayList<Integer> buildingComplex = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == 1 && !visited[i][j]) {
                    visited[i][j] = true;
                    buildingComplex.add(bfs(i, j));
                }
            }
        }

        final StringBuilder sb = new StringBuilder();
        sb.append(buildingComplex.size()).append("\n");

        Collections.sort(buildingComplex);

        for (final int type : buildingComplex) {
            sb.append(type).append("\n");
        }

        System.out.println(sb.toString().trim());
    }
}

