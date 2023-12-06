class Solution {
    public int solution(int chicken) {
        int answer = 0;
        int coupons = chicken;

        while (coupons >= 10) {
            int freeChickens = coupons / 10;
            answer += freeChickens;
            // 서비스 치킨 1마리를 얻을 때마다 10장의 쿠폰을 사용, 서비스 치킨에도 쿠폰이 1장씩 발급. 따라서 실제로는 9장의 쿠폰만 사용한 것과 같음.
            coupons = coupons - freeChickens * 9;
        }

        return answer;
    }
}