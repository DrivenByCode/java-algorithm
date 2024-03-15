import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
    private static char[][] map;
    private static HashMap<Integer, Integer> hasingTargetWords = new HashMap<>();
    private static final int[] dRow = {0, -1, 0, 1, -1, 1, -1, 1};
    private static final int[] dCol = {-1, 0, 1, 0, -1, 1, 1, -1};
    private static int n, m;

    private static void dfs(int x, int y, String word, int depth, HashMap<Integer, Integer> foundWords) {
        if (depth > 5) return; // 문자열 최대 길이 조절

        word += map[x][y];
        int hash = hash(word);

        if (hasingTargetWords.containsKey(hash)) {
            foundWords.put(hash, foundWords.getOrDefault(hash, 0) + 1);
        }

        for (int i = 0; i < 8; i++) {
            int nx = (x + dRow[i] + n) % n;
            int ny = (y + dCol[i] + m) % m;
            dfs(nx, ny, word, depth + 1, foundWords);
        }
    }

    private static int hash(String s) {
        int hash = 0, power = 1;
        for (char c : s.toCharArray()) {
            hash += (c - 'a' + 1) * power;
            power <<= 5;
        }
        return hash;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        map = new char[n][m];

        for (int i = 0; i < n; i++) {
            String input = br.readLine();
            map[i] = input.toCharArray();
        }

        for (int i = 0; i < k; i++) {
            String input = br.readLine();
            int hash = hash(input);
            hasingTargetWords.put(hash, hasingTargetWords.getOrDefault(hash, 0) + 1);
        }

        HashMap<Integer, Integer> foundWords = new HashMap<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                dfs(i, j, "", 0, foundWords);
            }
        }

        for (int hash : hasingTargetWords.keySet()) {
            System.out.println(foundWords.getOrDefault(hash, 0));
        }
    }
}
