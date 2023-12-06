import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static final char[] COMMANDS = {'D', 'S', 'L', 'R'};
    private static final int MAX = 10000;

    private static class Progress {
        private final int number;
        private final StringBuilder path;

        public Progress(int number, StringBuilder path) {
            this.number = number;
            this.path = path;
        }
    }

    private static String findShortestPath(int originalNumber, int targetNumber) {
        boolean[] visited = new boolean[MAX];
        Queue<Progress> queue = new LinkedList<>();
        queue.offer(new Progress(originalNumber, new StringBuilder()));

        while (!queue.isEmpty()) {
            Progress current = queue.poll();

            if (current.number == targetNumber) {
                return current.path.toString();
            }

            for (char command : COMMANDS) {
                int newNumber = processCommand(current.number, command);

                if (!visited[newNumber]) {
                    visited[newNumber] = true;
                    StringBuilder newPath = new StringBuilder(current.path).append(command);
                    queue.offer(new Progress(newNumber, newPath));
                }
            }
        }
        return "";
    }


    private static int processCommand(int number, char command) {
        switch (command) {
            case 'D':
                return (number * 2) % MAX;
            case 'S':
                return (number == 0) ? 9999 : number - 1;
            case 'L':
                return rotateLeft(number);
            case 'R':
                return rotateRight(number);
            default:
                return number;
        }
    }

    private static int rotateLeft(int number) {
        return (number % 1000) * 10 + number / 1000;
    }

    private static int rotateRight(int number) {
        return (number / 10) + (number % 10) * 1000;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int originalNumber = Integer.parseInt(st.nextToken());
            int targetNumber = Integer.parseInt(st.nextToken());

            System.out.println(findShortestPath(originalNumber, targetNumber));
        }
    }
}
