import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static final int MAX = 51;
    private static int n, m;
    private static long[] guitars;
    private static int minGuitars = MAX;
    private static int playedSongs = 0;

    private static void findMinGuitars(int index, int count, long songs) {
        int songCount = Long.bitCount(songs);
        // 최대한 플레이 할 수 있는 곡수가 갱신 될 때(커질 때), minGuitars변수 갱신
        if (songCount > playedSongs) {
            playedSongs = songCount;
            minGuitars = count;
        // 더 작은 기타 수로 최대한 많은 곡을 연주할 수 있다면 minGuitars 갱신
        } else if (songCount == playedSongs && count < minGuitars) {
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
        
        boolean hasPlayableSong = false;
        
        for (int i = 0; i < n; i++) {
            String input = br.readLine().split(" ")[1];
            for (int j = 0; j < m; j++) {
                if (input.charAt(j) == 'Y') {
                    guitars[i] |= (1L << j);
                    hasPlayableSong = true;
                }
            }
        }
        
        if (!hasPlayableSong) {
            System.out.println(-1);
            return;
        }

        findMinGuitars(0, 0, 0L);

        System.out.println(minGuitars);
    }
}
