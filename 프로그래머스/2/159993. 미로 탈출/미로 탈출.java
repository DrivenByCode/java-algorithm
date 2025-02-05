import java.util.*;

class Solution {
    private static final int[] dx = {-1, 0, 1, 0}; // 좌, 상, 우, 하
    private static final int[] dy = {0, 1, 0, -1};
    private static int w, h;

    public int solution(String[] maps) {
        h = maps.length;  // 세로 (행 개수)
        w = maps[0].length();  // 가로 (열 개수)

        Point start = null;
        Point lever = null;
        Point exit = null;

        // 시작점(S), 레버(L), 출구(E) 위치 찾기
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (maps[i].charAt(j) == 'S') start = new Point(i, j, 0);
                if (maps[i].charAt(j) == 'L') lever = new Point(i, j, 0);
                if (maps[i].charAt(j) == 'E') exit = new Point(i, j, 0);
            }
        }

        // S → L 최단 거리 탐색
        int toLever = bfs(maps, start, lever);
        if (toLever == -1) return -1;

        // L → E 최단 거리 탐색
        int toExit = bfs(maps, lever, exit);
        if (toExit == -1) return -1;

        return toLever + toExit;
    }

    private int bfs(String[] maps, Point start, Point target) {
        boolean[][] visited = new boolean[h][w];
        Queue<Point> queue = new LinkedList<>();
        
        queue.offer(start);
        visited[start.x][start.y] = true;

        while (!queue.isEmpty()) {
            Point current = queue.poll();

            if (current.x == target.x && current.y == target.y) {
                return current.cnt;
            }

            for (int i = 0; i < 4; i++) {
                int nx = current.x + dx[i];
                int ny = current.y + dy[i];

                if (isInMaps(nx, ny) && !visited[nx][ny] && maps[nx].charAt(ny) != 'X') {
                    queue.offer(new Point(nx, ny, current.cnt + 1));
                    visited[nx][ny] = true;
                }
            }
        }
        return -1; // 목표까지 도달 불가능한 경우
    }
    
    private boolean isInMaps(int x, int y) {
        return (0 <= x && x < h && 0 <= y && y < w);
    }

    private static class Point {
        private int x, y, cnt;
        Point(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }
}
