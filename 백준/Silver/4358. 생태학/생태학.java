import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        HashMap<String, Integer> speciesCount = new HashMap<>();
        int totalCount = 0;

        while (true) {
            String input = br.readLine();
            if (input == null || input.isEmpty()) {
                break;
            }

            speciesCount.put(input, speciesCount.getOrDefault(input, 0) + 1);
            totalCount++;
        }

        StringBuilder sb = new StringBuilder();

        final int total = totalCount;

        speciesCount.entrySet().stream().sorted(Map.Entry.comparingByKey()).forEach(entry -> {
            final float proportion = (float) entry.getValue() / total;
            final float roundedResult = Math.round(proportion * 1000000) / 10000f;
            sb.append(entry.getKey()).append(" ").append(String.format("%.4f", roundedResult)).append("\n");
        });

        System.out.println(sb.toString().trim());
    }
}
