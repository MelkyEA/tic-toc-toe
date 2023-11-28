import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.println("Player 1, whats your name?");
        String playerOneName = in.nextLine();
        System.out.println("Player 2, whats your name?");
        String playerTwoName = in.nextLine();

        char[][] board = new char[3][3];

        for(int i = 0; i < board.length; i++){
            for (int j = 0; j < board[i].length; j++){
                board[i][j] = '-';
            }
        }

        boolean isPlayerOne = true;
        boolean isGameEnded = false;
        drawBoard(board);

        while (!isGameEnded) {
            char symbol = isPlayerOne ? 'x' : 'o';

            int row, column;

            System.out.println((isPlayerOne ? playerOneName : playerTwoName) + "'s turn:");

            while (true) {
                System.out.println("Enter a row (1, 2 or 3): ");
                row = in.nextInt() - 1;

                if (row > 2 || row < 0) {
                    System.out.println("Row are out of bounds");
                    continue;
                }

                System.out.println("Enter a column (1, 2 or 3): ");
                column = in.nextInt() - 1;

                if (column > 2 || column < 0) {
                    System.out.println("Column are out of bounds");
                    continue;
                }

                if (board[row][column] != '-') {
                    System.out.println("Someone has already made a move there");
                    continue;
                }
                break;
            }

            board[row][column] = symbol;
            drawBoard(board);

            if (hasTie(board)) {
                System.out.println("Its a tie!");
                isGameEnded = true;
            } else {
                char winSymbol = hasWon(board);
                if (winSymbol != '-') {
                    System.out.println((winSymbol == 'x' ? playerOneName : playerTwoName) + " has won!");
                    isGameEnded = true;
                }
            }

            isPlayerOne = !isPlayerOne;
        }

    }

    private static void drawBoard(char[][] board) {
        for(int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
    }

    private static char hasWon(char[][] board){
        for(int i = 0; i < board.length; i++) {
            if(board[i][0] != '-' && board[i][0] == board[i][1] && board[i][1] == board[i][2]){
                return board[i][0];
            }
        }

        for(int j = 0; j < board[0].length; j++) {
            if(board[0][j] != '-' && board[0][j] == board[1][j] && board[1][j] == board[2][j]) {
                return board[0][j];
            }
        }

        if(board[0][0] != '-' && board[0][0] == board[1][1] && board[1][1] == board[2][2]){
            return board[0][0];
        }

        if(board[0][2] != '-' && board[0][2] == board[1][1] && board[1][1] == board[2][0]){
            return board[0][2];
        }

        return '-';
    }

    private static boolean hasTie(char[][] board){
        for(int i = 0; i < board.length; i++){
            for (int j = 0; j < board[i].length; j++){
                if(board[i][j] == '-')
                    return false;
            }
        }
        return true;
    }
}
