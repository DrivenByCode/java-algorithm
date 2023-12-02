import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Main {
    private static class CustomComparator implements Comparator<String> {

        @Override
        public int compare(String o1, String o2) {
            if (o1.length() == o2.length()) {
                return o1.compareTo(o2);
            }
            return o1.length() - o2.length();
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        ArrayList<String> arrayList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            if (!arrayList.contains(str)) {
                arrayList.add(str);
            }
        }
        arrayList.sort(new CustomComparator());

        StringBuilder sb = new StringBuilder();

        for (String str : arrayList) {
            sb.append(str).append("\n");
        }

        System.out.println(sb.toString().trim());
    }
}
