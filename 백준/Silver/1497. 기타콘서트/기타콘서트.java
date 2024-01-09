import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static final int MAX = 51;
    private static int n, m;
    private static long[] guitars;
    private static int minGuitars = MAX;
    private static int max = 0; // 추가된 변수

    private static void findMinGuitars(int index, int count, long songs) {
        int songCount = Long.bitCount(songs);
        if (songCount > max) {
            max = songCount;
            minGuitars = count;
        } else if (songCount == max && count < minGuitars) {
            minGuitars = count;
        }

        if (index == n) {
            return;
        }

        findMinGuitars(index + 1, count + 1, songs | guitars[index]);
        findMinGuitars(index + 1, count, songs);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        guitars = new long[n];

        for (int i = 0; i < n; i++) {
            String input = br.readLine().split(" ")[1];
            for (int j = 0; j < m; j++) {
                if (input.charAt(j) == 'Y') {
                    guitars[i] |= (1L << j);
                }
            }
        }

        findMinGuitars(0, 0, 0L);

        if (max == 0) {
            System.out.println(-1);
        } else {
            System.out.println(minGuitars);
        }
    }
}
