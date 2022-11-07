import lib.Input;

public class App {
    public static void main(String[] args) {
        char[][] board = new char[8][8];
        initBoard(board);
        printBoard(board);
        movePawn(board);
        printBoard(board);
        moveRook(board);
        printBoard(board);
    }

    /**
     * prints out the given char Array as a 2D Board
     * @param board
     */
    public static void printBoard(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            System.out.print("|");
            for (int j = 0; j < board.length; j++) {
                System.out.print(board[i][j]+"|");
            }
            System.out.println("");
        }
    }
    /**
     * initilizes a given board to be a Chess Board
     * @param board
     */
    public static void initBoard(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if(i == 0 && j == 0){
                    board[i][0] = 'T';
                    board[i][1] = 'S';
                    board[i][2] = 'L';
                    board[i][3] = 'Q';
                    board[i][4] = 'K';
                    board[i][5] = 'L';
                    board[i][6] = 'S';
                    board[i][7] = 'T';
                }
                if (i == 1) {
                    board[i][j] = 'B';
                }
                if (i == 6) {
                    board[i][j] = 'b';
                }
                if(i == 7 && j == 0){
                    board[i][0] = 't';
                    board[i][1] = 's';
                    board[i][2] = 'l';
                    board[i][3] = 'q';
                    board[i][4] = 'k';
                    board[i][5] = 'l';
                    board[i][6] = 's';
                    board[i][7] = 't';
                }
                if (i > 1 && i < 6) {
                    if(j % 2 == 1){
                        board[i][j] = '.';
                    } else {
                        board[i][j] = ' ';
                    }
                }
            }
        }
    }
    /**
     * A Method to move a Pawn
     * @param board
     */
    public static void movePawn(char[][] board) {
        int userInputPawn = Input.inputInt("What pawn do you want to move?:");
        int userInputPawnMv = Input.inputInt("How many steps to move?:");
        if (userInputPawn % 2 == 1) {
            board[6][userInputPawn] = '.';
        }else{
            board[6][userInputPawn] = ' ';
        }
        if (userInputPawnMv > 1) {
            board[6-2][userInputPawn] = 'b';
        } else {
            board[6-1][userInputPawn] = 'b';
        }
    }
    /**
     * A Method to move a Rook
     * @param board
     */
    public static void moveRook(char[][] board) {
        int userInputRook = Input.inputInt("What Rook do you want to move?:");
        int userInputRookMv = Input.inputInt("To the right = 1 or left = 0?:");
        if (userInputRook == 1 || userInputRook == 6) {
            if (userInputRook % 2 == 1) {
                board[7][userInputRook] = '.';
            }else{
                board[7][userInputRook] = ' ';
            }
            if (userInputRookMv == 1) {
                board[5][userInputRook+1] = 's';
            } else {
                board[5][userInputRook-1] = 's';
            }
        } else {
            System.out.println("Please give a valid index");
        }
    }
}
