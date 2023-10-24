import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    private static ArrayList<Long> list;

    private static long getCount(long mid) {
        long count = 0;
        for (long num : list) {
            count += num / mid;
        }
        return count;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            long number = Long.parseLong(br.readLine());
            list.add(number);
        }

        // 이분 탐색을 위해 정렬
        Collections.sort(list);

        long lt = 1;
        long rt = list.get(list.size() - 1);
        while (lt <= rt) {
            long mid = (lt + rt) / 2;

            // 최대 값을 찾아야하기 때문에
            // k보다 크거나 같은 lt를 찾고
            // 해당 값에 -1한 결과를 출력
            if (getCount(mid) >= k) {
                lt = mid + 1;
            } else {
                rt = mid - 1;
            }
        }

        System.out.println(lt - 1);
    }
}
