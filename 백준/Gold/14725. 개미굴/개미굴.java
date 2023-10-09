import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public class Main {
    private static class TrieNode {
        private HashMap<String, TrieNode> children = new HashMap<>();

        private void insert(String[] keys) {
            TrieNode node = this;
            for (String key : keys) {
                node.children.putIfAbsent(key, new TrieNode());
                node = node.children.get(key);
            }
        }

        private void print(String prefix) {
            ArrayList<String> keys = new ArrayList<>(children.keySet());
            Collections.sort(keys);  // 사전 순서로 정렬
            for (String key : keys) {
                System.out.println(prefix + key);
                children.get(key).print(prefix + "--");
            }
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        TrieNode root = new TrieNode();

        for (int i = 0; i < N; i++) {
            String[] input = br.readLine().split(" ");
            int K = Integer.parseInt(input[0]);
            String[] keys = Arrays.copyOfRange(input, 1, K + 1); // 단어들을 keys 배열에 저장
            root.insert(keys);
        }

        root.print("");
    }
}