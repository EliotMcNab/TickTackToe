package TickTackToe;

import java.util.*;

/**
 * Represents a Tick Tack Toe game
 */
public class TickTackToe extends Board {

    // the integers which represent the players on the board
    protected final int PLAYER_X_KEY;
    protected final int PLAYER_O_KEY;

    // the current turn
    // player X plays on even turns
    // player O plays on odd turns
    protected int turn = 0;

    // the singleton instance of the board
    protected static TickTackToe tickTackToe_Instance = null;

    /**
     * Called only once to create the singleton of the Board class
     * @param dimension the size of the board
     */
    private TickTackToe(int dimension) {

        // assigns the board's dimension
        super(dimension);

        // assigns the player keys to default of
        // X = 1
        // O = 0
        this.PLAYER_X_KEY = 1;
        this.PLAYER_O_KEY = 0;
    }


    /**
     * Called only once to create the singleton of the Board class
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
     * @param PLAYER_O_KEY integer representing player X in the board
     * @param PLAYER_X_KEY integer representing player O in the board
     */
    protected TickTackToe(int dimension, int EMPTY_TILE_KEY, int PLAYER_O_KEY, int PLAYER_X_KEY) {

        super(dimension, EMPTY_TILE_KEY);

        // if the player and empty tile keys the same...
        if (PLAYER_X_KEY == PLAYER_O_KEY || PLAYER_X_KEY == EMPTY_TILE_KEY || PLAYER_O_KEY == EMPTY_TILE_KEY) {
            // throws an error
            throw new IllegalArgumentException("PLayer and empty tile keys cannot be identical");
        }

        // list containing all the valid value for the keys
        List<String> validKeys = new ArrayList<>();
        // valid key values
        // {empty tile key, player O key, player X key}
        validKeys.add(" 0 1");
        validKeys.add("-1 1");
        validKeys.add("1 -1");

        // groups the keys inputted by the user in a string
        String userKeys = String.format("%s %s", PLAYER_O_KEY, PLAYER_X_KEY);
        boolean isNotAValidKey = !validKeys.contains(userKeys);

        // if the keys inputted by the user are not valid
        if (isNotAValidKey) {
            // throws an error
            throw new IllegalArgumentException("Key combination is invalid");
        }

        // assigns the player keys
        this.PLAYER_X_KEY = PLAYER_X_KEY;
        this.PLAYER_O_KEY = PLAYER_O_KEY;
    }

    /**
     * Gets the singleton instance of the Board class
     * @param dimension the size of the board
     * @return the singleton of the Board class
     */
    static TickTackToe getTickTackToe_Instance(int dimension) {
        // if the singleton hasn't been created yet...
        if(tickTackToe_Instance == null) {
            // calls the class constructor
            tickTackToe_Instance = new TickTackToe(dimension);
        }

        // returns the singleton
        return tickTackToe_Instance;
    }

    /**
     * Gets the singleton instance of the Board class
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
     * @param PLAYER_O_KEY integer representing player X in the board
     * @param PLAYER_X_KEY integer representing player O in the board
     * @return the singleton of the Board class
     */
    public static TickTackToe getTickTackToe_Instance(int dimension, int EMPTY_TILE_KEY, int PLAYER_O_KEY, int PLAYER_X_KEY) {
        // if the singleton hasn't been created yet...
        if(tickTackToe_Instance == null) {
            // calls the class constructor
            tickTackToe_Instance = new TickTackToe(dimension, EMPTY_TILE_KEY, PLAYER_O_KEY, PLAYER_X_KEY);
        }

        // returns the singleton
        return tickTackToe_Instance;
    }

    /**
     * moves on to the next turn
     */
    public void nextTurn() {
        turn++;
    }

    /**
     * Resets the turn to 0
     */
    public void resetTurns() {
        turn = 0;
    }

    /**
     * Determines the current player
     * @return the current player in string form
     */
    public String curPlayer() {
        // determines the current player
        return curPlayerTurn() == PLAYER_X_KEY ? "X" : "O";
    }

    /**
     * Determines whose player it is the turn to play
     * <ul>
     *     <li>0 : player O's turn (odd turns)
     *     <li>1 : player X's turn (even turns)
     * </ul>
     * @return the player whose turn it is to play
     */
    private int curPlayerTurn() {
        // it is X player's turn
        if (turn % 2 == 0) {
            return PLAYER_X_KEY;
        }
        // it is O player's turn
        else {
            return PLAYER_O_KEY;
        }
    }

    /**
     * Determines whether a tile on the board is playable (i.e. :if it is empty)
     * @param x the x-coordinates of the tile
     * @param y the y-coordinates of the tile
     * @return whether the tile is playable
     */
    public boolean isPlayableTile(int y, int x) {
        // tries and access the tile
        try {
            // if the tile is empty it is playable
            return getTile(y, x) == EMPTY_TILE_KEY;
        } catch (IndexOutOfBoundsException e) {
            // if there was an error in specifying the coordinates
            // the tile is by default unplayable
            return false;
        }
    }

