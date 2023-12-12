import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    private static class Time implements Comparable<Time> {
        private final int start;
        private final int end;

        private Time(final int start, final int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Time other) {
            if (this.start == other.start) {
                return Integer.compare(this.end, other.end);
            }
            return Integer.compare(this.start, other.start);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());

        PriorityQueue<Time> classes = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            classes.offer(new Time(start, end));
        }

        PriorityQueue<Integer> rooms = new PriorityQueue<>();
        while (!classes.isEmpty()) {
            Time current = classes.poll();
            if (!rooms.isEmpty() && rooms.peek() <= current.start) {
                rooms.poll();
            }
            rooms.offer(current.end);
        }

        System.out.println(rooms.size());
    }
}
