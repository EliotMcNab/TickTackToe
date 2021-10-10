package TickTackToe;

import java.util.Arrays;

public class AI extends Board{

    private static AI ai_Instance;

    /**
     * <h2>AI Constructor</h2>
     * <p>Called only once to create the singleton of the Board class</p>
     *
     * @param dimension the size of the board
     */
    protected AI(int dimension) {
        super(dimension);

    }

    public static AI getAiInstance(int dimension) {
        if (ai_Instance == null) {
            ai_Instance = new AI(dimension);
        }

        return ai_Instance;
    }
}
