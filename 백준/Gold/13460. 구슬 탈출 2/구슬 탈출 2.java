import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static int n, m;
    private static int[] dx = {0, 1, 0, -1}; // 상 우 하 좌
    private static int[] dy = {1, 0, -1, 0}; // 상 우 하 좌

    private static class Point {
        private final int rx;
        private final int ry;
        private final int bx;
        private final int by;
        private int times;

        private Point(int rx, int ry, int bx, int by, int times) {
            this.rx = rx;
            this.ry = ry;
            this.bx = bx;
            this.by = by;
            this.times = times;
        }
    }

    private static char[][] board;
    private static Queue<Point> queue = new LinkedList<>();
    private static boolean[][][][] visited;


    private static boolean isInBoard(int x, int y) {
        return (1 <= x && x <= n && 1 <= y && y <= m);
    }

    private static int bfs() {
        while (!queue.isEmpty()) {
            Point current = queue.poll();
            if (current.times > 10) return -1;

            // 빨간선만 떨어진 상태라면 움직임 수 리턴
            if (board[current.rx][current.ry] == 'O' && board[current.bx][current.by] != 'O') {
                return current.times;
            }

            for (int i = 0; i < 4; i++) {
                int nrx = current.rx, nry = current.ry, nbx = current.bx, nby = current.by;

                // 다음 위치에 보드 안에 있고, 현재 빨간공 위치가 구멍(0)이 아니고 다음 위치가 #(벽 또는 장애물)이 아닐 때
                while (isInBoard(nrx + dx[i], nry + dy[i]) && board[nrx + dx[i]][nry + dy[i]] != '#' && board[nrx][nry] != 'O') {
                    nrx += dx[i];
                    nry += dy[i];
                }

                // 다음 위치에 보드 안에 있고, 현재 파란공 위치가 구멍(0)이 아니고 다음 위치가 #(벽 또는 장애물)이 아닐 때
                while (isInBoard(nbx + dx[i], nby + dy[i]) && board[nbx + dx[i]][nby + dy[i]] != '#' && board[nbx][nby] != 'O') {
                    nbx += dx[i];
                    nby += dy[i];
                }

                if (nrx == nbx && nry == nby) {
                    if (board[nrx][nry] != 'O') {
                        int redDistance = Math.abs(nrx - current.rx) + Math.abs(nry - current.ry);
                        int blueDistance = Math.abs(nbx - current.bx) + Math.abs(nby - current.by);
                        // 빨간공이 더 많이 움직였다면 빨간공을 하나 이전으로 되돌림.
                        if (redDistance > blueDistance) {
                            nrx -= dx[i];
                            nry -= dy[i];
                        } else {
                            nbx -= dx[i];
                            nby -= dy[i];
                        }
                    }
                }

                if (!visited[nrx][nry][nbx][nby]) {
                    visited[nrx][nry][nbx][nby] = true;
                    queue.add(new Point(nrx, nry, nbx, nby, current.times + 1));
                }
            }
        }
        return -1;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken()); // 가로
        m = Integer.parseInt(st.nextToken()); // 세로

        board = new char[n + 1][m + 1];
        visited = new boolean[n + 1][m + 1][n + 1][m + 1];

        int rx = 0, ry = 0, bx = 0, by = 0;
        for (int i = 1; i <= n; i++) {
            String input = br.readLine();
            for (int j = 1; j <= m; j++) {
                board[i][j] = input.charAt(j - 1);
                if (board[i][j] == 'R') {
                    rx = i;
                    ry = j;
                } else if (board[i][j] == 'B') {
                    bx = i;
                    by = j;
                }
            }
        }

        queue.add(new Point(rx, ry, bx, by, 0));
        System.out.println(bfs());
    }
}