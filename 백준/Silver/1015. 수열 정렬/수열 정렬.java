import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] a = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        Point[] arr = new Point[n];

        for (int i = 0; i < n; i++) {
            arr[i] = new Point(i, a[i]);
        }

        // arr 원소 값 기준으로 오름차순 정렬
        Arrays.sort(arr, Comparator.comparingInt((Point x) -> x.y));

        int[] p = new int[n];

        // arr의 원소값 기준으로 오름차순 정렬한 것의 원래 인덱스를 p의 인덱스로 하고, p[인덱스] 값이 순차적으로 0부터 n-1의 값이 됨.
        for (int i = 0; i < n; i++) {
            p[arr[i].x] = i;
        }

        for (int num : p) {
            System.out.print(num + " ");
        }

    }
}
