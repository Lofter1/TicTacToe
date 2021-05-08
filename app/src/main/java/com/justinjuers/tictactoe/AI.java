package com.justinjuers.tictactoe;

public class AI {
    private int bestMoveRow;
    private int bestMoveColumn;

    public int[] makeMove(Board board){
        int[][] boardState = board.getBoardState();

        minimaxMaximizing(boardState, 0);

        board.move(bestMoveRow, bestMoveColumn, FieldValueEnum.O);

        return new int[]{bestMoveRow, bestMoveColumn};
    }

    // Maximizing minimax step aka the AI
    private int minimaxMaximizing(int[][] board, int depth){
        int eval = Board.evaluate(board);
        if (eval != FieldValueEnum.None || Board.countSigns(board)==9)
            return eval;

        int maxScore = Integer.MIN_VALUE;

        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 3; column++){
                if (board[row][column] != FieldValueEnum.None)
                    continue;

                board[row][column] = FieldValueEnum.O;    // make move
                int score = minimaxMinimizing(board, depth+1);

                if (score > maxScore) {
                    maxScore = score;
                    if (depth==0) { // saveBestMove
                        bestMoveRow = row;
                        bestMoveColumn = column;
                    }
                }
                board[row][column] = FieldValueEnum.None;   // Reset turn
            }
        }
        return maxScore;
    }

    // Minimizing minimax step, aka theoretical player
    private int minimaxMinimizing(int[][] board, int depth){
        int eval = Board.evaluate(board);
        if (eval != FieldValueEnum.None || Board.countSigns(board)==9)
            return eval;

        int minScore = Integer.MAX_VALUE;

        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 3; column++){
                if (board[row][column] != FieldValueEnum.None)
                    continue;

                board[row][column] = FieldValueEnum.X;  //make move
                int score = minimaxMaximizing(board, depth+1);

                if (score < minScore) {
                    minScore = score;
                    if (depth==0) { // saveBestMove
                        bestMoveRow = row;
                        bestMoveColumn = column;
                    }
                }
                board[row][column] = FieldValueEnum.None;   // Reset turn
            }
        }

        return minScore;
    }
}
