import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 19951: 태상이의 훈련소 생활 (골드 5)
// https://www.acmicpc.net/problem/19951
// 시간복잡도 O(N)

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] playGroundHeights = new int[n + 2];
        int[] prefixSum = new int[n + 2];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            playGroundHeights[i] = Integer.parseInt(st.nextToken());
        }

        for (int j = 0; j < m; j++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            prefixSum[start] += k;
            prefixSum[end + 1] -= k;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            prefixSum[i + 1] += prefixSum[i];
            playGroundHeights[i] += prefixSum[i];

            sb.append(playGroundHeights[i]).append(" ");
        }

        System.out.println(sb.toString().trim());
    }
}