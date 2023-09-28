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
        teams[currentVertex] = c;
        for (int nextVertex : adjList[currentVertex]) {
            if (teams[nextVertex] == c) {
                return false;
            }
            if (teams[nextVertex] == 0 && !isBiparetteGraph(nextVertex, 3 - c)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
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