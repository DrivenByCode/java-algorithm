import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static class CustomComparator implements Comparator<Point> {

        @Override
        public int compare(Point p1, Point p2) {
            if (p1.y == p2.y) {
                return p1.x - p2.x;
            }
            return p1.y - p2.y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        CustomComparator cc = new CustomComparator();

        PriorityQueue<Point> orders = new PriorityQueue<>(cc);

        for (int i = 0; i < n; i++) {
            String[] tmp = br.readLine().split(" ");
            int k = Integer.parseInt(tmp[0]);
            for (int j = 1; j <= k; j++) {
                orders.add(new Point(i, Integer.parseInt(tmp[j])));
            }
        }

        int[] sushies = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        PriorityQueue<Integer> q = new PriorityQueue<>();
        for (int i : sushies) {
            q.add(i);
        }

        int[] answer = new int[n];

        while (!q.isEmpty()) {
            if (orders.isEmpty()) {
                break;
            }

            Integer tmp = q.peek();
            Point p = orders.peek();

            if (tmp != null) {
                if (p.y == tmp) {
                    answer[p.x]++;
                    orders.poll();
                    q.poll();
                } else if (p.y > tmp) {
                    q.poll();
                } else {
                    orders.poll();
                }
            }
        }

        System.out.println(Arrays.toString(answer).replaceAll("[,\\[\\]]", ""));
    }
}
