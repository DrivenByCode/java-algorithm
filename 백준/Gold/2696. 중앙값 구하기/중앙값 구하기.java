import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(br.readLine());

            int[] inputs = new int[n];
            int idx = 0;

            while (idx < n) {
                for (String token : br.readLine().split(" ")) {
                    if (idx >= n) break;
                    inputs[idx++] = Integer.parseInt(token);
                }
            }


            PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
            PriorityQueue<Integer> minHeap = new PriorityQueue<>();

            List<Integer> result = new ArrayList<>();
            StringBuilder sb = new StringBuilder();

            for (int j = 0; j < n; j++) {
                if (maxHeap.isEmpty() || inputs[j] <= maxHeap.peek()) {
                    maxHeap.offer(inputs[j]);
                } else {
                    minHeap.offer(inputs[j]);
                }

                // 힙 밸런싱 : 항상 최대 힙의 크기가 최소힙과 같거나 1더 큼
                if (maxHeap.size() > minHeap.size() + 1) {
                    minHeap.offer(maxHeap.poll());
                } else if (minHeap.size() > maxHeap.size()) {
                    maxHeap.offer(minHeap.poll());
                }

                // 홀수 번째일 때 중앙값 추가 (j는 0부터 시작)
                if (j % 2 == 0) {
                    result.add(maxHeap.peek());
                }
            }

            sb.append(result.size()).append("\n");
            for (int k = 0; k < result.size(); k++) {
                sb.append(result.get(k)).append(" ");
                // 10번 째 숫자일 때마다 개행
                if ((k + 1) % 10 == 0) sb.append("\n");
            }

            System.out.println(sb.toString().trim());
        }
    }
}
