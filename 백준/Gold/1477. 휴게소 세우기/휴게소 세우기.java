import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    private static boolean canPlaceRestStops(List<Integer> locations, int maxDist, int m) {
        int count = 0;

        for (int i = 1; i < locations.size(); i++) {
            int dist = locations.get(i) - locations.get(i - 1);
            if (dist > maxDist) {
                count += (dist - 1) / maxDist;
            }
        }

        return count <= m;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());

        List<Integer> locations = new ArrayList<>();
        locations.add(0);
        locations.add(l);

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            locations.add(Integer.parseInt(st.nextToken()));
        }

        Collections.sort(locations);

        int left = 1;
        int right = l;
        int answer = 0;

        while (left <= right) {
            int mid = (left + right) / 2;
            if (canPlaceRestStops(locations, mid, m)) {
                answer = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        System.out.println(answer);
    }
}