    /**
     * Converts a tile into string form
     * @param x the x-coordinates of the tile
     * @param y the y-coordinates of the tile
     * @return the tile under string form
     */
    private String convertTile(int y, int x) {

        int tile = getTile(y, x);

        // tile is empty
        if (tile == EMPTY_TILE_KEY) {
            return "_";
        }
        // tile belongs to player O
        else if(tile == PLAYER_O_KEY) {
            return "O";
        }
        // tile belongs to player X
        else if(tile == PLAYER_X_KEY){
            return "X";
        }
        // tile is invalid (does not belong to any player and is not empty)
        else {
            // displays an error message
            throw new IllegalArgumentException("Specified tile is invalid");
        }

        // return Integer.toString(BOARD[y][x]);
    }

    /**
     * Allows the player whose turn it is to place a pawn on the board
     * @param x the x-coordinates of the pawn
     * @param y the y-coordinates of the pawn
     */
    public void play(int x, int y) {
        // if the tile is empty...
        if (isPlayableTile(y, x)){
            // places the corresponding player tile
            super.play(y, x, curPlayerTurn());
            // and moves on to the next turn
            nextTurn();
        }
    }

    @Override
    public boolean hasSpaceLeft(){
        return super.hasSpaceLeft();
    }

    /**
     * Determines whether the player X has made a valid alignment
     * @param winingPlayer the wining player in integer form
     * @return whether player X has won
     */
    public boolean hasPlayerXWon(int winingPlayer) {
        // checks for win conditions for player X
        return winingPlayer == PLAYER_X_KEY;
    }

    /**
     * Determines whether the player O has made a valid alignment
     * @param winingPlayer the wining player in integer form
     * @return whether player O has won
     */
    public boolean hasPlayerOWon(int winingPlayer) {
        // checks for win conditions for player O
        return winingPlayer == PLAYER_O_KEY;
    }

    /**
     * Checks for win conditions for both players
     * <ul>
     *     <li>-1 : no player has won
     *     <li> 0 : player O has won
     *     <li> 1 : player X has won
     * </ul>
     * @return which player has won or if none has won
     */
    public int winingPlayer() {
        // gets wining conditions
        int rowWining =     isWining(       rowScore(true));
        int columnWining =  isWining(    columnScore(true));
        int diag1Wining =   isWining(  downDiagScore(true));
        int diag2Wining =   isWining(    upDiagScore(true));

        // whether X is wining along the rows / columns / upwards or downward diagonals
        boolean XRowWining      = rowWining     == PLAYER_X_KEY;
        boolean XColumnWining   = columnWining  == PLAYER_X_KEY;
        boolean XDiag1Wining    = diag1Wining   == PLAYER_X_KEY;
        boolean XDiag2Wining    = diag2Wining   == PLAYER_X_KEY;

        // whether X is wining along the rows / columns / upwards or downward diagonals
        boolean ORowWining      = rowWining     == PLAYER_O_KEY;
        boolean OColumnWining   = columnWining  == PLAYER_O_KEY;
        boolean ODiag1Wining    = diag1Wining   == PLAYER_O_KEY;
        boolean ODiag2Wining    = diag2Wining   == PLAYER_O_KEY;

        // checks whether player X has won
        if (XRowWining || XColumnWining || XDiag1Wining || XDiag2Wining) {
            return PLAYER_X_KEY;
        }
        // checks whether player O has won
        if (ORowWining || OColumnWining || ODiag1Wining || ODiag2Wining) {
            return PLAYER_O_KEY;
        }

        // no player has won yet
        return EMPTY_TILE_KEY;
    }

    /**
     * Checks whether a player is wining based on his specified score
     * @param score the player's score along the rows / columns / up or down diagonal
     * @return whether a player has won
     */
    private int isWining(int[] score) {

        // gets the maxim score
        Arrays.sort(score);
        final int MAX_SCORE = score[score.length - 1];

        // if player X is wining
        if (MAX_SCORE == BOARD_DIMENSIONS * PLAYER_X_KEY) {
            return PLAYER_X_KEY;
        }
        // if player O is wining
        if (MAX_SCORE == BOARD_DIMENSIONS * PLAYER_O_KEY) {
            return PLAYER_O_KEY;
        }

        // no player has won yet
        return EMPTY_TILE_KEY;
    }


    /**
     * The player's score along the rows
     * @param considerEmpty whether the method considers empty tiles or not
     * @return the score of the player along the rows
     */
    protected int[] rowScore(boolean considerEmpty) {

        // how close the player is to wining on each row
        final int[] ROW_SCORE = new int[BOARD_DIMENSIONS];
        Arrays.fill(ROW_SCORE, 0);

        // verifies there are no empty tiles in the row
        int hasNoEmpty = 1;

        // Check rows for alignment
        for (int i = 0; i < BOARD_DIMENSIONS; i++) {

            // adds up every tile of the row
            for (int tile : getRow(i)) {
                ROW_SCORE[i] += tile != EMPTY_TILE_KEY ? tile : (hasNoEmpty = 0);
            }

            // if the method is set to consider empty tiles
            // and there was an empty tile in the row...
            if (considerEmpty && hasNoEmpty == 0) {
                // sets the row score along that row to be negative
                ROW_SCORE[i] = EMPTY_TILE_KEY;
            }

        }

        // returns the player score along the rows
        return ROW_SCORE;
    }

