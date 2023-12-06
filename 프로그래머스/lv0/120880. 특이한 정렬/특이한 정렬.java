import java.util.*;
class Solution {
    private static class DistByNum implements Comparable<DistByNum> {
        private final int num;
        private final int dist;
        private DistByNum(int num, int dist) {
            this.num = num;
            this.dist = dist;
        }
        
        @Override
        public int compareTo(DistByNum other) {
            if(this.dist == other.dist) return other.num - this.num;
            return this.dist - other.dist;
        }
    }
    public int[] solution(int[] numlist, int n) {
        ArrayList<DistByNum> distList = new ArrayList<>();

        for(int i : numlist) {
            int dist = Math.abs(i - n);
            distList.add(new DistByNum(i, dist));
        }
        
        Collections.sort(distList);
        
        int[] answer = new int[distList.size()];
        int idx = 0;
        
        for(DistByNum dn : distList) {
            answer[idx++] = dn.num;
        }
        
        return answer;
    }
}