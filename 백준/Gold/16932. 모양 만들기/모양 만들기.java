import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    private static int[][] map;
    private static boolean[][] visited;
    private static final int[] dx = {0, 1, 0, -1};
    private static final int[] dy = {1, 0, -1, 0};
    private static int n, m;
    private static int answer = 0;

    // 영역의 크기를 반환하는 DFS 함수
    private static int dfs(int x, int y, int groupNum) {
        visited[x][y] = true;
        map[x][y] = groupNum;
        int size = 1;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (0 <= nx && nx < n && 0 <= ny && ny < m) {
                if (!visited[nx][ny]) {
                    if (map[nx][ny] == 1) {
                        size += dfs(nx, ny, groupNum);
                    }
                }
            }
        }

        return size;
    }

    // 인접한 영역의 크기를 계산하는 함수
    private static void calculateMaxArea(int[] groupSize) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 0) {
                    Set<Integer> adjacentGroups = new HashSet<>();
                    for (int k = 0; k < 4; k++) {
                        int nx = i + dx[k];
                        int ny = j + dy[k];

                        if (0 <= nx && nx < n && 0 <= ny && ny < m) {
                            if (map[nx][ny] > 1) {
                                adjacentGroups.add(map[nx][ny]);
                            }
                        }
                    }

                    int newArea = 1; // 현재 0을 1로 바꾸는 크기 1
                    for (int groupNum : adjacentGroups) {
                        newArea += groupSize[groupNum];
                    }

                    answer = Math.max(answer, newArea);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int groupNum = 2; // 그룹 번호 시작 (0과 1은 이미 사용)
        int[] groupSize = new int[n * m + 2]; // 그룹 번호별 크기 저장

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 1) {
                    if (!visited[i][j]) {
                        int size = dfs(i, j, groupNum);
                        groupSize[groupNum] = size;
                        groupNum++;
                        answer = Math.max(answer, size); // 초기 최대 영역 크기 설정
                    }
                }
            }
        }

        calculateMaxArea(groupSize);

        System.out.println(answer);
    }
}