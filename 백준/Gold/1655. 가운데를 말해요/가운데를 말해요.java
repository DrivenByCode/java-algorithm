import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> minPq = new PriorityQueue<>();
        PriorityQueue<Integer> maxPq = new PriorityQueue<>(Collections.reverseOrder());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(br.readLine());

            // 최소힙과 최대힙의 크기가 같다면 최대힙에 삽입한다. (중앙값 또는 작은 값으로 만들기 위함)
            if (minPq.size() == maxPq.size()) {
                maxPq.offer(num);
                // 두 힙의 크기가 다르다면 최소힙에 넣는다.
            } else {
                minPq.offer(num);
            }

            if (!minPq.isEmpty() && !maxPq.isEmpty()) {
                // 중앙값 될 수가 더 크면 안됨. 같거나 최소힙에서 빼낸 값이 더 커야함.
                // 만약 최대힙에서 뺴낸 값이 더 크다면 최소힙, 최대힙에서 뺴낸 값들을 스왑함.
                if (minPq.peek() < maxPq.peek()) {
                    int tmp = minPq.poll();
                    minPq.offer(maxPq.poll());
                    maxPq.offer(tmp);
                }
            }

            sb.append(maxPq.peek()).append("\n");
        }

        System.out.println(sb);
    }
}
