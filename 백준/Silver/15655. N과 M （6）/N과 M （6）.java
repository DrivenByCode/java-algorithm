import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int[] selectedNumbers;
    static StringBuilder sb = new StringBuilder();
    static int[] arr;
    static int n, m;

    // 조합 구하기
    private static void getPermutation(int level, int start) {
        if (level == m) {
            for (int selectedNumber : selectedNumbers) {
                sb.append(selectedNumber + " ");
            }
            sb.append("\n");
            return;
        }
        for (int i = start; i < n; i++) {
            selectedNumbers[level] = arr[i];
            getPermutation(level + 1, i + 1);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        selectedNumbers = new int[m];

        arr = new int[n];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        getPermutation(0, 0);

        System.out.println(sb);
    }
}
