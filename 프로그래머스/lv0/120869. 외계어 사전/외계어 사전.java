import java.util.HashMap;

class Solution {
    public int solution(String[] spell, String[] dic) {
        int answer = 2;
        
        // spell 배열의 문자들과 그들의 빈도수를 저장하는 맵 생성
        HashMap<Character, Integer> spellMap = new HashMap<>();
        
        for(String s : spell) {
            char c = s.charAt(0);
            spellMap.put(c, spellMap.getOrDefault(c, 0) + 1);
        }
        
        // dic 배열의 문자들과 그들의 빈도수를 저장하는 맵 생성
        HashMap<Character, Integer> dicMap;
        
        for(String word : dic) {
            dicMap = new HashMap<>();
            for(char c : word.toCharArray()) {
                dicMap.put(c, dicMap.getOrDefault(c, 0) + 1);
            }
            
            if(spellMap.equals(dicMap)) {
                answer = 1;
                break; // 일치하는 문자열을 찾은 경우 루프를 종료
            }
        }
        
        return answer;
    }
}
