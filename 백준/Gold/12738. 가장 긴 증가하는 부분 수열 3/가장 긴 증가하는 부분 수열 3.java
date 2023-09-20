import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 반례 모음
// https://www.acmicpc.net/board/view/25494
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] nums = new int[n];
        int[] lis = new int[n]; // LIS를 저장할 배열

        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        int len = 0;

        for (int i = 0; i < n; i++) {
            int lt = 0;
            int rt = len;

            while (lt < rt) {
                int mid = lt + ((rt - lt) / 2);
                if (lis[mid] < nums[i]) {
                    lt = mid + 1;
                } else {
                    rt = mid;
                }
            }

            lis[lt] = nums[i]; // lis 배열에 nums[i] 저장

            /* lt는 이분 탐색을 통해 현재 숫자 nums[i]가 삽입될 위치를 나타냅니다.
            len은 현재까지 찾아진 LIS의 길이를 나타냅니다.
            if (lt == len) 조건은 nums[i]가 현재까지의 LIS의 마지막 숫자보다 크다는 것을 의미합니다. 즉, nums[i]를 LIS에 추가할 수 있다는 것을 나타냅니다
            따라서, len++를 통해 LIS의 길이를 1 증가시킵니다.*/
            if (lt == len) {
                len++;
            }
        }


        System.out.println(len);
    }
}
