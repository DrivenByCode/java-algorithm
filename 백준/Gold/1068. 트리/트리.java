import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    private static int n;
    private static ArrayList<Integer>[] tree;
    private static boolean[] deleted;

    // dfs 원리를 이용해서 해당하는 부모노드와 그의 모든 자식노드를 삭제
    private static void deleteNode(int node) {
        // 해당 노드는 삭제했음을 기록
        deleted[node] = true;
        for (int child : tree[node]) {
            deleteNode(child);
        }
    }

    // dfs 원리를 이용하여 트리의 리프 노드(단말노드) 개수를 계산
    private static int countLeafNodes(int node) {
        if (deleted[node]) return 0;

        int count = 0;
        for (int child : tree[node]) {
            if (!deleted[child]) {
                count += countLeafNodes(child);
            }
        }

        // 자식 노드가 없거나 모든 자식 노드가 삭제된 경우 리프 노드로 카운트, 이게 리프노드(단말 노드)의 개념
        if (count == 0) return 1;

        return count;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        tree = new ArrayList[n];
        deleted = new boolean[n];

        for (int i = 0; i < n; i++) {
            tree[i] = new ArrayList<>();
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        int root = -1;
        for (int i = 0; i < n; i++) {
            int parent = Integer.parseInt(st.nextToken());
            if (parent == -1) {
                root = i;
            } else {
                tree[parent].add(i);
            }
        }

        int deleteNodeNumber = Integer.parseInt(br.readLine());
        deleteNode(deleteNodeNumber);

        // 루트 노드를 지웠으면 개수는 0, 리프노드(단말노드)는 루트노드를 제외함.
        // 그렇지 않은 경우, 삭제 후 남은 리프 노드의 개수를 계산하여 출력
        if (deleteNodeNumber == root) {
            System.out.println(0);
        } else {
            System.out.println(countLeafNodes(root));
        }
    }
}
