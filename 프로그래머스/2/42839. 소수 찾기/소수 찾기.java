import java.util.*;

class Solution {
    private static Set<Integer> primeSet = new HashSet<>();
    private static boolean[] visited;
    
    // 소수 판별 메서드
    private static boolean isPrime(int num) {
        if (num < 2) return false;
        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) return false;
        }
        return true;
    }

    private static void dfs(int level, String numbers, String s) {
        if (!s.isEmpty()) {
            int num = Integer.parseInt(s);
            if (isPrime(num)) {
                primeSet.add(num);
            }
        }
        if (level == numbers.length()) {
            return;
        }

        for (int i = 0; i < numbers.length(); i++) {
            if (!visited[i]) {
                visited[i] = true;
                dfs(level + 1, numbers, s + numbers.charAt(i));
                visited[i] = false;
            }
        }
    }

    public int solution(String numbers) {
        int n = numbers.length();
        visited = new boolean[n];
        dfs(0, numbers, "");
        return primeSet.size();
    }
}