import java.util.*;
class Solution {
    private static boolean[] visited = new boolean[10_001];
    private static String[][] tickets;
    private static List<String> routes = new ArrayList<>();
    private static int n;
    
    private static void dfs(String start, String route, int cnt) {
        if(cnt == n) {
            routes.add(route);
            return;
        }
        
        for(int i = 0; i < n; i++) {
            if(start.equals(tickets[i][0]) && !visited[i]) {
                visited[i] = true;
                dfs(tickets[i][1], route + " " +  tickets[i][1], cnt + 1);
                visited[i] = false;
            }
        }
        
    }
    public String[] solution(String[][] tickets) {
        this.tickets = tickets;
        
        n = tickets.length;
                    
        dfs("ICN", "ICN", 0);
        
        Collections.sort(routes);
        
        String[] answer = routes.get(0).split(" ");
        
        return answer;
    }
}