import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static class TreeNode {
        private TreeNode[] children = new TreeNode[26];

        private void insert(String str) {
            // 루트 노드를 바라보게 함
            TreeNode node = this;
            char[] chars = str.toCharArray();
            for (char c : chars) {
                if (node.children[c - 'a'] == null) {
                    node.children[c - 'a'] = new TreeNode();
                }
                node = node.children[c - 'a'];
            }
        }

        private boolean contains(String str) {
            // 루트 노드를 바라보게 함
            TreeNode node = this;
            char[] chars = str.toCharArray();
            for (char c : chars) {
                // 만약 해당 자식노드가 비어있다면 접두사를 포함하고 있지 않은 것
                // 즉시 false 리턴
                if (node.children[c - 'a'] == null) {
                    return false;
                }
                node = node.children[c - 'a'];
            }
            return true;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        TreeNode trie = new TreeNode();

        for (int i = 0; i < n; i++) {
            trie.insert(br.readLine());
        }

        int cnt = 0;

        for (int j = 0; j < m; j++) {
            if (trie.contains(br.readLine())) {
                cnt++;
            }
        }

        System.out.println(cnt);
    }
}