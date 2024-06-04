import java.util.*;

class Solution {
    static class Point {
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return x == point.x && y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    public int solution(int[] arrows) {
        int[] dx = {0, 1, 1, 1, 0, -1, -1, -1};
        int[] dy = {1, 1, 0, -1, -1, -1, 0, 1};
        
        Set<Point> visitedPoints = new HashSet<>();
        Set<String> visitedEdges = new HashSet<>();
        
        Point current = new Point(0, 0);
        visitedPoints.add(current);
        
        int rooms = 0;
        
        for (int arrow : arrows) {
            for (int i = 0; i < 2; i++) {
                Point next = new Point(current.x + dx[arrow], current.y + dy[arrow]);
                String edge1 = current.x + "," + current.y + "-" + next.x + "," + next.y;
                String edge2 = next.x + "," + next.y + "-" + current.x + "," + current.y;
                
                if (visitedPoints.contains(next) && !visitedEdges.contains(edge1)) {
                    rooms++;
                }
                
                visitedPoints.add(next);
                visitedEdges.add(edge1);
                visitedEdges.add(edge2);
                
                current = next;
            }
        }
        
        return rooms;
    }
}
