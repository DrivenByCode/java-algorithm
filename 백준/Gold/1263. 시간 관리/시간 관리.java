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
            if (this.deadline == other.deadline) {
                return this.duration - other.duration;
            }
            return this.deadline - other.deadline;
        }
    }

    private static int getMaxStartTime(PriorityQueue<Work> workProcess, int time) {
        int startTime = time;
        while (!workProcess.isEmpty()) {
            Work currentWork = workProcess.poll();
            if (startTime + currentWork.duration <= currentWork.deadline) {
                startTime += currentWork.duration;
            } else {
                return -1;
            }
        }
        return time;
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

        int max = -2;
        for (int time = 0; time < 1000001; time++) {
            PriorityQueue<Work> tmpPq = new PriorityQueue<>();
            tmpPq.addAll(workProcess);
            int num = getMaxStartTime(tmpPq, time);
            if (num == -1) {
                max = Math.max(max, num);
                System.out.println(max);
                return;
            }

            max = Math.max(max, num);
        }


        System.out.println(max);
    }
}

