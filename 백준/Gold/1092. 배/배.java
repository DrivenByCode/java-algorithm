import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());  // 크레인의 수

        st = new StringTokenizer(br.readLine());

        List<Integer> cranes = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int crane = Integer.parseInt(st.nextToken());
            cranes.add(crane);
        }

        cranes.sort(Collections.reverseOrder());

        int m = Integer.parseInt(br.readLine());  // 박스의 수

        st = new StringTokenizer(br.readLine());
        List<Integer> boxes = new ArrayList<>();

        for (int j = 0; j < m; j++) {
            boxes.add(Integer.parseInt(st.nextToken()));
        }

        boxes.sort(Collections.reverseOrder());

        if (cranes.get(0) < boxes.get(0)) {
            // 가장 큰 크레인도 가장 무거운 박스를 못 옮기는 경우
            System.out.println(-1);
            return;
        }

        int time = 0;

        while (!boxes.isEmpty()) {
            int craneIndex = 0;
            int boxIndex = 0;

            // 크레인이 최대한 많은 박스를 처리할 수 있도록 반복
            while (craneIndex < n && boxIndex < boxes.size()) {
                if (cranes.get(craneIndex) >= boxes.get(boxIndex)) {
                    // 크레인이 옮길 수 있는 가장 무거운 박스를 처리
                    boxes.remove(boxIndex);
                    craneIndex++;
                } else {
                    // 현재 크레인이 옮길 수 없는 경우, 다음 박스로 이동
                    boxIndex++;
                }
            }

            time++;
        }

        System.out.println(time);
    }
}
