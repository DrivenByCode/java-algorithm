import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static class NumInfo implements Comparable<NumInfo> {
        private final int orgVal;
        private final int absVal;
        private NumInfo(final int orgVal, final int absVal) {
            this.orgVal = orgVal;
            this.absVal = absVal;
        }
        @Override
        public int compareTo(final NumInfo other) {
            if(this.absVal == other.absVal) {
                return this.orgVal - other.orgVal;
            }
            return this.absVal - other.absVal;
        }
    }
    public static void main(String[] args) throws IOException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        PriorityQueue<NumInfo> pq = new PriorityQueue<>();

        for(int i = 0; i < n; i++) {
            int num = Integer.parseInt(br.readLine());
            if(num == 0) {
                if(pq.isEmpty()) {
                    System.out.println(0);
                    continue;
                }
                System.out.println(pq.poll().orgVal);
                continue;
            }
            pq.offer(new NumInfo(num, Math.abs(num)));
        }
    }
}