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
