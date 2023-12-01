import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    private static class TrieNode {
        private TrieNode[] children;
        private boolean isEndOfWord;

        private TrieNode() {
            children = new TrieNode[10]; // 전화번호는 각각 0-9
            isEndOfWord = false;
        }
    }

    private static class Trie {
        private TrieNode root;

        private Trie() {
            root = new TrieNode();
        }

        // 루트부터 말단 노드까지 계속 반복하여, 트라이에 이미 있고 그게 말단 노드이면 false 리턴
        // 만약 해당 노드가 비어있다면
        private boolean insert(String key) {
            // root노드의 참조를 node 변수에 할당하여 root노드의 참조를 보존
            TrieNode node = root;
            for (int level = 0; level < key.length(); level++) {
                int index = key.charAt(level) - '0';
                if (node.children[index] == null) {
                    node.children[index] = new TrieNode();
                    // 말단 노드인지 여부 파악
                } else if (node.children[index].isEndOfWord) {
                    return false; // 이미 트라이에 있는 번호이고 마지막 글자(말단 노드)일 때
                }

                // 노드를 자식 노드로 계속 업데이트
                node = node.children[index];
            }

            // 마지막 단어까지 다 넣으면 그것은 마지막 단어가 맞음
            node.isEndOfWord = true;

            return true;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(br.readLine());
            Trie trie = new Trie();
            String[] phoneNumbers = new String[n];
            boolean consistent = true;

            for (int j = 0; j < n; j++) {
                phoneNumbers[j] = br.readLine();
            }

            Arrays.sort(phoneNumbers); // 사전순으로 정렬.

            for (int j = 0; j < n; j++) {
                if (!trie.insert(phoneNumbers[j])) {
                    consistent = false;
                    break;
                }
            }

            System.out.println(consistent ? "YES" : "NO");
        }
    }
}
