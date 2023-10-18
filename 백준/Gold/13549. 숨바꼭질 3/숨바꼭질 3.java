import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// bfs는 가중치가 없거나 모두 같아야 사용가능
// 이 문제는 가중치(시간)가 0인경우 1인경우로 2가지임
// 따라서 1. 다익스트라 2. 0-1 bfs 3. 큐에 -1, 1과 그 좌표의 2의 거듭제곱인 것도 넣음.
public class Main {
    private static int MAX = (int)1e5 + 1;

    private static class Position implements Comparable<Position> {
        private final int x;
        private final int time;

        private Position(int x, int time) {
            this.x = x;
            this.time = time;
        }

        @Override
        public int compareTo(Position other) {
            return this.time - other.time;
        }
    }

    private static boolean isInMaxRange(int pos) {
        return 0 <= pos && pos < MAX;
    }

    private static int dijkstra(int start, int end) {
        if (start == end) return 0;

        int[] times = new int[MAX];
        for (int i = 0; i < MAX; i++) {
            times[i] = Integer.MAX_VALUE;
        }
        times[start] = 0;

        PriorityQueue<Position> queue = new PriorityQueue<>();
        queue.offer(new Position(start, 0));

        while (!queue.isEmpty()) {
            Position pos = queue.poll();
            int now = pos.x;
            int time = pos.time;

            if (now == end) return time;

            if (isInMaxRange(now * 2) && time < times[now * 2]) {
                times[now * 2] = Math.min(times[now * 2], time);
                queue.offer(new Position(now * 2, time));
            }

            if (isInMaxRange(now + 1) && time + 1 < times[now + 1]) {
                times[now + 1] = Math.min(times[now + 1], time + 1);
                queue.offer(new Position(now + 1, time + 1));
            }

            if (isInMaxRange(now - 1) && time + 1 < times[now - 1]) {
                times[now - 1] = Math.min(times[now - 1], time + 1);
                queue.offer(new Position(now - 1, time + 1));
            }
        }

        return -1; // 이 부분은 도달할 수 없는 경우를 처리하기 위함입니다. 문제의 조건에 따라 적절히 수정해주세요.
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());  // 출발지점
        int k = Integer.parseInt(st.nextToken());  // 도착지점

        System.out.println(dijkstra(n, k));
    }
}
