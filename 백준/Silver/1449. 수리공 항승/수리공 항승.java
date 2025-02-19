import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); 
        int l = Integer.parseInt(st.nextToken());

        int[] leaks = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            leaks[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(leaks); // 오름차순 정렬 

        int cnt = 0; 
        int covered = 0;

        for (int i = 0; i < n; i++) {
            if (covered < leaks[i]) { 
                cnt++;
                covered = leaks[i] + l - 1;
            }
        }

        System.out.println(cnt);
    }
}
