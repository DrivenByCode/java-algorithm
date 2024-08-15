import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static int[][] board = new int[9][9];

    private static boolean isValid(int row, int col, int num) {
        for (int i = 0; i < 9; i++) {
            // 같은 행이나 열에 해당 숫자가 있다면 숫자를 넣지 못함
            if (board[row][i] == num || board[i][col] == num) {
                return false;
            }
        }

        int startRow = (row / 3) * 3;
        int startCol = (col / 3) * 3;

        // 3*3 모양에 해당 숫자가 있다면 false 반환
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[startRow + i][startCol + j] == num) {
                    return false;
                }
            }
        }

        return true;
    }

    public static void solveSudoku(int row, int col) {
        // 마지막 열이면 다음 행 첫번째로 넘김
        if (col == 9) {
            solveSudoku(row + 1, 0);
            return;
        }

        // 마지막 행이면 스도쿠 출력
        if (row == 9) {
            printBoard();
            System.exit(0);
        }

        if (board[row][col] == 0) {
            for (int num = 1; num <= 9; num++) {
                if (isValid(row, col, num)) {
                    board[row][col] = num;
                    solveSudoku(row, col + 1);
                    board[row][col] = 0;
                }
            }
            return;
        }
        solveSudoku(row, col + 1);
    }

    private static void printBoard() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sb.append(board[i][j]);
            }
            sb.append("\n");
        }

        System.out.println(sb.toString().trim());
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 9; i++) {
            String input = br.readLine();
            for (int j = 0; j < 9; j++) {
                board[i][j] = input.charAt(j) - '0';
            }
        }

        solveSudoku(0, 0);
    }
}
