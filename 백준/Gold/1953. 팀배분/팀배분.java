import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    private static int[] team;
    private static ArrayList<Integer>[] adjList;

    private static void makeBiPariteGraphByDFS(int currentVertex, int c) {
        // 팀이 정해져있지 않을 때 하나의 팀(A : 1)으로 정함
        team[currentVertex] = c;
        for (int nextVertex : adjList[currentVertex]) {
            if (team[nextVertex] == 0) {
                // A가 아닌 B(B : 2)팀으로 정함
                makeBiPariteGraphByDFS(nextVertex, 3 - c);
            }

        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        StringTokenizer st;

        team = new int[n + 1];

        adjList = new ArrayList[n + 1];

        for (int i = 0; i <= n; i++) {
            adjList[i] = new ArrayList<>();
        }

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            int nums = Integer.parseInt(st.nextToken());
            for (int j = 1; j <= nums; j++) {
                int vertex = Integer.parseInt(st.nextToken());
                adjList[i].add(vertex);
                adjList[vertex].add(i);
            }
        }

        for (int i = 1; i <= n; i++) {
            // 팀이 안정해져있을 경우
            if (team[i] == 0) {
                makeBiPariteGraphByDFS(i, 1);
            }
        }

        int blueTeamCnt = 0;
        ArrayList<Integer> blueList = new ArrayList<>();
        int redTeamCnt = 0;
        ArrayList<Integer> redList = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (team[i] == 1) {
                blueTeamCnt++;
                blueList.add(i);
            } else {
                redTeamCnt++;
                redList.add(i);
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(blueTeamCnt + "\n" + blueList.toString().replaceAll("[\\[\\],]", "") + "\n");
        sb.append(redTeamCnt + "\n" + redList.toString().replaceAll("[\\[\\],]", ""));

        System.out.println(sb);
    }
}
