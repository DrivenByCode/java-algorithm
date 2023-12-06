import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static int[] trees;

    private static long getTreeSize(int height) {
        long size = 0;
        for (int tree : trees) {
            if (tree > height) {
                size += tree - height;
            }
        }
        return size;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        trees = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).sorted().toArray();

        int lt = 0;
        int rt = trees[n - 1] + 1;
        while (lt < rt) {
            int mid = (lt + rt) / 2;
            if (getTreeSize(mid) < m) {
                rt = mid;
            } else {
                lt = mid + 1;
            }
        }

        System.out.println(lt - 1); // upper-bound일 때 lt - 1한 것이 최대값
    }
}
