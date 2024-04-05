class Solution {
    private static boolean[] visited;
    private static int answer;
    private static String[] changableWords;
    private static boolean isPossible(String begin, String target) {
        int len = begin.length();
        
        int cnt = 0;
        
        for(int i = 0; i < len; i++) {
            if(begin.charAt(i) != target.charAt(i)) {
                cnt++;
                if(cnt >= 2) {
                    return false;
                }
            }
        }
        
        if(cnt == 0) {
            return false;
        }
        
        return true;
    }
    
    private static void dfs(String begin, String target, int count) {        
        if(answer != 0 && answer < count) {
            return;
        }
        
        if(begin.equals(target)) {
            answer = count;
            return;
        }

        for(int i = 0; i < changableWords.length; i++) {
            if(!visited[i]) {
                if(isPossible(begin, changableWords[i])) {
                    visited[i] = true;
                    dfs(changableWords[i], target, count + 1);
                    visited[i] = false;
                }
            }
        }
    }
    
    public int solution(String begin, String target, String[] words) {
        int len = words.length;
        
        visited = new boolean[words.length];
        
        changableWords = words;
        
        dfs(begin, target, 0);
        
        return answer;
    }
}