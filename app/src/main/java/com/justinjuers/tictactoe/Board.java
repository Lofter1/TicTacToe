package com.justinjuers.tictactoe;

public class Board {
    private final int[][] state = new int[3][3];

    public int[][] getBoardState(){
        return state.clone();
    }

    public boolean move(int row, int column, int fieldValue) {
        if ((row >= 0 && row < 3) &&
                (column >= 0 && column < 3) &&
                this.state[row][column] == FieldValueEnum.None){
            this.state[row][column] = fieldValue;
            return true;
        }
        return false;
    }

    /**
     * @param board 2 dimensional int array representing the board to evaluate
     * @return If game is over, FieldValueEnum of Winner, otherwise FieldValueEnum.None
     */
    public static int evaluate(int[][] board){

        int sum, sum2;
        for (int i=0; i < 3; i++) {
            sum  = board[i][0] + board[i][1] + board[i][2];
            sum2 = board[0][i] + board[1][i] + board[2][i];

            if ((sum == FieldValueEnum.X*3)||(sum2 == FieldValueEnum.X*3)) {
                return FieldValueEnum.X;
            }
            else if ((sum == FieldValueEnum.O*3)||(sum2 == FieldValueEnum.O*3)) {
                return  FieldValueEnum.O;
            }
        }

        sum  = board[0][0] + board[1][1] + board[2][2];
        sum2 = board[0][2] + board[1][1] + board[2][0];

        if((sum == FieldValueEnum.O*3)||(sum2 == FieldValueEnum.O*3)) {
            return FieldValueEnum.O;
        }
        else if((sum == FieldValueEnum.X*3)||(sum2 == FieldValueEnum.X*3)) {
            return FieldValueEnum.X;
        }

        return FieldValueEnum.None;
    }

    public static int countSigns(int[][] board) {
        int count=0;
        for (int i=0; i < 3; i++){
            for (int j=0; j < 3; j++){
                if (board[i][j]!=FieldValueEnum.None) {
                    count++;
                }
            }
        }

        return count;
    }

    public void reset() {
        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 3; column++) {
                state[row][column] = FieldValueEnum.None;
            }
        }
    }
}
