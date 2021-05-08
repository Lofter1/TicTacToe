package com.justinjuers.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private final TicTacToe game = new TicTacToe();
    private final Button[][] buttons = new Button[3][3];
    private TextView winnerLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        winnerLabel = findViewById(R.id.winnerLabel);

        buttons[0][0] = findViewById(R.id.field00);
        buttons[0][1] = findViewById(R.id.field01);
        buttons[0][2] = findViewById(R.id.field02);
        buttons[1][0] = findViewById(R.id.field10);
        buttons[1][1] = findViewById(R.id.field11);
        buttons[1][2] = findViewById(R.id.field12);
        buttons[2][0] = findViewById(R.id.field20);
        buttons[2][1] = findViewById(R.id.field21);
        buttons[2][2] = findViewById(R.id.field22);
    }



    public void setField00(View view) {
        this.makeMove(0,0);
    }
    public void setField01(View view) {
        this.makeMove(0,1);
    }
    public void setField02(View view) {
        this.makeMove(0,2);
    }
    public void setField10(View view) {
        this.makeMove(1,0);
    }
    public void setField11(View view) {
        this.makeMove(1,1);
    }
    public void setField12(View view) {
        this.makeMove(1,2);
    }
    public void setField20(View view) {
        this.makeMove(2,0);
    }
    public void setField21(View view) {
        this.makeMove(2,1);
    }
    public void setField22(View view) {
        this.makeMove(2,2);
    }

    private void makeMove(int row, int column){
        if(game.makeMove(row,column, FieldValueEnum.X)){
            setButton(buttons[row][column], FieldValueEnum.X);
        }

        if(game.isFinished()){
            this.finnishGame();
        }

        int[] moveCoordinates = game.makeAiMove();
        Button buttonToSet = buttons[moveCoordinates[0]][moveCoordinates[1]];
        setButton(buttonToSet, FieldValueEnum.O);

        if(game.isFinished()){
            this.finnishGame();
        }
    }

    private void setButton(Button buttonToSet, int fieldValue){
        if (fieldValue == FieldValueEnum.X){
            buttonToSet.setText("X");
            buttonToSet.setEnabled(false);
        }
        else if(fieldValue == FieldValueEnum.O){
            buttonToSet.setText("O");
            buttonToSet.setEnabled(false);
        }
        else {
            buttonToSet.setText("");
            buttonToSet.setEnabled(true);
        }
    }

    private void finnishGame(){
        switch(game.getWinner()){
            case FieldValueEnum.None:
                winnerLabel.setText(R.string.tie);
                break;
            case FieldValueEnum.O:
                winnerLabel.setText(R.string.computerWon);
                break;
            case FieldValueEnum.X:
                winnerLabel.setText(R.string.playerWon);
                break;
        }
    }

    public void resetGame(View view) {
        game.reset();
        for(int row = 0; row < 3; row++){
            for(int column = 0; column < 3; column++){
                setButton(buttons[row][column], FieldValueEnum.None);
            }
        }
        winnerLabel.setText("");
    }
}