import java.util.*;
class Solution {
    public int solution(int[] bandage, int health, int[][] attacks) {        
        // 시전 시간(t), 초당 회복량(x), 추가 회복량(y)
        int t = bandage[0];
        int x = bandage[1];
        int y = bandage[2];        
        
        int idx = 0;
        int healCnt = 0;
        
        int maxLen = attacks.length;
        
        int maxHealth = health;
        
        int maxTime = attacks[maxLen - 1][0];

    
        for(int sec = 1; sec <= maxTime; sec++) {
            if(health <= 0) return -1;
            
            // attacks [공격 시간 , 피해량]
            if(attacks[idx][0] == sec) {                
                int damage = attacks[idx][1];
                health = (health - damage) >= 0 ? health - damage : 0;
                healCnt = 0;
                idx++;
                
                if(idx == maxLen) {
                    return health > 0 ? health : -1;
                }
            } else {
                healCnt++;
                if(health >= maxHealth) {
                    continue;
                }
                health += x;
                if(healCnt % t == 0) {
                    health += y;
                }
                
                if(health > maxHealth) health = maxHealth;
            }
            
        }
        return health > 0 ? health : -1;
    }
}