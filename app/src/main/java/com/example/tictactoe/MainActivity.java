package com.example.tictactoe;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button[][] buttons = new Button[3][3];
    private boolean playerXTurn = true;
    private TextView resultText;
    private int turnCount = 0;
    private boolean gameOver = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultText = findViewById(R.id.resultText);
        initializeGrid();


        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (!gameOver) {
                            onButtonClick(view);
                        }
                    }
                });
            }
        }


        Button resetButton = findViewById(R.id.resetButton);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGame();
            }
        });
    }


    private void initializeGrid() {
        buttons[0][0] = findViewById(R.id.button1);
        buttons[0][1] = findViewById(R.id.button2);
        buttons[0][2] = findViewById(R.id.button3);
        buttons[1][0] = findViewById(R.id.button4);
        buttons[1][1] = findViewById(R.id.button5);
        buttons[1][2] = findViewById(R.id.button6);
        buttons[2][0] = findViewById(R.id.button7);
        buttons[2][1] = findViewById(R.id.button8);
        buttons[2][2] = findViewById(R.id.button9);
    }


    private void onButtonClick(View view) {
        Button clickedButton = (Button) view;


        if (!clickedButton.getText().toString().equals("")) {
            return;
        }


        if (playerXTurn) {
            clickedButton.setText("X");
            clickedButton.setTextSize(60);
        } else {
            clickedButton.setText("O");
            clickedButton.setTextSize(60);
        }

        turnCount++;


        if (checkForWinner()) {
            resultText.setText(playerXTurn ? "Player X Wins!" : "Player O Wins!");
            gameOver = true;
            disableButtons();
        } else if (turnCount == 9) {
            resultText.setText("It's a Draw!");
            gameOver = true;
            disableButtons();
        } else {

            playerXTurn = !playerXTurn;
            resultText.setText(playerXTurn ? "Player X's Turn" : "Player O's Turn");
        }
    }


    private boolean checkForWinner() {

        for (int row = 0; row < 3; row++) {
            if (buttons[row][0].getText().equals(buttons[row][1].getText()) &&
                    buttons[row][1].getText().equals(buttons[row][2].getText()) &&
                    !buttons[row][0].getText().toString().equals("")) {
                return true;
            }
        }

        for (int col = 0; col < 3; col++) {
            if (buttons[0][col].getText().equals(buttons[1][col].getText()) &&
                    buttons[1][col].getText().equals(buttons[2][col].getText()) &&
                    !buttons[0][col].getText().toString().equals("")) {
                return true;
            }
        }

        if (buttons[0][0].getText().equals(buttons[1][1].getText()) &&
                buttons[1][1].getText().equals(buttons[2][2].getText()) &&
                !buttons[0][0].getText().toString().equals("")) {
            return true;
        }

        if (buttons[0][2].getText().equals(buttons[1][1].getText()) &&
                buttons[1][1].getText().equals(buttons[2][0].getText()) &&
                !buttons[0][2].getText().toString().equals("")) {
            return true;
        }

        return false;
    }


    private void disableButtons() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col].setEnabled(false);
            }
        }
    }


    private void resetGame() {

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col].setText("");
                buttons[row][col].setEnabled(true);
            }
        }


        playerXTurn = true;
        turnCount = 0;
        gameOver = false;


        resultText.setText("Player X's Turn");
    }
}
