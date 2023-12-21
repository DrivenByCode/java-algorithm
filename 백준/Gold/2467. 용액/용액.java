import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        Arrays.sort(arr);

        int lt = 0;
        int rt = arr.length - 1;

        int min = Math.abs(arr[lt] + arr[rt]);
        int targetLt = arr[lt];
        int targetRt = arr[rt];
        while (lt < rt) {
            int sum = Math.abs(arr[lt] + arr[rt]);

            if (min > sum) {
                min = sum;
                targetLt = arr[lt];
                targetRt = arr[rt];
            }

            if (sum == 0) {
                break;
            }

            if (Math.abs(arr[lt]) > Math.abs(arr[rt])) {
                lt++;
            }else if (Math.abs(arr[lt]) < Math.abs(arr[rt])) {
                rt--;
            }
        }

        System.out.println(targetLt + " " + targetRt);
    }
}
