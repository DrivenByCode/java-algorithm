import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.PriorityQueue;

public class Main {
    private static void removeTop(PriorityQueue<Integer> heap, HashMap<Integer, Integer> countMap) {
        while (!heap.isEmpty()) {
            int top = heap.poll();
            if (countMap.getOrDefault(top, 0) > 0) {
                countMap.put(top, countMap.get(top) - 1);
                if (countMap.get(top) == 0) {
                    countMap.remove(top);
                }
                break;
            }
        }
    }

    private static void removeInvalid(PriorityQueue<Integer> heap, HashMap<Integer, Integer> countMap) {
        while (!heap.isEmpty() && countMap.getOrDefault(heap.peek(), 0) == 0) {
            heap.poll();
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());

            PriorityQueue<Integer> minHeap = new PriorityQueue<>();
            PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
            HashMap<Integer, Integer> countMap = new HashMap<>();

            for (int i = 0; i < n; i++) {
                String command = br.readLine();
                String[] commands = command.split(" ");

                if (commands[0].equals("I")) {
                    int value = Integer.parseInt(commands[1]);
                    minHeap.offer(value);
                    maxHeap.offer(value);
                    countMap.put(value, countMap.getOrDefault(value, 0) + 1);
                } else if (commands[0].equals("D")) {
                    if (commands[1].equals("1")) {
                        removeTop(maxHeap, countMap);
                    } else if (commands[1].equals("-1")) {
                        removeTop(minHeap, countMap);
                    }
                }
            }
            removeInvalid(maxHeap, countMap);
            removeInvalid(minHeap, countMap);

            if (minHeap.isEmpty() || maxHeap.isEmpty()) {
                System.out.println("EMPTY");
            } else {
                System.out.println(maxHeap.poll() + " " + minHeap.poll());
            }
        }
    }
}