    /**
     * The player's score along the columns
     * @param considerEmpty whether the method considers empty tiles or not
     * @return the score of the player along the columns
     */
    protected int[] columnScore(boolean considerEmpty) {

        // how close the player is to wining on each column
        final int[] COLUMN_SCORE = new int[BOARD_DIMENSIONS];
        Arrays.fill(COLUMN_SCORE, 0);

        // verifies there are no empty tiles in the column
        int hasNoEmpty = 1;

        // checks columns for alignment
        for (int i = 0; i < BOARD_DIMENSIONS; i++) {

            // adds up every tile in the column, ignoring empty tiles
            for (int[] row: getBoard()) {
                COLUMN_SCORE[i] += row[i] != EMPTY_TILE_KEY ? row[i] : (hasNoEmpty = 0);
            }

            // if the method is set to consider empty tiles
            // and there was an empty tile in the row...
            if (considerEmpty && hasNoEmpty == 0) {
                // sets the column score along that column to be negative
                COLUMN_SCORE[i] = EMPTY_TILE_KEY;
            }

        }

        // returns the player score along the columns
        return COLUMN_SCORE;
    }

    /**
     * The player's score along the downwards diagonal
     * @param considerEmpty whether the method considers empty tiles or not
     * @return the score of the player along the downwards diagonal
     */
    protected int[] downDiagScore(boolean considerEmpty) {

        // how close the player is to wining along the diagonals
        final int[] DIAGONAL_SCORE = new int[1];

        // verifies there are no empty tiles in downward diagonal
        int hasNoEmpty = 1;

        // adds up every tile in the downwards diagonal
        for (int i = 0; i < BOARD_DIMENSIONS; i++) {
            // geste the tile
            int curTile = getTile(i,i);

            // checks it isn't empty
            DIAGONAL_SCORE[0] += curTile != EMPTY_TILE_KEY ? curTile : (hasNoEmpty = 0);
        }

        // if the method is set to consider empty tiles
        // and there is an empty tile in the diagonal
        if (considerEmpty && hasNoEmpty == 0) {
            // sets the diagonal score to be negative
            DIAGONAL_SCORE[0] = EMPTY_TILE_KEY;
        }

        return DIAGONAL_SCORE;
    }

    /**
     * The player's score along the upwards diagonal
     * @param considerEmpty whether the method considers empty tiles or not
     * @return the score of the player along the upwards diagonal
     */
    protected int[] upDiagScore(boolean considerEmpty) {

        // how close the player is to wining along the upwards diagonal
        final int[] DIAGONAL_SCORE = new int[1];

        // verifies that there are no empty tiles along the diagonal
        int hasNoEmpty = 1;

        // adds up every tile in the upwards diagonal
        for (int i = 0; i < BOARD_DIMENSIONS; i++) {

            // gets the current tile
            int curTile = getTile(i, BOARD_DIMENSIONS - 1 -i);
            // if it is not empty, adds it to the diagonal score
            DIAGONAL_SCORE[0] += curTile != EMPTY_TILE_KEY ? curTile : (hasNoEmpty = 0);

        }

        // if the method is set to consider empty tiles
        // and there is an empty tile along the diagonal...
        if (considerEmpty && hasNoEmpty == 0) {
            // sets the diagonal score to be negative
            DIAGONAL_SCORE[0] = EMPTY_TILE_KEY;
        }

        // returns the player's score along the downwards diagonal
        return DIAGONAL_SCORE;
    }

    /**
     * Used to specify conversion of the board to a string
     * @return the board under string format
     */
    @Override
    public String toString() {
        // a matrix containing each row of the board under string form
        String[] boardStringRows = new String[BOARD_DIMENSIONS];
        // initialises the value of each row
        Arrays.fill(boardStringRows, "");

        // the final representation of the board in string form
        StringBuilder boardStringFinal = new StringBuilder();

        // converts each row to a string
        for (int i = 0; i < BOARD_DIMENSIONS; i++) {
            for (int j = 0; j < BOARD_DIMENSIONS; j++) {
                // gets the values of each tile and separates them
                boardStringRows[i] += convertTile(i, j) + " ";
            }
        }

        // adds a space between each row
        for (int i = 0; i < boardStringRows.length -1; i++) {
            boardStringFinal.append(boardStringRows[i]).append("\n");
        }
        // does not add a space after the last row
        boardStringFinal.append(boardStringRows[boardStringRows.length - 1]);

        // returns the formatted result
        return boardStringFinal.toString();
    }
}
