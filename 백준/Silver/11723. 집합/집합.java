import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int m = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int s = 0;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            String command = st.nextToken();
            if (command.equals("all")) {
                // 1부터 20을 사용하기 때문에 맨 오른쪽 비트는 버리고 사용하므로 21이 들어감.
                // -1 한 이유는 이진수에서 2의 제곱수는 맨 좌측 비트만 켜져있는 상태여서
                // 모든 비트가 켜져있게 하기 위함.
                s = (1 << 21) - 1;
            } else if (command.equals("empty")) {
                s = 0;
            } else {
                int num = Integer.parseInt(st.nextToken());
                if (command.equals("add")) {
                    s |= (1 << num);
                } else if (command.equals("check")) {
                    sb.append((s & (1 << num)) == (1 << num) ? 1 : 0).append("\n");
                } else if (command.equals("remove")) {
                    s &= ~(1 << num);
                } else if (command.equals("toggle")) {
                    s ^= (1 << num);
                }
            }
        }
        System.out.println(sb.toString().trim());
    }
}
