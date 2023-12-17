import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    private static class Work implements Comparable<Work> {
        private final int duration;
        private final int deadline;

        private Work(final int duration, final int deadline) {
            this.duration = duration;
            this.deadline = deadline;
        }

        @Override
        public int compareTo(Work other) {
            // 마감 시간이 늦은 것부터 정렬
            return other.deadline - this.deadline;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st;

        PriorityQueue<Work> workProcess = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int duration = Integer.parseInt(st.nextToken());
            int deadline = Integer.parseInt(st.nextToken());
            workProcess.offer(new Work(duration, deadline));
        }

        int currentTime = Integer.MAX_VALUE;
        while (!workProcess.isEmpty()) {
            Work currentWork = workProcess.poll();
            currentTime = Math.min(currentTime, currentWork.deadline) - currentWork.duration;
            if (currentTime < 0) {
                System.out.println(-1);
                return;
            }
        }

        System.out.println(currentTime);
    }
}
