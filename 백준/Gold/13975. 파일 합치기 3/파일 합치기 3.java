import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        for (int i = 0; i < t; i++) {
            int k = Integer.parseInt(br.readLine());
            String[] strs = br.readLine().split(" ");
            PriorityQueue<Long> pq = new PriorityQueue<>();

            for (String str : strs) {
                pq.offer(Long.parseLong(str));
            }

            long totalCost = 0;

            while (pq.size() > 1) {
                long first = pq.poll();
                long second = pq.poll();
                long cost = first + second;
                totalCost += cost;
                pq.offer(cost);
            }

            System.out.println(totalCost);
        }
    }
}