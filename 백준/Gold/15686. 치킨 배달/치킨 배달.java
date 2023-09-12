import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    // 크기가 n*n인 도시에서, 최소인 "도시의 치킨거리"를 구하기.
    // 도시의 치킨 거리는 모든 집의 치킨거리 합임.
    // m은 도시의 치킨 집 중에서 최대 m개의 치킨 집을 고를 수 있고, 나머지는 폐업
    static int n, m;
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

    // 두 위치 사이의 맨해튼 거리를 계산하는 함수
    private static int getDistance(Location l1, Location l2) {
        return Math.abs(l1.x - l2.x) + Math.abs(l1.y - l2.y);
    }

    private static void getMinDistance(ArrayList<Location> homes, ArrayList<Location> chickens, int level, int start, int[] selectedNumbers) {
        if (level == m) {
            int totalDistance = 0;
            for (Location home : homes) {
                int minDistance = Integer.MAX_VALUE;
                for (int selectedNum : selectedNumbers) {
                    minDistance = Math.min(minDistance, getDistance(home, chickens.get(selectedNum)));
                }
                totalDistance += minDistance;
                if (totalDistance >= answer) return;
            }
            answer = totalDistance;
            return;
        }
        for (int i = start; i < chickens.size(); i++) {
            selectedNumbers[level] = i;
            getMinDistance(homes, chickens, level + 1, i + 1, selectedNumbers);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

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

        getMinDistance(homes, chickens, 0, 0, selectedNumbers);

        System.out.println(answer);
    }
}
