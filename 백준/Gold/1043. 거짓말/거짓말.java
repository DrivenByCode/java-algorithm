import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    private static int[] parent;
    private static boolean[] truth;

    public static int find(int x) {
        if (x == parent[x]) {
            return x;
        }
        return parent[x] = find(parent[x]);
    }

    public static void union(int a, int b) {
        int findX = find(a);
        int findY = find(b);

        if (findX != findY) {
            parent[findY] = findX;
            if (truth[findX] || truth[findY]) {
                truth[findX] = truth[findY] = true;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        parent = new int[n + 1];
        truth = new boolean[n + 1];

        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }

        st = new StringTokenizer(br.readLine());
        int knownCount = Integer.parseInt(st.nextToken());
        for (int i = 0; i < knownCount; i++) {
            int knownPerson = Integer.parseInt(st.nextToken());
            truth[knownPerson] = true;
        }

        ArrayList<Integer>[] parties = new ArrayList[m];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int partySize = Integer.parseInt(st.nextToken());
            parties[i] = new ArrayList<>();
            if (partySize > 0) {
                int firstPerson = Integer.parseInt(st.nextToken());
                parties[i].add(firstPerson);
                for (int j = 1; j < partySize; j++) {
                    int person = Integer.parseInt(st.nextToken());
                    parties[i].add(person);
                    union(firstPerson, person); // 파티 참가자
                }
            }
        }

        int answer = 0;
        for (ArrayList<Integer> party : parties) {
            boolean canLie = true;
            for (int person : party) {
                // 진실을 아는 사람과 연결된 파티는 거짓말 할 수 없음
                if (truth[find(person)]) {
                    canLie = false;
                    break;
                }
            }
            if (canLie) {
                answer++;
            }
        }

        System.out.println(answer);
    }
}
