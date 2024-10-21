import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    private static boolean[] visited;
    private static int[] arr;
    private static int max = Integer.MIN_VALUE;
    private static int target = 0;
    private static List<Integer> nums = new ArrayList<>();

    private static void dfs(int i) {
        if (arr[i] == target) {
            nums.add(target);
        }

        if (!visited[arr[i]]) {
            visited[arr[i]] = true;
            dfs(arr[i]);
            visited[arr[i]] = false;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        arr = new int[n + 1];
        visited = new boolean[n + 1];

        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        for (int i = 1; i <= n; i++) {
            visited[i] = true;
            target = i;
            dfs(i);
            visited[i] = false;
        }

        Collections.sort(nums);

        System.out.println(nums.size());
        for (int num : nums) {
            System.out.println(num);
        }
    }
}
