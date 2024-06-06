import java.util.HashSet;
import java.util.Set;

class Solution {
    public int solution(int N, int number) {
        if (N == number) return 1;
        
        Set<Integer>[] dp = new Set[9];
        
        for (int i = 1; i < 9; i++) {
            dp[i] = new HashSet<>();
            // i번째 set에 N숫자를 집어 넣음.
            dp[i].add(Integer.parseInt(new String(new char[i]).replace("\0", String.valueOf(N))));
        }
        
        // dp[i]는 N을 i개 써서 만들 수 있는 수의 조합을 말함
        for (int i = 1; i < 9; i++) {
            for (int j = 1; j < i; j++) {
                for (int a : dp[j]) {
                    for (int b : dp[i - j]) {
                        dp[i].add(a + b);
                        dp[i].add(a - b);
                        dp[i].add(a * b);
                        if (b != 0) dp[i].add(a / b);
                    }
                }
            }
            if (dp[i].contains(number)) {
                return i;
            }
        }
        
        return -1;
    }
}
