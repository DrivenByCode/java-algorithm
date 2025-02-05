import java.util.*;

class Solution {
    private static final int[] dx = {-1, 0, 1, 0};  // 상, 우, 하, 좌 (row, col)
    private static final int[] dy = {0, 1, 0, -1};
    private static int h, w;
    private static boolean[][] visited;
    
    public int solution(String[] board) {
        h = board.length;          // 행의 개수
        w = board[0].length();     // 열의 개수
        
        visited = new boolean[h][w];
        Point start = null;
        
        Loop:
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (board[i].charAt(j) == 'R') {
                    start = new Point(i, j, 0);
                    break Loop;
                }
            }
        }
        
        return bfs(board, start);
    }
    
    private int bfs(String[] board, Point start) {
        Queue<Point> queue = new LinkedList<>();
        queue.offer(start);
        visited[start.x][start.y] = true;
        
        while (!queue.isEmpty()) {
            Point cur = queue.poll();
            
            // 현재 위치가 목표 'G'이면 이동 횟수 반환
            if (board[cur.x].charAt(cur.y) == 'G') {
                return cur.cnt;
            }
            
            for (int i = 0; i < 4; i++) {
                int nx = cur.x;
                int ny = cur.y;
                
                // 해당 방향으로 계속 이동 (장애물 'D' 또는 보드 경계에 도달할 때까지)
                while (true) {
                    int nextX = nx + dx[i];
                    int nextY = ny + dy[i];
                    
                    if (!isInBoard(nextX, nextY) || board[nextX].charAt(nextY) == 'D') {
                        break;
                    }
                    
                    nx = nextX;
                    ny = nextY;
                }
                
                // 만약 현재 위치에서 한 칸도 이동하지 못했다면 다음 방향으로
                if (nx == cur.x && ny == cur.y) continue;
                
                // 아직 방문하지 않은 위치라면 큐에 추가
                if (!visited[nx][ny]) {
                    visited[nx][ny] = true;
                    queue.offer(new Point(nx, ny, cur.cnt + 1));
                }
            }
        }
        return -1;
    }
    
    private boolean isInBoard(int x, int y) {
        return 0 <= x && x < h && 0 <= y && y < w;
    }
    
    private static class Point {
        private int x, y, cnt;
        private Point(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }
}
