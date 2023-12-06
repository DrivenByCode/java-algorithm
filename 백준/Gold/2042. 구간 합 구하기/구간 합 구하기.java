import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static long[] arr, tree;

    private static long init(int start, int end, int node) {
        // 재귀의 종료, 리프 노드의 값 할당, 구간 합의 계산을 의미
        if (start == end) return tree[node] = arr[start];

        int mid = (start + end) / 2;
        return tree[node] = init(start, mid, node * 2) + init(mid + 1, end, node * 2 + 1);
    }

    private static void update(int start, int end, int node, int index, long diff) {
        if (index < start || index > end) return;

        tree[node] += diff;
        if (start != end) {
            int mid = (start + end) / 2;
            update(start, mid, node * 2, index, diff);
            update(mid + 1, end, node * 2 + 1, index, diff);
        }
    }

    private static long sum(int start, int end, int node, int left, int right) {
        if (left > end || right < start) return 0;

        if (left <= start && end <= right) return tree[node];

        int mid = (start + end) / 2;
        return sum(start, mid, node * 2, left, right) + sum(mid + 1, end, node * 2 + 1, left, right);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        arr = new long[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Long.parseLong(br.readLine());
        }

        tree = new long[N * 4];
        init(0, N - 1, 1);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());

            if (a == 1) {
                update(0, N - 1, 1, b - 1, c - arr[b - 1]);
                arr[b - 1] = c;
            } else {
                sb.append(sum(0, N - 1, 1, b - 1, (int) c - 1)).append('\n');
            }
        }

        System.out.println(sb);
    }
}

