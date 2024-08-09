import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/25757
// O(N)

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        String gameType = st.nextToken();
        Map<String, Integer> minPlayers = new HashMap<>();
        Set<String> players = new HashSet<>();

        minPlayers.put("Y", 2);
        minPlayers.put("F", 3);
        minPlayers.put("O", 4);

        for (int i = 0; i < n; i++) {
            players.add(br.readLine());
        }

        System.out.println(players.size() / (minPlayers.get(gameType) - 1));
    }
}
