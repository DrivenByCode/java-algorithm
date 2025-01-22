// 시뮬레이션
import java.util.*;
class Solution {
    public int solution(int[][] points, int[][] routes) {
        // 1. 모든 로봇의 "초 단위 위치 경로"를 저장할 리스트
        // - allPaths.get(i): i번째 로봇의 0초부터 마지막 포인트 도착까지 매초 (r, c)
        // 위치를 담은 List<int[]>
        int x = routes.length;  // 로봇 수 (routes의 행 개수)
        List<List<int[]>> allPaths = new ArrayList<>();

        // 2. 각 로봇별로 실제 이동 경로를 구한다.
        for (int i = 0; i < x; i++) {
            // 현재 로봇이 순서대로 방문할 포인트 번호들
            int[] route = routes[i];
            // i번째 로봇의 "초 단위 위치 경로"를 담을 리스트
            List<int[]> robotPath = new ArrayList<>();

            // (1) 첫 포인트 좌표를 시작 위치로 설정
            //  - 포인트 번호 route[0] → 실제 좌표는 points[route[0]-1]
            int[] startPos = points[route[0] - 1]; 
            // 현재 위치를 복제
            int[] currentPos = new int[]{ startPos[0], startPos[1] };
            
            // t = 0 시점에 로봇 위치 등록
            robotPath.add(new int[]{ currentPos[0], currentPos[1] });

            // (2) route의 나머지 포인트들을 순차적으로 이동
            for (int j = 0; j < route.length - 1; j++) {
                // 다음에 이동할 포인트 좌표
                int[] nextPos = points[route[j + 1] - 1];

                // (2-1) r 좌표를 먼저 이동 (문제에서 r → c 순서로 이동한다고 명시)
                while (currentPos[0] < nextPos[0]) {
                    currentPos[0]++;
                    robotPath.add(new int[]{ currentPos[0], currentPos[1] });
                }
                while (currentPos[0] > nextPos[0]) {
                    currentPos[0]--;
                    robotPath.add(new int[]{ currentPos[0], currentPos[1] });
                }

                // (2-2) c 좌표를 나중에 이동
                while (currentPos[1] < nextPos[1]) {
                    currentPos[1]++;
                    robotPath.add(new int[]{ currentPos[0], currentPos[1] });
                }
                while (currentPos[1] > nextPos[1]) {
                    currentPos[1]--;
                    robotPath.add(new int[]{ currentPos[0], currentPos[1] });
                }
            }

            // i번째 로봇의 전체 이동 경로를 저장
            allPaths.add(robotPath);
        }

        // 3. 각 로봇 경로의 최대 길이를 구한다. (가장 오래 이동하는 로봇 기준)
        int maxLen = 0;
        for (List<int[]> path : allPaths) {
            maxLen = Math.max(maxLen, path.size());
        }

        // 4. 0초부터 maxLen - 1초까지 시뮬레이션하며 충돌(위험 상황) 횟수를 센다.
        int dangerCount = 0;

        for (int t = 0; t < maxLen; t++) {
            // 같은 시각 t에 각 좌표에 몇 대의 로봇이 있는지 센다.
            Map<String, Integer> positionCount = new HashMap<>();

            // 로봇별 현재 위치 확인
            for (int i = 0; i < x; i++) {
                List<int[]> path = allPaths.get(i);
                // 아직 이동 경로가 남아 있다면(즉, t초에 위치가 존재)
                if (t < path.size()) {
                    int[] pos = path.get(t);
                    String key = pos[0] + "," + pos[1]; // "(r,c)"
                    positionCount.put(key, positionCount.getOrDefault(key, 0) + 1);
                }
            }

            // 시각 t에, 2대 이상 로봇이 모인 좌표가 몇 개인지 확인
            for (int count : positionCount.values()) {
                if (count >= 2) {
                    // 같은 좌표에 2대 이상 → 위험 상황 1회
                    dangerCount++;
                }
            }
        }

        // 5. 위험 상황 횟수를 반환
        return dangerCount;
    }
}
