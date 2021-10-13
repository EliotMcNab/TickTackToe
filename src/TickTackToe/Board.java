package TickTackToe;

import java.util.*;

/**
 * Represents a Tick Tack Toe Board
 */
public class Board {

    // the board in matrix form
    // BY DEFAULT
    // -1 = empty tile
    // 0 = O player pawn
    // 1 = X player pawn
    protected final int[][] BOARD;

    // the integers which represent the empty tiles on the board
    protected final int EMPTY_TILE_KEY;

    // the width and height of the board
    protected final int BOARD_DIMENSIONS;

    /**
     * Gets the empty tile key
     * @return the empty tile key
     */
    public int getEMPTY_TILE_KEY() {
        return EMPTY_TILE_KEY;
    }

    /**
     * Gets the board dimensions
     * @return the board dimensions
     */
    public int getBOARD_DIMENSIONS() {
        return BOARD_DIMENSIONS;
    }

    /**
     * Creates a new instance of the Board class
     * @param dimension the size of the board
     */
    Board(int dimension) {

        // assigns the board's dimension
        this.BOARD_DIMENSIONS = dimension;

        // assign the empty tile key to default of
        // empty = -1
        this.EMPTY_TILE_KEY = -1;

        // creates the board
        BOARD = new int[BOARD_DIMENSIONS][BOARD_DIMENSIONS];
        // populates the board with empty tiles
        for (int[] row : BOARD) {
            Arrays.fill(row, -1);
        }

    }

    /**
     * Creates a new instance of the Board class
     * <br>
     * Valid Key Combination :
     * <br>
     * {empty tile key, player O key, player X key}
     * <ul>
     *      <li>-1, 0, 1
     *      <li>0, -1, 1
     *      <li>0, 1, -1
     * </ul>
     * @param dimension the size of the board
     * @param EMPTY_TILE_KEY integer representing empty tiles in the board
     */
    Board(int dimension, int EMPTY_TILE_KEY) {

        // assigns the board's dimension
        this.BOARD_DIMENSIONS = dimension;

        // list containing all the valid value for the keys
        int[] validKeys = {-1, 0};

        // determines whether the key inputted by the user is valid
        boolean keyIsNotValid = !(Arrays.binarySearch(validKeys, EMPTY_TILE_KEY) > 0);

        // if the keys inputted by the user are not valid
        if (keyIsNotValid) {
            // throws an error
            throw new IllegalArgumentException("Key combination is invalid");
        }

        // assigns the empty tile key
        this.EMPTY_TILE_KEY = EMPTY_TILE_KEY;

        // creates the board
        BOARD = new int[BOARD_DIMENSIONS][BOARD_DIMENSIONS];
        // populates the board with empty tiles
        for (int[] row : BOARD) {
            Arrays.fill(row, EMPTY_TILE_KEY);
        }
    }

    /**
     * Places a pawn on the specified tile
     * @param x x-coordinates of the pawn
     * @param y y-coordinates of the pawn
     * @param pawn integer to represent the pawn on the board
     */
    void play(int y, int x, int pawn) {

        // tries and place a pawn onto the board
        try {
            // places the pawn at the specified coordinates
            BOARD[y][x] = pawn;
        } catch (IndexOutOfBoundsException e) { // if the coordinates are invalid...

            // creates a new error message
            String errorMessage = String.format("x and y coordinates of pawn must be comprised between 0 and %s",
                                                BOARD_DIMENSIONS - 1);

            // warns the user that the x and y arguments are invalid
            throw new IllegalArgumentException(errorMessage);
        }
    }

    /**
     * Gets the entire board
     * @return the board
     */
    int[][] getBoard() {
        return BOARD;
    }

    /**
     * Gets the entire specified row
     * @param y the y-coordinate of the row
     * @return the row
     */
    int[] getRow(int y){
        return BOARD[y];
    }

    /**
     * Gets the specified tile
     * @param x the x-coordinate of the tile
     * @param y the y-coordinate of the tile
     * @return the tile
     */
    int getTile(int y, int x) {

        // tries and get the value of the specified tile
        try {
            // gets the value of the tiles
            return BOARD[y][x];
        } catch (IndexOutOfBoundsException e) { // if the coordinates are invalid...

            // creates a new error message
            String errorMessage = String.format("x and y coordinates of pawn must be comprised between 0 and %s",
                    BOARD_DIMENSIONS - 1);

            // warns the user that the x and y arguments are invalid
            throw new IllegalArgumentException(errorMessage);
        }
    }

    /**
     * Checks the board for any empty tiles
     * @return whether the board still has empty tiles
     */
    boolean hasSpaceLeft() {
        // loops through each row of the board...
        for (int[] row : BOARD) {
            // looks through each tile of that row...
            for (int tile : row) {
                // if the tile contains the empty tile key, it is empty
                if (tile == EMPTY_TILE_KEY) {
                    return true;
                }
            }
        }

        // the board has no empty tiles left
        return false;
    }
}
