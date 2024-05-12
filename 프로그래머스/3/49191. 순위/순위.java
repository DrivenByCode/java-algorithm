import java.util.*;

class Solution {
    private static final int INF = 999999;

    private static int floydWarshall(int[][] results, int n) {
        int[][] dist = new int[n][n];

        // 초기 인접 행렬 설정
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(i == j) {
                    dist[i][j] = 0; // 자기 자신으로의 거리는 0
                } else {
                    dist[i][j] = INF; // 그 외는 INF로 초기화
                }
            }
        }

        // 경기 결과를 기반으로 인접 행렬 구성
        for(int[] result : results) {
            int winner = result[0] - 1; // 0부터 시작하도록 인덱스 조정
            int loser = result[1] - 1; // 0부터 시작하도록 인덱스 조정
            dist[winner][loser] = 1; // 승자에서 패자로의 거리를 1로 설정
        }

        // 플로이드-워셜 알고리즘 수행
        for(int k = 0; k < n; k++) {
            for(int i = 0; i < n; i++) {
                for(int j = 0; j < n; j++) {
                    if(dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }

        // 순위를 결정하기 위해 각 선수에 대해 도달 가능한 선수의 개수를 센다.
        int answer = 0;
        for(int i = 0; i < n; i++) {
            int reachablePlayers = 0;
            for(int j = 0; j < n; j++) {
                if(dist[i][j] != INF || dist[j][i] != INF) {
                    reachablePlayers++;
                }
            }
            // 모든 선수와의 경기 결과를 알 수 있다면 순위를 결정할 수 있는 선수이므로 answer 증가.
            if(reachablePlayers == n) {
                answer++;
            }
        }
        return answer;
    }

    public int solution(int n, int[][] results) {
        return floydWarshall(results, n);
    }
}
