class Solution {
    static int euclidean(int a, int b) {
        if(b == 0) return a;
        return euclidean(b, a % b);
    }
    public int solution(int a, int b) {
        int answer = 2;
        
        int gcd = euclidean(b, a % b);
        
        a /= gcd;
        b /= gcd;
        
        while(b % 2 == 0 && b > 2) {
            b /= 2;
        }
        
        while(b % 5 == 0 && b > 5) {
            b /= 5;
        }
                
        // b == 1 죽, 정수도 유한 소수로 분류. 분모가 1인 경우도 유한소수
        if(b == 5 || b == 2 || b == 1) {
            answer = 1;
        } 
        
        return answer;
    }
}