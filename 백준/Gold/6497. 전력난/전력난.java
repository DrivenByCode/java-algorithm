import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    static int[] parents;
    static int[] ranks;

    static class Location implements Comparable<Location> {
        private final int vertex1;
        private final int vertex2;
        private final int value;

        private Location(int vertex1, int vertex2, int value) {
            this.vertex1 = vertex1;
            this.vertex2 = vertex2;
            this.value = value;
        }

        @Override
        public int compareTo(Location other) {
            // 비용 기준으로 오름차순
            return this.value - other.value;
        }
    }

    static private void init(int size) {
        parents = new int[size];
        ranks = new int[size];
        for (int i = 0; i < size; i++) {
            ranks[i] = 1;
            parents[i] = i;
        }
    }

    static private int find(int x) {
        if (parents[x] == x) return parents[x];
        return parents[x] = find(parents[x]);
    }

    static private void Union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if (rootX == rootY) return;

        if (ranks[rootX] > ranks[rootY]) {
            parents[rootY] = rootX;
        } else {
            parents[rootX] = rootY;
            if (ranks[rootX] == ranks[rootY]) {
                ranks[rootY]++;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        ArrayList<Location> city = new ArrayList<>();

        boolean flag = false;

        while (!flag) {
            String line = br.readLine();
            if (line == null || line.equals("0 0")) break; // 입력의 끝이거나 "0 0"을 만나면 종료

            st = new StringTokenizer(line);

            // 집의 수 - 정점 수 (Vertex)
            int m = Integer.parseInt(st.nextToken());

            // 길의 수 - 간선 수 (Edges)
            int n = Integer.parseInt(st.nextToken());

            // city 초기화
            city.clear();

            // 초기화
            init(m);

            int maxValue = 0;

            for (int i = 0; i < n; i++) {
                // 계속 반복하기 때문에, ArrayList와 paraents, ranks는 새로운 테스트 케이스가 시작될 때마다 초기화 해야함.
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                if (x == 0 && y == 0) {
                    flag = true;
                    break;
                }
                int value = Integer.parseInt(st.nextToken());
                city.add(new Location(x, y, value));
                maxValue += value;
            }

            Collections.sort(city);

            // 모두 더 하면 최소 비용.

            int minValue = 0;
            int cnt = 0;
            for (Location lc : city) {
                int vertex1 = lc.vertex1;
                int vertex2 = lc.vertex2;
                if (find(vertex1) != find(vertex2)) {
                    Union(vertex1, vertex2);
                    minValue += lc.value;
                    cnt++;
                }

                if (cnt == m + 1) break;
            }

            // 최대 비용 - 최소 비용: 절약 할 수 있는 최대 비용.
            System.out.println(maxValue - minValue);
        }
    }
}
