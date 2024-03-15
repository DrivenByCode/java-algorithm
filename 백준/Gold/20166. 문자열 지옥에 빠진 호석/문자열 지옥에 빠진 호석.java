import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static char[][] map;
    private static final HashMap<Integer, Integer> hasingTargetWords = new HashMap<>();
    private static final int[] dRow = {0, -1, 0, 1, -1, 1, -1, 1};
    private static final int[] dCol = {-1, 0, 1, 0, -1, 1, 1, -1};
    private static int[] powers = new int[6];

    private static int n, m;

    private static class Node {
        int row, col, hash, depth;

        public Node(int row, int col, int depth, int hash) {
            this.row = row;
            this.col = col;
            this.depth = depth;
            this.hash = hash;
        }
    }

    private static void bfs(int row, int col) {
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(row, col, 1, map[row][col] - 'a' + 1)); // 초기 위치와 해시값

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            int hash = current.hash;

            // 현재 해시값으로 찾은 단어의 개수를 1 증가시킴
            if (hasingTargetWords.containsKey((hash))) {
                hasingTargetWords.put(hash, hasingTargetWords.get(hash) + 1);
            }

            int nextDepth = current.depth + 1;
            if (current.depth < 5) { // 깊이 제한 (문제에 따라 조정)
                for (int i = 0; i < 8; i++) {
                    int nextRow = (current.row + dRow[i] + n) % n;
                    int nextCol = (current.col + dCol[i] + m) % m;
                    int nextHash = hash + ((map[nextRow][nextCol] - 'a' + 1) * powers[nextDepth]);
                    queue.offer(new Node(nextRow, nextCol, nextDepth, nextHash));
                }
            }
        }
    }

    private static int hash(String s) {
        int hash = 0;
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            hash += (chars[i] - 'a' + 1) * powers[i + 1];
        }
        return hash;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int powerInit = 1;
        for (int i = 1; i <= 5; i++) {
            powers[i] = powerInit;
            powerInit <<= 5;
        }

        map = new char[n][m];

        for (int i = 0; i < n; i++) {
            String input = br.readLine();
            map[i] = input.toCharArray();
        }

        List<Integer> targetWords = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            String input = br.readLine();
            int hash = hash(input);
            hasingTargetWords.put(hash, 0);
            targetWords.add(hash);
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                bfs(i, j);
            }
        }

        for (int hash : targetWords) {
            System.out.println(hasingTargetWords.getOrDefault(hash, 0));
        }
    }
}
