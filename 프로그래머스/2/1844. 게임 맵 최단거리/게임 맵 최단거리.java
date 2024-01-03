import java.util.*;
class Solution {
    // 동서남북
    private static final int[] dx = {1, -1, 0, 0};
    private static final int[] dy = {0, 0, -1, 1};
    
    private static boolean[][] visited;
    private static class Coord {
        private final int x;
        private final int y;
        private Coord(final int x, final int y) {
            this.x = x;
            this.y = y;
        }
    }
    private static int bfs(int[][] maps, int row, int col) {
        int count = 0;
        Queue<Coord> coords = new LinkedList<>();
        coords.offer(new Coord(0,0));
        visited[0][0] = true;
        while(!coords.isEmpty()) {
            int qsize = coords.size();
            for(int size = 0; size < qsize; size++) {
                Coord currentNode = coords.poll();
                if(currentNode.x == row - 1 && currentNode.y == col - 1) {
                    return count + 1;
                }
                for(int movement = 0; movement < 4; movement++) {
                    int nx = currentNode.x + dx[movement];
                    int ny = currentNode.y + dy[movement];
                    if(0 <= nx && nx < row && 0 <= ny && ny < col && !visited[nx][ny] && maps[nx][ny] == 1) {
                        coords.offer(new Coord(nx, ny));
                        visited[nx][ny] = true;
                    }
                }
            }
            count++;
        }
        
        return -1;
    }
    public int solution(int[][] maps) {
        int row = maps.length;
        int col = maps[0].length;
        
        visited = new boolean[row][col];
        
        int answer = bfs(maps, row, col);
        return answer;
    }
}