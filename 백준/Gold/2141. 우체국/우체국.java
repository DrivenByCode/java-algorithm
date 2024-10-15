import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static class Village implements Comparable<Village> {
        long position;
        long population;

        Village(long position, long population) {
            this.position = position;
            this.population = population;
        }

        @Override
        public int compareTo(Village o) {
            return Long.compare(this.position, o.position);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        Village[] villages = new Village[n];
        long totalPopulation = 0;
        StringTokenizer st;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            long position = Long.parseLong(st.nextToken());
            long population = Long.parseLong(st.nextToken());

            villages[i] = new Village(position, population);
            totalPopulation += population;
        }

        // 마을 위치를 기준으로 오름차순 정렬
        Arrays.sort(villages);

        long accumulatedPopulation = 0;
        long halfPopulation = (totalPopulation + 1) / 2; // 절반 이상의 인구를 찾기 위해

        for (int i = 0; i < n; i++) {
            accumulatedPopulation += villages[i].population;
            if (accumulatedPopulation >= halfPopulation) {
                System.out.println(villages[i].position);
                break;
            }
        }
    }
}