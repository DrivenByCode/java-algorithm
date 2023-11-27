import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        ArrayList<String> species = new ArrayList<>();
        HashMap<String, Integer> speciesCount = new HashMap<>();
        int totalCount = 0;

        while (true) {
            String input = br.readLine();
            if (input == null || input.isEmpty()) {
                break;
            }

            if (!species.contains(input)) {
                species.add(input);
            }
            speciesCount.put(input, speciesCount.getOrDefault(input, 0) + 1);
            totalCount++;
        }

        Collections.sort(species);

        StringBuilder sb = new StringBuilder();


        for (final String s : species) {
            final float proportion = (float) speciesCount.get(s) / totalCount;
            final float roundedResult = Math.round(proportion * 1000000) / 10000f;

            sb.append(s).append(" ").append(String.format("%.4f", roundedResult)).append("\n");
        }

        System.out.println(sb.toString().trim());
    }
}