import java.io.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());

        int[] songsLevel = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int q = Integer.parseInt(br.readLine());

        int[] prefixSum = new int[n]; // 연주할 수 없는 곡들의 누적합

        for(int  i = 0; i < n - 1; i++) {
            if(songsLevel[i] > songsLevel[i + 1]) {
                prefixSum[i + 1]++;
            }
            prefixSum[i + 1] += prefixSum[i];
        }
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < q; i++) {
            int[] playList = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int res = prefixSum[playList[1] - 1] - prefixSum[playList[0] - 1];
            sb.append(res).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}