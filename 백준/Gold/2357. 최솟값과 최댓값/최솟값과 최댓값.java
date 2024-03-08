import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[] arr;
    static int[] minTree, maxTree;

    static int initMin(int start, int end, int node) {
        if (start == end) return minTree[node] = arr[start];
        int mid = (start + end) / 2;
        return minTree[node] = Math.min(initMin(start, mid, node * 2), initMin(mid + 1, end, node * 2 + 1));
    }

    static int initMax(int start, int end, int node) {
        if (start == end) return maxTree[node] = arr[start];
        int mid = (start + end) / 2;
        return maxTree[node] = Math.max(initMax(start, mid, node * 2), initMax(mid + 1, end, node * 2 + 1));
    }

    static int findMin(int start, int end, int node, int left, int right) {
        if (left > end || right < start) return Integer.MAX_VALUE;
        if (left <= start && end <= right) return minTree[node];
        int mid = (start + end) / 2;
        return Math.min(findMin(start, mid, node * 2, left, right), findMin(mid + 1, end, node * 2 + 1, left, right));
    }

    static int findMax(int start, int end, int node, int left, int right) {
        if (left > end || right < start) return Integer.MIN_VALUE;
        if (left <= start && end <= right) return maxTree[node];
        int mid = (start + end) / 2;
        return Math.max(findMax(start, mid, node * 2, left, right), findMax(mid + 1, end, node * 2 + 1, left, right));
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N];
        minTree = new int[N * 4];
        maxTree = new int[N * 4];

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        initMin(0, N - 1, 1);
        initMax(0, N - 1, 1);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int left = Integer.parseInt(st.nextToken()) - 1;
            int right = Integer.parseInt(st.nextToken()) - 1;

            int min = findMin(0, N - 1, 1, left, right);
            int max = findMax(0, N - 1, 1, left, right);

            sb.append(min).append(" ").append(max).append("\n");
        }

        System.out.print(sb.toString());
    }
}
