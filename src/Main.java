import TickTackToe.TickTackToe;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int boardDimensions = 0;
        int x = -1, y = -1;
        int winingPlayer;

        final int EMPTY_TILE_KEY = 0;
        final int PLAYER_O_KEY   = -1;
        final int PLAYER_X_KEY   = 1;

        // while the user hasn't enter correct board dimensions (>= 3)...
        do {
            // tries and get board dimensions
            try {
                // asks user for board dimensions
                System.out.print("Enter board dimensions : ");
                boardDimensions = sc.nextInt();
            } catch (InputMismatchException e) {
                // clears inout stream
                sc.next();
            }
        } while (boardDimensions < 3);

        // creates singleton instance of board
        TickTackToe testBoard = TickTackToe.getTickTackToe_Instance(boardDimensions, EMPTY_TILE_KEY, PLAYER_O_KEY, PLAYER_X_KEY);

        // displays board
        System.out.println(testBoard);
        System.out.println();

        // loop conditions
        boolean playerXHasWon;
        boolean playerOHasWon;
        boolean isSpaceLeft;

        // while none of the player has won and there are still empty tiles...
        do {

            // asks the user for the pawn's coordinates...
            do {
                // asks the current player to place a pawn
                System.out.println("It is " + testBoard.curPlayer() + "'s turn!");
                System.out.println("Place a pawn at coordinates");

                // tries and get the pawn coordinates
                try {
                    // gets the x coordinates
                    System.out.print("x : ");
                    x = sc.nextInt();
                    // gets the y coordinates
                    System.out.print("y : ");
                    y = sc.nextInt();
                } catch (InputMismatchException e) { // if user input is not an integer
                    // clears the input stream
                    sc.next();
                }

            // ...until the coordinates are valid
            } while (x < 0 || x > boardDimensions || y < 0 || y > boardDimensions);

            // places the pawn
            testBoard.play(x, y);

            // displays the board
            System.out.println(testBoard);
            System.out.println();

            // determines the currently wining player
            winingPlayer = testBoard.winingPlayer();

            // determines loop conditions
            playerXHasWon = testBoard.hasPlayerXWon(winingPlayer);
            playerOHasWon = testBoard.hasPlayerOWon(winingPlayer);
            isSpaceLeft = testBoard.hasSpaceLeft();

        } while (!playerXHasWon && !playerOHasWon && isSpaceLeft);
    }
}
