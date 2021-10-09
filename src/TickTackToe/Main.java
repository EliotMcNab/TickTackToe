package TickTackToe;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int boardDimensions = 0;
        int x = 0, y = 0;

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
        Board testBoard = Board.getBoard_Instance(boardDimensions);

        // displays board
        System.out.println(testBoard);
        System.out.println("");

        // while none of the player has won and there are still empty tiles...
        gameLoop : do {

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
                } catch (InputMismatchException e) {
                    // clears the input stream
                    sc.next();
                    // asks the user again for the coordinates
                    continue gameLoop;
                }

            // ...until the coordinates are valid
            } while (x < 0 || x > boardDimensions || y < 0 || y > boardDimensions);

            // places the pawn
            testBoard.play(x, y);

            // displays the board
            System.out.println(testBoard);
            System.out.println("");

        } while (!testBoard.hasPlayerXWon() && !testBoard.hasPlayerOWon() && testBoard.hasSpaceLeft());
    }
}
