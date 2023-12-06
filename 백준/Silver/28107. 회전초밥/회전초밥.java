import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static class Order {
        int customer;
        int sushiType;

        public Order(int customer, int sushiType) {
            this.customer = customer;
            this.sushiType = sushiType;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        // 스시 타입 오름 차순 정렬, 만약 스시 타입이 같다면, 고객 번호 오름차순으로 정렬
        PriorityQueue<Order> orders = new PriorityQueue<>(
                (o1, o2) -> o1.sushiType == o2.sushiType ? o1.customer - o2.customer : o1.sushiType - o2.sushiType
        );

        for (int i = 0; i < n; i++) {
            String[] tmp = br.readLine().split(" ");
            int k = Integer.parseInt(tmp[0]);
            for (int j = 1; j <= k; j++) {
                orders.add(new Order(i, Integer.parseInt(tmp[j])));
            }
        }

        int[] sushies = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        PriorityQueue<Integer> q = new PriorityQueue<>();
        for (int i : sushies) {
            q.add(i);
        }

        int[] answer = new int[n];

        while (!q.isEmpty() && !orders.isEmpty()) {
            int sushi = q.peek();
            Order order = orders.peek();

            if (order.sushiType == sushi) {
                // 주문한 스시 타입과, 큐의 스시 타입이 같다면 해당 주문과 큐의 스시를 없앰
                answer[order.customer]++;
                orders.poll();
                q.poll();
            } else if (order.sushiType > sushi) {
                // 주문한 스시 타입 보다, 큐의 스시 번호가 더 크다면 큐의 스시를 버림
                q.poll();
            } else {
                // 주문한 스시 타입 보다, 큐의 스시 번호가 더 작다면 해당 주문을 버림
                orders.poll();
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int value : answer) {
            sb.append(value).append(" ");
        }
        System.out.println(sb.toString().trim());

    }
}