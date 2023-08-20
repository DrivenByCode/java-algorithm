import java.awt.Point;

class Solution {
    public static boolean areParallel(Point A, Point B, Point C, Point D) {
        double slopeAB, slopeCD;
        
        if (A.x - B.x == 0) {
            slopeAB = Double.POSITIVE_INFINITY;
        } else {
            slopeAB = (double) (A.y - B.y) / (A.x - B.x);
        }
        
        if (C.x - D.x == 0) {
            slopeCD = Double.POSITIVE_INFINITY;
        } else {
            slopeCD = (double) (C.y - D.y) / (C.x - D.x);
        }
        
        return slopeAB == slopeCD;
    }

    public static int checkParallelCombinations(Point[] points) {
        // 모든 가능한 조합 확인
        if (areParallel(points[0], points[1], points[2], points[3])) return 1;
        if (areParallel(points[0], points[2], points[1], points[3])) return 1;
        if (areParallel(points[0], points[3], points[1], points[2])) return 1;

        return 0;
    }
    public int solution(int[][] dots) {
        Point[] points = new Point[4];
        int idx = 0;
        
        for(int[] arr : dots) {
            int x = arr[0];
            int y = arr[1];
            points[idx++] = new Point(x, y);
        }
        return checkParallelCombinations(points);
    }
}