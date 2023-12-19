import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    private static class Time implements Comparable<Time> {
        private final int startTime;
        private final int endTime;

        private Time(final int startTime, final int endTime) {
            this.startTime = startTime;
            this.endTime = endTime;
        }

        @Override
        public int compareTo(Time other) {
            if (this.startTime == other.startTime) {
                return this.endTime - other.endTime;
            }
            return this.startTime - other.startTime;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st;

        PriorityQueue<Time> meetings = new PriorityQueue<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            meetings.offer(new Time(start, end));
        }

        PriorityQueue<Integer> rooms = new PriorityQueue<>();
        while (!meetings.isEmpty()) {
            Time current = meetings.poll();
            if (!rooms.isEmpty() && rooms.peek() <= current.startTime) {
                rooms.poll();
            }
            rooms.offer(current.endTime);
        }

        System.out.println(rooms.size());
    }
}
