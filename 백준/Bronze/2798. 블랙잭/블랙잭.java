import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        List<Integer> nums = new ArrayList<>();

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            nums.add(Integer.parseInt(st.nextToken()));
        }

        Collections.sort(nums, Collections.reverseOrder());

        TreeSet<Integer> sums = new TreeSet<>();

        for (int i = 0; i < n - 2; i++) {
            for (int j = i + 1; j < n - 1; j++) {
                for (int k = j + 1; k < n; k++) {
                    int sum = nums.get(i) + nums.get(j) + nums.get(k);

                    if (sum <= m) {
                        sums.add(sum);
                    }
                }
            }
        }

        System.out.println(sums.floor(m));
    }
}
