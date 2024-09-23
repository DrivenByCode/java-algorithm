import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// O(T * M)
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            // M개의 비행기 정보를 읽어들이지만 실제로 사용하지 않음
            for (int i = 0; i < M; i++) {
                br.readLine();
            }

            // 모든 비행 스케쥴은 항상 연결 그래프를 이루기 때문에
            // 모든 국가를 연결하는 데 필요한 최소 비행기 수는 N - 1
            System.out.println(N - 1);
        }
    }
}
