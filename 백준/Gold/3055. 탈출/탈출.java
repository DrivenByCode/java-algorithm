import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static int r, c;
    private static char[][] map;
    private static int[][] dist;
    // 12시부터 시계방향 순 => 상 우 하 좌
    private static final int[] dx = {0, 1, 0, -1};
    private static final int[] dy = {1, 0, -1, 0};

    private static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        map = new char[r][c];
        dist = new int[r][c];

        Queue<Point> hedgehog = new LinkedList<>();
        Queue<Point> water = new LinkedList<>();

        for (int i = 0; i < r; i++) {
            String line = br.readLine();
            for (int j = 0; j < c; j++) {
                map[i][j] = line.charAt(j);
                if (map[i][j] == 'S') {
                    hedgehog.offer(new Point(i, j));
                } else if (map[i][j] == '*') {
                    water.offer(new Point(i, j));
                }
            }
        }
        
        while (!hedgehog.isEmpty()) {
            int waterSize = water.size();
            // 처음에 water 큐에 넣었던 원소의 수 만큼만 for문 돎.
            for (int i = 0; i < waterSize; i++) {
                Point w = water.poll();
                for (int j = 0; j < 4; j++) {
                    int nx = w.x + dx[j];
                    int ny = w.y + dy[j];
                    if (0 <= nx && nx < r && 0 <= ny && ny < c && map[nx][ny] == '.') {
                        map[nx][ny] = '*';
                        water.offer(new Point(nx, ny));
                    }
                }
            }

            int hedgehogSize = hedgehog.size();
            // 처음에 hedgehog 큐에 넣었던 좌표(딱 하나) 수 만큼만 for문 돎.
            for (int i = 0; i < hedgehogSize; i++) {
                Point h = hedgehog.poll();
                for (int j = 0; j < 4; j++) {
                    int nx = h.x + dx[j];
                    int ny = h.y + dy[j];
                    if (0 <= nx && nx < r && 0 <= ny && ny < c) {
                        // dist가 갱신 되지 않았다면 방문하지 않은 것으로 visited 배열 역할도 함.
                        if (map[nx][ny] == '.' && dist[nx][ny] == 0) {
                            dist[nx][ny] = dist[h.x][h.y] + 1;
                            hedgehog.offer(new Point(nx, ny));
                        } else if (map[nx][ny] == 'D') {
                            // 다음 이동지가 비버의 굴이면 현재 거리 + 1 한 것을 출력.
                            System.out.println(dist[h.x][h.y] + 1);
                            return;
                        }
                    }
                }
            }
            // 이렇게 water 큐, hedgehog 큐를 돌고 나면 한 타임이 지나간 것.
        }

        System.out.println("KAKTUS");
    }
}
