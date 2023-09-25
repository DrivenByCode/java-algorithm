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
        int[] arr = new int[n];
        int[] lis = new int[n];
        int[] prev = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            prev[i] = -1;
        }

        int len = 0;

        for (int i = 0; i < n; i++) {
            int lt = 0;
            int rt = len;
            while (lt < rt) {
                int mid = lt + ((rt - lt) / 2);
                if (arr[lis[mid]] < arr[i]) {
                    lt = mid + 1;
                } else {
                    rt = mid;
                }
            }

            lis[lt] = i;

            // 현재 인덱스에 있는 원소 이전에 존재하는 원소의 인덱스를 저장
            if (lt > 0) {
                prev[i] = lis[lt - 1];
            }
            // lt : 현재 len 과 같음
            // 현재 원소가 배열을 마지막 위치에 추가되는 경우, LIS길이가 1증가함을 의미함.
            if (lt == len) {
                len++;
            }
        }

        System.out.println(len);

        Deque<Integer> stk = new ArrayDeque<>();
        int idx = lis[len - 1];
        while (idx != -1) {
            stk.push(arr[idx]);
            idx = prev[idx]; // 이전 원소의 인덱스로 이동
        }

        StringBuilder sb = new StringBuilder();

        while (!stk.isEmpty()) {
            sb.append(stk.pop() + " ");
        }

        System.out.println(sb);
    }
}
