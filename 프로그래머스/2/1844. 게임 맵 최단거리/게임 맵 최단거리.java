import java.util.*;

class Solution {
    private static boolean[][] visited;
    private static int row, col;
    private static final int[] dx = {0, 1, 0, -1};
    private static final int[] dy = {1, 0, -1, 0};

    private static class Point {
        private int x;
        private int y;
        private Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static int bfs(int[][] maps) {
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(0, 0));
        visited[0][0] = true;
        int count = 1;
        while(!queue.isEmpty()) {
            int size = queue.size();
            for (int s = 0; s < size; s++) {
                Point point = queue.poll();
                int x = point.x;
                int y = point.y;

                if(x == row-1 && y == col-1) {
                    return count;
                }
                for(int i = 0; i < 4; i++) {
                    int nx = x + dx[i];
                    int ny = y + dy[i];
                    if(0 <= nx && nx < row && 0 <= ny && ny < col) {
                        if(!visited[nx][ny] && maps[nx][ny] == 1) {
                            visited[nx][ny] = true;
                            queue.offer(new Point(nx, ny));
                        }
                    }
                }
            }
            count++;
        }
        return -1;
    }

    public int solution(int[][] maps) {
        row = maps.length;
        col = maps[0].length;
        visited = new boolean[row][col];
        return bfs(maps);
    }
}
