import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        TreeMap<String, Integer> wordCount = new TreeMap<>(Collections.reverseOrder());
        for(int i = 0; i < n; i++) {
            String word = br.readLine();
            wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
        }

        for(Map.Entry<String, Integer> entry: wordCount.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
            break;
        }

        bw.write(sb.toString());
        bw.flush();
    }
}