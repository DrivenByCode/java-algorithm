import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

// 백준 11728
// https://www.acmicpc.net/11728
// 시간복잡도 : O(n)

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] A = new int[n];
        int[] B = new int[m];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            B[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(A);
        Arrays.sort(B);

        int lt = 0;
        int rt = 0;

        List<Integer> answer = new ArrayList<>();

        while (lt < n && rt < m) {
            if (A[lt] > B[rt]) {
                answer.add(B[rt++]);
            } else {
                answer.add(A[lt++]);
            }
        }

        while (lt < n) {
            answer.add(A[lt++]);
        }

        while (rt < m) {
            answer.add(B[rt++]);
        }

        StringBuilder sb = new StringBuilder();

        for (int num : answer) {
            sb.append(num).append(" ");
        }

        System.out.println(sb.toString().trim());
    }
}