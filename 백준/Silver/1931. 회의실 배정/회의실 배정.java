import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    private static class Time implements Comparable<Time> {
        private final long start;
        private final long end;

        private Time(final long start, final long end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Time other) {
            if (this.end == other.end) {
                // Long.compare를 사용하여 start 값을 비교
                return Long.compare(this.start, other.start);
            }
            // Long.compare를 사용하여 end 값을 비교
            return Long.compare(this.end, other.end);
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());

        PriorityQueue<Time> pq = new PriorityQueue<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            long start = Long.parseLong(st.nextToken());
            long end = Long.parseLong(st.nextToken());
            pq.offer(new Time(start, end));
        }

        long end = -1; // 초기화
        int answer = 0;
        while (!pq.isEmpty()) {
            Time now = pq.poll();
            if (end != -1) {
                if (now.start >= end) {
                    answer++;
                    end = now.end;
                }
            } else {
                answer++;
                end = now.end;
            }
        }

        System.out.println(answer);
    }
}
