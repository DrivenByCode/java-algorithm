import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int n, m;
    private static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[n + 1];

        st = new StringTokenizer(br.readLine());

        int lt = 0;
        int rt = 0;

        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(st.nextToken());
            arr[i] = num;
            rt += num;
            lt = Math.max(lt, num);
        }

        while (lt <= rt) {
            int mid = (lt + rt) / 2;
            if (getCounts(mid) <= m) {
                rt = mid - 1;
            } else {
                lt = mid + 1;
            }
        }

        System.out.println(lt);
    }

    private static int getCounts(int capacity) {
        int count = 1;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            if (sum + arr[i] > capacity) {
                count++;
                sum = arr[i];
                continue;
            }
            sum += arr[i];
        }

        return count;
    }
}
