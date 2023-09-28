import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    private static int n, m;
    private static int[] teams;
    private static ArrayList<Integer>[] adjList;

    private static boolean isBiparetteGraph(int currentVertex, int c) {
        // 해당 학생을 1팀으로 하고
        teams[currentVertex] = c;
        // 해당학생과 인접해있는 인접한 학생들을 모두 조사
        for (int nextVertex : adjList[currentVertex]) {
            // 같은 현재 학생과 인접학생이 모두 같은 팀이면 이분 그래프가 아님
            if (teams[nextVertex] == c) {
                return false;
            }
            // 인접 학생이 팀이 없는데, 그 인접 학생도 인접학생의 인접학생과 팀이 같다면 이분 그래프가 아님
            if (teams[nextVertex] == 0 && !isBiparetteGraph(nextVertex, 3 - c)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        // 정점(vertex)의 수
        int n = Integer.parseInt(st.nextToken());
        // 간선(Edge)의 수
        int m = Integer.parseInt(st.nextToken());

        teams = new int[n + 1];

        adjList = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            adjList[i] = new ArrayList<>();
        }

        for (int j = 0; j < m; j++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            adjList[x].add(y);
            adjList[y].add(x);
        }

        for (int k = 1; k <= n; k++) {
            // k학생이 어느 팀인지 안 정해져 있다면
            if (teams[k] == 0) {
                if (!isBiparetteGraph(k, 1)) {
                    System.out.println(0);
                    return;
                }
            }
        }

        System.out.println(1);
    }
}
