import java.io.*;
import java.util.*;

public class Main {
    private static final Map<String, Integer> wordCount = new HashMap<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        Set<String> w = new HashSet<>();

        TreeSet<String> words = new TreeSet<>((o1, o2) -> {
            if(wordCount.get(o1) == wordCount.get(o2)) {
                if(o1.length() == o2.length()) {
                    return o1.compareTo(o2);
                }
                return o2.length() - o1.length();
            }
            return wordCount.get(o2) - wordCount.get(o1);
        });

        for(int i  = 0; i < n; i++) {
            String word = br.readLine();
            if(word.length() < m) continue;
            wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
            w.add(word);
        }

        words.addAll(w);

        StringBuilder sb = new StringBuilder();

        while(!words.isEmpty()) {
            sb.append(words.pollFirst()).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
    }
}