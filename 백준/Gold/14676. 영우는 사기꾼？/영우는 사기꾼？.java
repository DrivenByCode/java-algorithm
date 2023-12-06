import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    private static int n, m, k;
    private static int[] indegree;
    private static ArrayList<Integer>[] orders;
    private static int[] buildingCount;
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    private static String topologicalSort() throws IOException {
        for (int l = 1; l <= k; l++) {
            st = new StringTokenizer(br.readLine());
            int actionType = Integer.parseInt(st.nextToken());
            int buildingType = Integer.parseInt(st.nextToken());

            if (actionType == 1) {
                if (indegree[buildingType] != 0) {
                    return "Lier!";
                }
                if (buildingCount[buildingType] == 0) {
                    for (final int nextVertex : orders[buildingType]) {
                        indegree[nextVertex]--;
                    }
                }
                buildingCount[buildingType]++;
            } else {
                if (buildingCount[buildingType] == 0) {
                    return "Lier!";
                }
                buildingCount[buildingType]--;
                if (buildingCount[buildingType] == 0) {
                    for (final int nextVertex : orders[buildingType]) {
                        indegree[nextVertex]++;
                    }
                }
            }
        }
        return "King-God-Emperor";
    }

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        indegree = new int[n + 1];
        orders = new ArrayList[n + 1];
        buildingCount = new int[n + 1];

        for (int i = 0; i <= n; i++) {
            orders[i] = new ArrayList<>();
        }

        for (int j = 1; j <= m; j++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            orders[x].add(y);
            indegree[y]++;
        }

        System.out.println(topologicalSort());
    }
}
