class Solution {
    // "mm:ss" 형식의 시간을 배열로 변환
    private static int[] parseTime(String time) {
        String[] parts = time.split(":");
        return new int[]{Integer.parseInt(parts[0]), Integer.parseInt(parts[1])};
    }

    // 시간 배열을 초 단위로 변환
    private static int convertToSeconds(int[] time) {
        return time[0] * 60 + time[1];
    }

    // 초 단위를 "mm:ss" 형식으로 변환
    private static String formatTime(int totalSeconds) {
        int minutes = totalSeconds / 60;
        int seconds = totalSeconds % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }

    public String solution(String videoLength, String position, String openingStart, String openingEnd, String[] commands) {
        int[] videoLengthArray = parseTime(videoLength);
        int[] positionArray = parseTime(position);
        int[] openingStartArray = parseTime(openingStart);
        int[] openingEndArray = parseTime(openingEnd);

        int videoEnd = convertToSeconds(videoLengthArray);
        int current = convertToSeconds(positionArray);
        int openingStartSec = convertToSeconds(openingStartArray);
        int openingEndSec = convertToSeconds(openingEndArray);

        // 현재 위치가 오프닝 범위에 있다면 op_end로 이동
        if (openingStartSec <= current && current <= openingEndSec) {
            current = openingEndSec;
        }

        for (String command : commands) {
            if (command.equals("prev")) {
                // 10초 전으로 이동
                if (current < 10) {
                    current = 0;
                } else {
                    current -= 10;
                }
            } else if (command.equals("next")) {
                // 10초 후로 이동
                int remainingTime = videoEnd - current;
                if (remainingTime < 10) {
                    current = videoEnd;
                } else {
                    current += 10;
                }
            }

            // 오프닝 범위 체크
            if (openingStartSec <= current && current <= openingEndSec) {
                current = openingEndSec;
            }

            // 동영상 길이 범위 체크
            if (current > videoEnd) {
                current = videoEnd;
            }
        }

        return formatTime(current);
    }
}
