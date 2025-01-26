import java.util.*;

class Solution {
    // 상하좌우 방향
    private static final int[] dx = {0, 0, -1, 1};
    private static final int[] dy = {-1, 1, 0, 0};

    public int solution(int[][] land) {
        int n = land.length;
        int m = land[0].length;
        boolean[][] visited = new boolean[n][m];
        List<int[]> oilPockets = new ArrayList<>(); // 석유 덩어리 정보 저장 (크기와 해당 열의 범위)

        // 모든 칸 탐색하며 석유 덩어리 찾기
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (land[i][j] == 1 && !visited[i][j]) {
                    oilPockets.add(findOilBlock(land, visited, i, j));
                }
            }
        }

        // 각 열에서 최대 석유량 계산
        int[] oilInColumns = new int[m];
        for (int[] pocket : oilPockets) {
            int size = pocket[0];
            int minCol = pocket[1];
            int maxCol = pocket[2];

            for (int col = minCol; col <= maxCol; col++) {
                oilInColumns[col] += size;
            }
        }

        // 최대 석유량 계산
        int maxOil = 0;
        for (int oil : oilInColumns) {
            maxOil = Math.max(maxOil, oil);
        }

        return maxOil;
    }

    // BFS를 활용해 석유 덩어리 정보를 찾는 메서드
    private int[] findOilBlock(int[][] land, boolean[][] visited, int startX, int startY) {
        int n = land.length;
        int m = land[0].length;
        int size = 0; // 석유 덩어리 크기
        int minCol = startY, maxCol = startY;

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{startX, startY});
        visited[startX][startY] = true;

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            size++;
            int x = curr[0];
            int y = curr[1];
            minCol = Math.min(minCol, y);
            maxCol = Math.max(maxCol, y);

            // 상하좌우 탐색
            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];

                if (0 <= nx && nx < n && 0 <= ny && ny < m && !visited[nx][ny] && land[nx][ny] == 1) {
                    visited[nx][ny] = true;
                    queue.add(new int[]{nx, ny});
                }
            }
        }

        return new int[]{size, minCol, maxCol}; // 크기와 열 범위 반환
    }
}
