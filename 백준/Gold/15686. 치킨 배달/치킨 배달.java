import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    // 최소 치킨 거리 값을 저장할 변수
    static int answer = Integer.MAX_VALUE;

    // 위치 정보를 저장할 클래스
    static class Location {
        private final int x;
        private final int y;

        private Location(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    // 두 위치 사이의 맨해튼 거리를 계산하느 ㄴ함수
    private static int getDistance(Location l1, Location l2) {
        return Math.abs(l1.x - l2.x) + Math.abs(l1.y - l2.y);
    }

    private static void getMinDistance(ArrayList<Location> homes, ArrayList<Location> chickens, int level, int m, int start, int[] selectedNumbers) {
//        System.out.println("getMinDistance(" + level + ", " + m + ")");
        // M개의 치킨집을 선택했을 때의 치킨 거리 계산
        if (level == m) {
            int totalDistance = 0;
            for (Location home : homes) {
                int minDistance = Integer.MAX_VALUE;
                for (int selectNumber : selectedNumbers) {
                    minDistance = Math.min(minDistance, getDistance(home, chickens.get(selectNumber)));
                }
                totalDistance += minDistance;
                // 현재까지 계산한 거리가 이미 찾은 최소 거리보다 크다면 더 이상 계산할 필요가 없음
                if (totalDistance > answer) {
                    return;
                }
            }
            answer = Math.min(totalDistance, answer);
            return;
        }

        // 치킨집을 선택하는 조합을 구하는 부분
        for (int i = start; i < chickens.size(); i++) {
            selectedNumbers[level] = i;
            getMinDistance(homes, chickens, level + 1, m, i + 1, selectedNumbers);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] selectedNumbers = new int[m];

        int[][] map = new int[n][n];

        ArrayList<Location> homes = new ArrayList<>();
        ArrayList<Location> chickens = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) {
                    homes.add(new Location(i, j));
                } else if (map[i][j] == 2) {
                    chickens.add(new Location(i, j));
                }
            }
        }

        getMinDistance(homes, chickens, 0, m, 0, selectedNumbers);

        System.out.println(answer);
    }
}