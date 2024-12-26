import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int n;
    private static List<Meeting> meetings;
    private static int[] memo;
    private static class Meeting {
        int startTime, endTime, pplCnt;

        Meeting(int startTime, int endTime, int pplCnt) {
            this.startTime = startTime;
            this.endTime = endTime;
            this.pplCnt = pplCnt;
        }
    }

    private static int dfs(int index) {
        if (index >= n) return 0; 
        if (memo[index] != -1) return memo[index]; // 이미 계산된 경우 반환

        // 현재 회의를 포함하지 않는 경우
        int exclude = dfs(index + 1);

        // 현재 회의를 포함하는 경우
        int include = meetings.get(index).pplCnt;
        int nextIndex = index + 1;

        // 겹치지 않는 다음 회의 탐색
        while (nextIndex < n && meetings.get(nextIndex).startTime < meetings.get(index).endTime) {
            nextIndex++;
        }

        include += dfs(nextIndex);

        // 최대값 저장
        memo[index] = Math.max(exclude, include);
        return memo[index];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        meetings = new ArrayList<>();
        StringTokenizer st;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int startTime = Integer.parseInt(st.nextToken());
            int endTime = Integer.parseInt(st.nextToken());
            int pplCnt = Integer.parseInt(st.nextToken());
            meetings.add(new Meeting(startTime, endTime, pplCnt));
        }

        meetings.sort(Comparator.comparingInt(a -> a.endTime));

        memo = new int[n];
        Arrays.fill(memo, -1);

        System.out.println(dfs(0));
    }
}