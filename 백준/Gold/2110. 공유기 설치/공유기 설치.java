import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static int[] array;
    private static int n, c;

    private static int getCount(int len) {
        int last = array[0];
        int cnt = 1;
        for (int i = 1; i < n; i++) {
            // 가장 가까운 두 집의 거리가 len 이상인 것의 개수가 c개면
            // len이 가장 인접한 두 공유기 사이의 최대 거리가 됨.
            if (array[i] - last >= len) {
                cnt++;
                last = array[i];
            }
        }
        return cnt;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(array);

        int min = 1;
        int max = array[n - 1] - array[0];

        while (min <= max) {
            int mid = (min + max) / 2;

            if (getCount(mid) < c) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }

        System.out.println(max);
    }
}
