package TickTackToe;

import java.util.Arrays;

/**
 * <h1>Board</h1>
 * <p>Represents a Tick Tack Toe board</p>
 */
class Board {

    // the board in matrix form
    // -1 = empty tile
    // 0 = O player pawn
    // 1 = X player pawn
    private final int[][] board;
    // the current turn
    // player X plays on even turns
    // player O plays on odd turns
    private int turn = 0;
    // the width and height of the board
    private final int BOARD_DIMENSIONS;

    // the singleton instance of the board
    private static Board board_Instance = null;

    /**
     * <h2>Board Constructor</h2>
     * <p>Called only once to create the singleton of the Board class</p>
     * @param dimension the size of the board
     */
    private Board(int dimension) {

        // assigns the board's dimension
        this.BOARD_DIMENSIONS = dimension;

        // creates the board
        board = new int[BOARD_DIMENSIONS][BOARD_DIMENSIONS];
        // populates the board with empty tiles
        for (int[] row : board) {
            Arrays.fill(row, -1);
        }
    }

    /**
     * <h2>getBoardInstance</h2>
     * @param dimension the size of the board
     * @return the singleton of the Board class
     */
    static Board getBoard_Instance(int dimension) {
        // if the singleton hasn't been created yet...
        if(board_Instance == null) {
            // calls the class constructor
            board_Instance = new Board(dimension);
        }

        // returns the singleton
        return  board_Instance;
    }

    /**
     * <h2>nextTurn</h2>
     * <p>moves on to the next turn</p>
     */
    public void nextTurn() {
        turn++;
    }

    /**
     * <h2>curPlayer</h2>
     * <p>Determines the current player to display him under string form</p>
     * @return the current player in string form
     */
    public String curPlayer() {
        // determines the current player
        return curPlayerTurn() == 1 ? "X" : "O";
    }

    /**
     * <h2>curPlayerTurn</h2>
     * <p>Determines whose player it is the turn to play</p>
     * @return
     */
    private int curPlayerTurn() {
        // it is X player's turn
        if (turn % 2 == 0) {
            return 1;
        }
        // it is O player's turn
        else {
            return  0;
        }
    }

    /**
     * <h2>isPLayableTile</h2>
     * <p>Determines whether or not a tile on the board is playable (if it is empty)</p>
     * @param x the x-coordinates of the tile
     * @param y the y-coordinates of the tile
     * @return wether or not the tile is playable
     */
    public boolean isPlayableTile(int x, int y) {
        // tries and access the tile
        try {
            // if the tile is empty it is playable
            return board[x][y] == -1;
        } catch (IndexOutOfBoundsException e) {
            // if there was an error in specifying the coordinates
            // the tile is by default unplayable
            return false;
        }
    }

    /**
     * <h2>convertTile</h2>
     * <p>Converts a tile into string form</p>
     * @param x the x-coordinates of the tile
     * @param y the y-coordinates of the tile
     * @return the tile under string form
     */
    private String convertTile(int x, int y) {

        // tile is empty
        if (board[y][x] == -1) {
            return "_";
        }
        // tile belongs to player O
        else if(board[y][x] == 0) {
            return "O";
        }
        // tile belongs to player X
        else if(board[y][x] == 1){
            return "X";
        }
        // tile is invalid (does not belong to any player and is not empty)
        else {
            // displays an error message
            throw new IllegalArgumentException("Specified tile is invalid");
        }
    }

    /**
     * <h2>play</h2>
     * <p>Allows the player whose turn it is to place a pawn on the board</p>
     * @param x the x-coordinates of the pawn
     * @param y the y-coordinates of the pawn
     */
    public void play(int x, int y) {
        // if the tile is empty...
        if (isPlayableTile(x, y)){
            // places the corresponding player tile
            board[x][y] = curPlayerTurn();
            // and moves on to the next turn
            nextTurn();
        }
    }

    /**
     * <h2>hasPlayerXWon</h2>
     * <p>Determines whether or not the player X has made a valid alignment</p>
     * @return whether player X has won
     */
    public boolean hasPlayerXWon() {
        // checks for win conditions for player X
        return winCondition() == 1;
    }

    /**
     * <h2>hasPlayerOWon</h2>
     * <p>Determines whether or not the player O has made a valid alignment</p>
     * @return whether player O has won
     */
    public boolean hasPlayerOWon() {
        // checks for win conditions for player O
        return winCondition() == 0;
    }

    /**
     * <h2>hasSpaceLeft</h2>
     * <p>Checks the board for any empty tiles</p>
     * @return whether the board still has empty tiles
     */
    public boolean hasSpaceLeft() {
        // loops through each row of the board...
        for (int[] row : board) {
            // looks through each tile of that row...
            for (int tile : row) {
                // if the tile contains a -1, it is empty
                if (tile == -1) {
                    return true;
                }
            }
        }

        // the board has no empty tiles left
        return false;
    }

