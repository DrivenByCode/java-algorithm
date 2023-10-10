import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

    private static class TrieNode {
        private HashMap<Character, TrieNode> children = new HashMap<>();

        private void insert(String str) {
            TrieNode node = this;
            char[] chars = str.toCharArray();
            for (char c : chars) {
                node.children.putIfAbsent(c, new TrieNode());
                node = node.children.get(c);
            }
        }

        private boolean contains(String str) {
            TrieNode node = this;
            char[] chars = str.toCharArray();
            for (char c : chars) {
                if (node.children.get(c) != null) {
                    node = node.children.get(c);
                } else {
                    return false;
                }
            }
            return true;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        TrieNode trie = new TrieNode();

        for (int i = 0; i < n; i++) {
            String input = br.readLine();
            trie.insert(input);
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
