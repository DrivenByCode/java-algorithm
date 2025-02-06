import java.util.*;

class Solution {
    private static final int[] dx = {-1, 0, 1, 0};
    private static final int[] dy = {0, 1, 0, -1};
    private static boolean[][] visited;
    private static int r, c;

    public int[] solution(String[] maps) {
        List<Integer> landSizes = new ArrayList<>();
        r = maps.length;
        c = maps[0].length();
        visited = new boolean[r][c];

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (Character.isDigit(maps[i].charAt(j)) && !visited[i][j]) {
                    int size = getLandSize(maps, i, j);
                    landSizes.add(size);
                }
            }
        }

        if (landSizes.isEmpty()) {
            return new int[]{-1};
        }

        Collections.sort(landSizes);
        return landSizes.stream().mapToInt(i -> i).toArray();
    }

    private static int getLandSize(String[] maps, int x, int y) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x, y});
        visited[x][y] = true; // 방문 체크

        int sum = maps[x].charAt(y) - '0';

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int cx = curr[0], cy = curr[1];

            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];

                if (isInMaps(nx, ny) && !visited[nx][ny] && Character.isDigit(maps[nx].charAt(ny))) {
                    queue.offer(new int[]{nx, ny});
                    visited[nx][ny] = true;
                    sum += maps[nx].charAt(ny) - '0';
                }
            }
        }

        return sum;
    }

    private static boolean isInMaps(int x, int y) {
        return 0 <= x && x < r && 0 <= y && y < c;
    }
}
