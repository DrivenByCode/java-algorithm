import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int sum = 0;
        int len = 0;
        int lt = 0;
        int min = Integer.MAX_VALUE;
        for (int rt = 0; rt < arr.length; rt++) {
            sum += arr[rt];
            len++;

            while (sum >= s) {
                min = Math.min(len, min);
                sum -= arr[lt++];
                len--;
            }
        }

        System.out.println(min == Integer.MAX_VALUE ? 0 : min);
    }
}
