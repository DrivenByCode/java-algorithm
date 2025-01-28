import java.util.*;

class Solution {
    public int solution(String[] friends, String[] gifts) {
        // pairCounts[a][b] = a가 b에게 준 선물 횟수
        Map<String, Map<String, Integer>> pairCounts = new HashMap<>();
        for (String friend : friends) {
            pairCounts.put(friend, new HashMap<>());
        }

        // 선물 기록 처리
        for (String gift : gifts) {
            String[] arr = gift.split(" ");
            String sender = arr[0];
            String receiver = arr[1];

            Map<String, Integer> inner = pairCounts.get(sender);
            inner.put(receiver, inner.getOrDefault(receiver, 0) + 1);
        }

        // 선물 지수 계산 (전체적으로 준 선물 수 - 받은 선물 수)
        Map<String, Integer> giftIndex = new HashMap<>();
        // 먼저 전체적으로 준/받은 횟수 추적
        Map<String, Integer> totalGiven = new HashMap<>();
        Map<String, Integer> totalReceived = new HashMap<>();

        for (String friend : friends) {
            totalGiven.put(friend, 0);
            totalReceived.put(friend, 0);
        }

        // pairCounts를 순회하며 friend별로 준/받은 합계 구함
        for (String sender : friends) {
            for (Map.Entry<String, Integer> entry : pairCounts.get(sender).entrySet()) {
                String receiver = entry.getKey();
                int count = entry.getValue();
                totalGiven.put(sender, totalGiven.get(sender) + count);
                totalReceived.put(receiver, totalReceived.get(receiver) + count);
            }
        }

        for (String friend : friends) {
            giftIndex.put(friend, totalGiven.get(friend) - totalReceived.get(friend));
        }

        // 다음 달에 받을 선물 수 계산
        Map<String, Integer> nextMonthGifts = new HashMap<>();
        for (String friend : friends) {
            nextMonthGifts.put(friend, 0);
        }

        // 모든 (i, j) 쌍(i<j)에 대해 비교
        for (int i = 0; i < friends.length; i++) {
            for (int j = i + 1; j < friends.length; j++) {
                String f1 = friends[i];
                String f2 = friends[j];
                int f1toF2 = pairCounts.get(f1).getOrDefault(f2, 0);
                int f2toF1 = pairCounts.get(f2).getOrDefault(f1, 0);

                if (f1toF2 > f2toF1) {
                    // f1이 더 많이 보냄 => f1이 다음 달에 f2로부터 선물 받음
                    nextMonthGifts.put(f1, nextMonthGifts.get(f1) + 1);
                } else if (f1toF2 < f2toF1) {
                    // f2가 더 많이 보냄 => f2가 다음 달에 f1로부터 선물 받음
                    nextMonthGifts.put(f2, nextMonthGifts.get(f2) + 1);
                } else {
                    // 같거나 아예 주고받은 적이 없는 경우 => 선물 지수 비교
                    int idx1 = giftIndex.get(f1);
                    int idx2 = giftIndex.get(f2);
                    if (idx1 > idx2) {
                        // f1이 선물 지수 큼 => f1이 f2에게 선물 받음
                        nextMonthGifts.put(f1, nextMonthGifts.get(f1) + 1);
                    } else if (idx1 < idx2) {
                        // f2가 선물 지수 큼 => f2가 f1에게 선물 받음
                        nextMonthGifts.put(f2, nextMonthGifts.get(f2) + 1);
                    } else {
                        // 선물 지수도 같으면 다음 달에 주고받지 않음
                    }
                }
            }
        }

        // 가장 많이 선물을 받는 친구가 받을 선물의 수를 찾음
        int answer = 0;
        for (String friend : friends) {
            answer = Math.max(answer, nextMonthGifts.get(friend));
        }

        return answer;
    }
}
