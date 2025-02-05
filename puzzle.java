import java.util.Scanner;

class Puzzle{
    public static void main(String[] args) {
        int[][] board = {
            { 1, 2, 3, 4 },
            { 5, 6, 7, 8 },
            { 9, 10, 11, 12 },
            { 13, 14, 15, 0 } // 0 represents the empty space
        };

        Scanner scanner = new Scanner(System.in);
        printBoard(board);

        while (true) {
            System.out.println("Enter the number to move (0 to exit): ");
            int number = scanner.nextInt();
            if (number == 0) {
                break;
            }

            if (moveNumber(board, number)) {
                printBoard(board);
                if (isSolved(board)) {
                    System.out.println("Congratulations! You solved the puzzle.");
                    break;
                }
            } else {
                System.out.println("Invalid move. Try again.");
            }
        }

        scanner.close();
    }

    private static void printBoard(int[][] board) {
        for (int[] row : board) {
            for (int cell : row) {
                System.out.printf("%2d ", cell);
            }
            System.out.println();
        }
    }

    private static boolean moveNumber(int[][] board, int number) {
        int emptyRow = 0, emptyCol = 0;
        int numberRow = 0, numberCol = 0;

        // Find the empty space and the number position
        boolean foundEmpty = false, foundNumber = false;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 0) {
                    emptyRow = i;
                    emptyCol = j;
                    foundEmpty = true;
                }
                if (board[i][j] == number) {
                    numberRow = i;
                    numberCol = j;
                    foundNumber = true;
                }
                if (foundEmpty && foundNumber) break;
            }
            if (foundEmpty && foundNumber) break;
        }

        // Check if the move is valid
        if (Math.abs(emptyRow - numberRow) + Math.abs(emptyCol - numberCol) == 1) {
            board[emptyRow][emptyCol] = number;
            board[numberRow][numberCol] = 0;
            return true;
        }

        return false;
    }

    private static boolean isSolved(int[][] board) {
        int counter = 1;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (i == board.length - 1 && j == board[i].length - 1) {
                    if (board[i][j] != 0) {
                        return false;
                    }
                } else {
                    if (board[i][j] != counter++) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
