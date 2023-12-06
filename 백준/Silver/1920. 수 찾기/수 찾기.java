import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        ArrayList<Long> target = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            long number = Long.parseLong(st.nextToken());
            target.add(number);
        }

        ArrayList<Long> input = new ArrayList<>();
        int m = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            long number = Long.parseLong(st.nextToken());
            input.add(number);
        }

        Collections.sort(target);

        // n과 m은 다를 수 있음에 주의
        for (int i = 0; i < m; i++) {
            int lt = 0;
            int rt = n - 1;
            int answer = 0;
            while (lt <= rt) {
                int mid = (lt + rt) / 2;
                if (target.get(mid).equals(input.get(i))) {
                    answer = 1;
                    break;
                }
                if (target.get(mid) > input.get(i)) {
                    // target은 오름차순 정렬되어있기 때문에 값이 크다면 왼쪽절반을 확인
                    rt = mid - 1;
                } else {
                    /// target은 오름차순 정렬되어있기 때문에 값이 작거나 같다면 오른쪽 절반을 확인
                    lt = mid + 1;
                }
            }
            System.out.println(answer);
        }
    }
}
