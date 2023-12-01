import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static class TrieNode {
        private TrieNode[] children;
        private boolean isEndOfWord;

        private TrieNode() {
            children = new TrieNode[26];
            isEndOfWord = false;
        }
    }

    private static class Trie {
        private final TrieNode root;

        private Trie() {
            root = new TrieNode();
        }

        private void insert(String key) {
            TrieNode node = root;
            for (int level = 0; level < key.length(); level++) {
                int index = key.charAt(level) - 'a';
                if (node.children[index] == null) {
                    node.children[index] = new TrieNode();
                }
                // 루트 노드를 자식 노드로 업데이트
                node = node.children[index];
            }
            // 끝까지 단어를 삽입했으면 이것은 마지막 단어.
            node.isEndOfWord = true;
        }

        private int getTypingCount(String key) {
            TrieNode node = root;
            int totalCount = 0;

            for (int level = 0; level < key.length(); level++) {
                int index = key.charAt(level) - 'a';
                if (level == 0 || node.isEndOfWord || countChildren(node) != 1) {
                    totalCount++;
                }
                // 루트 노드를 자식 노드로 업데이트
                node = node.children[index];
            }

            return totalCount;
        }

        private int countChildren(TrieNode node) {
            int count = 0;
            for (int i = 0; i < 26; i++) {
                if (node.children[i] != null) {
                    count++;
                    if (count >= 2) {
                        return count;
                    }
                }
            }
            return count;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            String input = br.readLine();
            if (input == null || input.isEmpty()) {
                break;
            }

            int n = Integer.parseInt(input);
            String[] inputs = new String[n];
            Trie trie = new Trie();

            for (int i = 0; i < n; i++) {
                inputs[i] = br.readLine();
                trie.insert(inputs[i]);
            }

            int answer = 0;
            for (String str : inputs) {
                answer += trie.getTypingCount(str);
            }

            System.out.printf("%.2f\n", answer / (double) n);
        }
    }
}
