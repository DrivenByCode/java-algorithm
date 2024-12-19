import java.io.*;
import java.util.*;

public class Main {
    private static final Map<String, Integer> wordCount = new HashMap<>();
    private static class WordInfo implements Comparable<WordInfo> {
        private final String word;
        private final int wordCount;
        public WordInfo(String word, int wordCount) {
            this.word = word;
            this.wordCount = wordCount;
        }

        @Override
        public int compareTo(WordInfo other) {
            if(this.wordCount == other.wordCount) {
                if(this.word.length() == other.word.length()) {
                    return this.word.compareTo(other.word);
                }
                return other.word.length() - this.word.length();
            }
            return other.wordCount - this.wordCount;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        PriorityQueue<WordInfo> words = new PriorityQueue<>();

        for(int i  = 0; i < n; i++) {
            String word = br.readLine();
            if(word.length() < m) continue;
            wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
        }

        for(Map.Entry<String, Integer> entry : wordCount.entrySet()) {
            String word = entry.getKey();
            int wordCount = entry.getValue();

            words.offer(new WordInfo(word, wordCount));
        }

        StringBuilder sb = new StringBuilder();

        while(!words.isEmpty()) {
            sb.append(words.poll().word).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
    }
}