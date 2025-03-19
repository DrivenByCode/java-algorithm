import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static final int[] dx = {-2, -1, 1, 2, 2, 1, -1, -2}; // 나이트가 이동할 수 있는 경로
    private static final int[] dy = {-1, -2, -2, -1, 1, 2, 2, 1};
    private static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());
        StringTokenizer st;


        for (int i = 0; i < t; i++) {
            int l = Integer.parseInt(br.readLine());

            visited = new boolean[l][l];

            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());

            int c = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            System.out.println(bfs(new Point(a, b), new Point(c, d), l));
        }

    }

    private static class Point {
        private final int x;
        private final int y;

        private Point(final int x, final int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static int bfs(Point start, Point end, int l) {
        Queue<Point> queue = new LinkedList<>();
        queue.offer(start);

        visited[start.x][start.y] = true;
        int moves = 0;

        if (start.x == end.x && start.y == end.y) {
            return moves;
        }

        while (!queue.isEmpty()) {
            int size = queue.size(); // 현재 레벨의 노드 개수
            for (int i = 0; i < size; i++) {
                Point curr = queue.poll();
                for (int j = 0; j < 8; j++) {
                    Point next = new Point(curr.x + dx[j], curr.y + dy[j]);
                    if (0 <= next.x && next.x < l && 0 <= next.y && next.y < l && !visited[next.x][next.y]) {
                        visited[next.x][next.y] = true;
                        queue.offer(next);


                        if (next.x == end.x && next.y == end.y) {
                            return moves + 1; // 해당 레벨이 끝나기 전 반환하는 것이기 때문에 +1을 해줌
                        }

                    }
                }
            }
            moves++;
        }

        return moves;
    }
}
