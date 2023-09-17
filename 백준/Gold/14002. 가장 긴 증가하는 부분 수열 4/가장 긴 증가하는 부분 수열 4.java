import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] nums = new int[n];
        
        // 현재까지의 원소들로 만들 수 있는 증가하는 부분 수열에서 마지막 원소의 인덱스를 저장
        int[] tails = new int[n];

        // 해당원소 이전에 위치하는 원소의 인덱스를 저장
        int[] prev = new int[n];

        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
            prev[i] = -1;
        }

        int len = 0;

        for (int i = 0; i < n; i++) {
            int lt = 0;
            int rt = len;

            while (lt < rt) {
                int mid = lt + ((rt - lt) / 2);
                if (nums[tails[mid]] < nums[i]) {
                    lt = mid + 1;
                } else {
                    rt = mid;
                }
            }

            //            System.out.println(">>>" + i + " " + lt + " " + rt + " " + len);
            // 현재까지의 원소들로 만들 수 있는 증가하는 부분 수열에서 마지막 원소의 인덱스를 저장
            tails[lt] = i;

            // 현재 인덱스에 있는 원소 이전에 존재하는 원소의 인덱스를 저장
            if (0 < lt) {
                prev[i] = tails[lt - 1];
            }

            if (lt == len) {
                len++;
            }
        }

        //        System.out.println(">>>>" + Arrays.toString(tails));
        //        System.out.println(">>>>" + Arrays.toString(prev));

        System.out.println(len);

        Deque<Integer> stack = new ArrayDeque<>();
        int idx = tails[len - 1]; // 가장 긴 증가하는 부분 수열의 마지막 원소의 인덱스
        while (idx != -1) {
            stack.push(nums[idx]);
            idx = prev[idx]; // 이전 원소의 인덱스로 이동
        }

        while (!stack.isEmpty()) {
            System.out.print(stack.pop() + " ");
        }
    }
}