    /**
     * <h2>winCondition</h2>
     * <p>Checks for win conditions for both players</p>
     * <ul>
     *     <li>-1 : no player has won</li>
     *     <li> 0 : player O has won</li>
     *     <li> 1 : player X has won</li>
     * </ul>
     * @return which player has won or if none has won
     */
    private int winCondition() {
        // gets wining conditions
        int rowWining =rowWiningCondition();
        int columnWining = columnWinConditions();
        int diag1Wining = downwardDiagonalWinConditions();
        int diag2Wining = upwardDiagonalWinConditions();

        // checks whether player X has won
        if ((rowWining == 1) || (columnWining == 1) || (diag1Wining == 1) || (diag2Wining == 1)) {
            return 1;
        }
        // checks whether player O has won
        if ((rowWining == 0) || (columnWining == 0) || (diag1Wining == 0) || (diag2Wining == 0)) {
            return 0;
        }

        // no player has won yet
        return -1;
    }

    /**
     * <h2>rowWiningCondition</h2>
     * <p>Checks weather a player has won along the rows</p>
     * @return which player has won or if none has won
     */
    private int rowWiningCondition() {
        // Check rows for alignment
        for (int[] row : board) {

            // resets the row sum for each new row
            int rowSum = 0;
            // verifies that no empty tiles are counted in the sum
            int hasNoEmpty = 1;

            // adds up every tile of the row
            for (int tile: row) {
                rowSum = tile != -1 ? rowSum + tile : (hasNoEmpty = 0);
            }

            // checks whether player X has won
            if (rowSum == BOARD_DIMENSIONS) {
                return 1;
            }
            // checks whether player O has won
            if (rowSum == 0 && hasNoEmpty != 0) {
                return 0;
            }
        }

        // no player has won yet
        return -1;
    }

    /**
     * <h2>rowWiningCondition</h2>
     * <p>Checks weather a player has won along the columns</p>
     * @return which player has won or if none has won
     */
    private int columnWinConditions() {
        // checks columns for alignment
        for (int i = 0; i < board[0].length; i++) {

            // resets the column sum for each new column
            int columnSum = 0;
            // verifies that no empty tiles are counted in the sum
            int hasNoEmpty = 1;

            // adds up every tile in the column
            for (int[] row: board) {
                columnSum = row[i] != -1 ? columnSum + row[i] : (hasNoEmpty = 0);
            }

            // checks whether player X has won
            if (columnSum == BOARD_DIMENSIONS) {
                return 1;
            }
            // checks whether player O has won
            if (columnSum == 0 && hasNoEmpty != 0) {
                return 1;
            }
        }

        // no player has won yet
        return -1;
    }

    /**
     * <h2>rowWiningCondition</h2>
     * <p>Checks weather a player has won along the downward diagonal</p>
     * @return which player has won or if none has won
     */
    private int downwardDiagonalWinConditions() {
        // checks downward diagonal for alignment
        int diagonalSumDown = 0;
        // verifies that no empty tiles are counted in the sum
        int hasNoEmpty = 1;

        // adds up every tile in the downwards diagonal
        for (int i = 0; i < BOARD_DIMENSIONS; i++) {
            diagonalSumDown += board[i][i];
            hasNoEmpty = board[i][i] == -1 ? 0 : 1;
        }
        // checks whether player X has won
        if (diagonalSumDown == BOARD_DIMENSIONS) {
            return 1;
        }
        // checks whether player O has won
        if (diagonalSumDown == 0 && hasNoEmpty != 0){
            return 0;
        }

        // no player has won yet
        return -1;
    }

    /**
     * <h2>rowWiningCondition</h2>
     * <p>Checks weather a player has won along the upward diagonal</p>
     * @return which player has won or if none has won
     */
    private int upwardDiagonalWinConditions() {

        // checks upward diagonal for alignment
        int diagonalSumUp = 0;
        // verifies that no empty tiles are counted in the sum
        int hasNoEmpty = 1;

        // adds up every tile in the downwards diagonal
        for (int i = 0; i < BOARD_DIMENSIONS; i++) {
            diagonalSumUp += board[i][BOARD_DIMENSIONS - 1 - i];
            hasNoEmpty = board[i][BOARD_DIMENSIONS - 1 - i]== -1 ? 0 : 1;
        }
        // checks whether player X has won
        if (diagonalSumUp == BOARD_DIMENSIONS) {
            return 1;
        }
        // checks whether player O has won
        if (diagonalSumUp == 0 && hasNoEmpty != 0){
            return 0;
        }

        // no player has won yet
        return -1;
    }

    /**
     * <h2>toString</h2>
     * <p>Used to specify conversion of the board to a string</p>
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
            for (int j = 0; j < board[0].length; j++) {
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
