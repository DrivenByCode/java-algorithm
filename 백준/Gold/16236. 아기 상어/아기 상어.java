import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    private static int[][] space;
    private static int n;
    private static final int[] dx = {-1, 0, 0, 1}; // 상, 좌, 우, 하 순서로 변경
    private static final int[] dy = {0, -1, 1, 0};
    private static Point shark; // 아기 상어의 위치를 저장하는 변수

    private static class Point implements Comparable<Point> {
        int x, y, dist; // 거리(dist) 필드 추가

        Point(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }

        @Override
        public int compareTo(Point other) {
            if (this.dist != other.dist) {
                return this.dist - other.dist;
            } else if (this.x != other.x) {
                return this.x - other.x;
            } else {
                return this.y - other.y;
            }
        }
    }

    private static boolean isWithinBound(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < n;
    }

    private static int bfs() {
        int sharkSize = 2, ateFishes = 0, time = 0;
        Point target;
        do {
            boolean[][] visited = new boolean[n][n]; // 방문 배열 초기화
            PriorityQueue<Point> queue = new PriorityQueue<>();
            queue.offer(new Point(shark.x, shark.y, 0));
            visited[shark.x][shark.y] = true;
            target = null; // 가장 가까운 물고기를 저장할 변수

            while (!queue.isEmpty()) {
                Point current = queue.poll();

                // 먹을 수 있는 물고기를 찾았을 때
                if (space[current.x][current.y] > 0 && space[current.x][current.y] < sharkSize) {
                    target = current;
                    break; // 가장 가까운 물고기를 찾았으므로 반복문 종료
                }

                for (int i = 0; i < 4; i++) {
                    int nx = current.x + dx[i];
                    int ny = current.y + dy[i];

                    if (isWithinBound(nx, ny) && !visited[nx][ny] && space[nx][ny] <= sharkSize) {
                        visited[nx][ny] = true;
                        queue.offer(new Point(nx, ny, current.dist + 1));
                    }
                }
            }

            if (target != null) {
                time += target.dist; // 이동 시간 추가
                ateFishes++;
                space[target.x][target.y] = 0; // 물고기 먹기
                if (ateFishes == sharkSize) { // 크기 증가
                    sharkSize++;
                    ateFishes = 0;
                }
                shark = new Point(target.x, target.y, 0); // 아기 상어 위치 업데이트
            }
        } while (target != null);

        return time; // 더 이상 먹을 물고기가 없을 때
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st;

        space = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                space[i][j] = Integer.parseInt(st.nextToken());
                if (space[i][j] == 9) {
                    shark = new Point(i, j, 0); // 아기 상어 위치 초기화
                    space[i][j] = 0;
                }
            }
        }

        System.out.println(bfs());
    }
}
