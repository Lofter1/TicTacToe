package com.justinjuers.tictactoe;

public class TicTacToe {
    private final Board board;
    private final AI ai;

    private int valueOfCurrentPlayer;

    public TicTacToe(){
        board = new Board();
        ai = new AI();
        valueOfCurrentPlayer = FieldValueEnum.X;
    }

    public boolean isFinished(){
        return (Board.evaluate(board.getBoardState()) != FieldValueEnum.None) ||
                (Board.countSigns(board.getBoardState()) == 9);
    }

    public boolean makeMove(int row, int column, int playerValue){
        if(valueOfCurrentPlayer == playerValue){
            valueOfCurrentPlayer = -valueOfCurrentPlayer; // Switch player
            return board.move(row, column, playerValue);
        }
        return false;
    }

    public int[] makeAiMove(){
        valueOfCurrentPlayer = -valueOfCurrentPlayer;
        return ai.makeMove(board);
    }

    public int getWinner() {
        return Board.evaluate(board.getBoardState());
    }

    public void reset(){
        valueOfCurrentPlayer = FieldValueEnum.X;
        board.reset();
    }
}
