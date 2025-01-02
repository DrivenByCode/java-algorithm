import java.io.*;
import java.util.*;

public class Main {
    static class Player {
        int level;
        String nickname;

        Player(int level, String nickname) {
            this.level = level;
            this.nickname = nickname;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int p = Integer.parseInt(st.nextToken()); // 플레이어 수
        int m = Integer.parseInt(st.nextToken()); // 방 최대 정원

        // 방들을 담을 리스트
        // 각각의 방은 Player 리스트로 구성
        List<List<Player>> rooms = new ArrayList<>();

        // 플레이어 정보를 입력받는 순서대로 방에 배정
        for (int i = 0; i < p; i++) {
            st = new StringTokenizer(br.readLine());
            int level = Integer.parseInt(st.nextToken());
            String nickname = st.nextToken();

            Player newPlayer = new Player(level, nickname);
            boolean assigned = false; // 방에 배정되었는지 여부

            // 이미 생성된 방들을 순회하며 배정 가능 여부 확인
            for (List<Player> room : rooms) {
                // 방의 첫 번째 플레이어 레벨
                int baseLevel = room.get(0).level;
                if (room.size() < m && Math.abs(baseLevel - newPlayer.level) <= 10) {
                    room.add(newPlayer);
                    assigned = true;
                    break;
                }
            }

            // 배정되지 못했다면 새로운 방을 만든다.
            if (!assigned) {
                List<Player> newRoom = new ArrayList<>();
                newRoom.add(newPlayer);
                rooms.add(newRoom);
            }
        }

        for (List<Player> room : rooms) {
            // 방이 꽉 찼으면 Started!, 아니면 Waiting!
            if (room.size() == m) {
                sb.append("Started!\n");
            } else {
                sb.append("Waiting!\n");
            }

            room.sort(Comparator.comparing(ply -> ply.nickname));

            for (Player ply : room) {
                sb.append(ply.level).append(" ").append(ply.nickname).append("\n");
            }
        }

        System.out.print(sb);
    }
}
