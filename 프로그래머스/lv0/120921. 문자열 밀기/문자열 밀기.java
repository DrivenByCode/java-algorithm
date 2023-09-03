class Solution {
    public static int solution(String A, String B) {
        int answer = -1;
        int len = A.length();

        for (int i = 0; i < len; i++) {
            if (A.equals(B)) {
                return i;
            }
            // 한칸 씩 밈을 가정 = 마지막 것 + 처음부터 마지막 - 1까지 한 것을 더함
            // 예를들어 hello 일 때 -> o + hell -> ohell
            A = A.charAt(len - 1) + A.substring(0, len - 1);
        }

        return answer;
    }
}