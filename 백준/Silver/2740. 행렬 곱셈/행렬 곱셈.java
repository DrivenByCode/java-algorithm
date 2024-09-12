import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 첫 번째 행렬 a의 크기 입력
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());  // a의 행의 수
        int m = Integer.parseInt(st.nextToken());  // a의 열의 수

        // 첫 번째 행렬 a 입력
        int[][] a = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                a[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 두 번째 행렬 b의 크기 입력
        st = new StringTokenizer(br.readLine());
        int m2 = Integer.parseInt(st.nextToken()); // b의 행의 수 (m과 동일)
        int k = Integer.parseInt(st.nextToken());  // b의 열의 수

        // 두 번째 행렬 b 입력
        int[][] b = new int[m][k];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < k; j++) {
                b[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 결과 행렬 c 초기화
        int[][] c = new int[n][k];

        // 행렬 곱셈 수행
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < k; j++) {
                for (int l = 0; l < m; l++) {
                    c[i][j] += a[i][l] * b[l][j];
                }
            }
        }

        // 결과 출력
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < k; j++) {
                sb.append(c[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }
}
