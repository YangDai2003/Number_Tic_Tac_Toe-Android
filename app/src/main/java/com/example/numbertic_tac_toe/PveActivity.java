package com.example.numbertic_tac_toe;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.numbertic_tac_toe.tictactoe.Field;
import com.example.numbertic_tac_toe.tictactoe.Move;
import com.example.numbertic_tac_toe.tictactoe.PenguAI;
import com.example.numbertic_tac_toe.tictactoe.ai.Human;
import com.example.numbertic_tac_toe.tictactoe.ai.SimpleAI;
import com.google.android.material.color.DynamicColors;
import com.google.android.material.elevation.SurfaceColors;

import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PveActivity extends AppCompatActivity {
    protected Field[][] board;
    protected boolean[] firstPlayedPieces;
    protected boolean[] secondPlayedPieces;
    private PenguAI first;
    private PenguAI second;
    private PenguAI winner;
    Random random = new Random();
    private final int a = random.nextInt(2);
    private AlertDialog alertDialog;
    private String chooseNum = "";
    private int x = 0;
    private int y = 0;
    private boolean end = false;
    private int player = 1;
    private boolean can1 = true;
    private boolean can2 = true;
    private boolean chosen = false;

    public void initButtons() {
        Button button0 = findViewById(R.id.button0);
        button0.setOnClickListener(view -> {
            if (chosen && !end) {
                x = 0;
                y = 0;
                button0.setText(chooseNum);
                button0.setTextColor(getColor(R.color.blue));
                playGame();
                if (end) {
                    getResult();
                } else {
                    playGame();
                    if (end) getResult();
                }
                chosen = false;
            }
        });
        Button button1 = findViewById(R.id.button1);
        button1.setOnClickListener(view -> {
            if (chosen && !end) {
                x = 0;
                y = 1;
                button1.setText(chooseNum);
                button1.setTextColor(getColor(R.color.blue));
                playGame();
                if (end) {
                    getResult();
                } else {
                    playGame();
                    if (end) getResult();
                }
                chosen = false;
            }
        });
        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(view -> {
            if (chosen && !end) {
                x = 0;
                y = 2;
                button2.setText(chooseNum);
                button2.setTextColor(getColor(R.color.blue));
                playGame();
                if (end) {
                    getResult();
                } else {
                    playGame();
                    if (end) getResult();
                }
                chosen = false;
            }
        });
        Button button3 = findViewById(R.id.button3);
        button3.setOnClickListener(view -> {
            if (chosen && !end) {
                x = 1;
                y = 0;
                button3.setText(chooseNum);
                button3.setTextColor(getColor(R.color.blue));
                playGame();
                if (end) {
                    getResult();
                } else {
                    playGame();
                    if (end) getResult();
                }
                chosen = false;
            }
        });
        Button button4 = findViewById(R.id.button4);
        button4.setOnClickListener(view -> {
            if (chosen && !end) {
                x = 1;
                y = 1;
                button4.setText(chooseNum);
                button4.setTextColor(getColor(R.color.blue));
                playGame();
                if (end) {
                    getResult();
                } else {
                    playGame();
                    if (end) getResult();
                }
                chosen = false;
            }
        });
        Button button5 = findViewById(R.id.button5);
        button5.setOnClickListener(view -> {
            if (chosen && !end) {
                x = 1;
                y = 2;
                button5.setText(chooseNum);
                button5.setTextColor(getColor(R.color.blue));
                playGame();
                if (end) {
                    getResult();
                } else {
                    playGame();
                    if (end) getResult();
                }
                chosen = false;
            }
        });
        Button button6 = findViewById(R.id.button6);
        button6.setOnClickListener(view -> {
            if (chosen && !end) {
                x = 2;
                y = 0;
                button6.setText(chooseNum);
                button6.setTextColor(getColor(R.color.blue));
                playGame();
                if (end) {
                    getResult();
                } else {
                    playGame();
                    if (end) getResult();
                }
                chosen = false;
            }
        });
        Button button7 = findViewById(R.id.button7);
        button7.setOnClickListener(view -> {
            if (chosen && !end) {
                x = 2;
                y = 1;
                button7.setText(chooseNum);
                button7.setTextColor(getColor(R.color.blue));
                playGame();
                if (end) {
                    getResult();
                } else {
                    playGame();
                    if (end) getResult();
                }
                chosen = false;
            }
        });
        Button button8 = findViewById(R.id.button8);
        button8.setOnClickListener(view -> {
            if (chosen && !end) {
                x = 2;
                y = 2;
                button8.setText(chooseNum);
                button8.setTextColor(getColor(R.color.blue));
                playGame();
                if (end) {
                    getResult();
                } else {
                    playGame();
                    if (end) getResult();
                }
                chosen = false;
            }
        });
    }

    public void showSingleOptions(View view) {
        if (!end){
            List<Integer> firstlist = IntStream.range(0, 9).filter(i -> !firstPlayedPieces[i]).boxed().collect(Collectors.toList());
            List<Integer> secondlist = IntStream.range(0, 9).filter(i -> !secondPlayedPieces[i]).boxed().collect(Collectors.toList());
            String[] strArray;
            if(player == 1){
                strArray = new String[firstlist.size()];
                for (int i = 0; i < firstlist.size(); i++)
                    strArray[i] = String.valueOf(firstlist.get(i));
                final String[] nums = strArray;
                AlertDialog.Builder alert = new AlertDialog.Builder(this);
                alert.setTitle(getString(R.string.choose));
                alert.setItems(nums, (dialogInterface, i) -> {
                    chooseNum = nums[i];
                    chosen = true;
                    alertDialog.dismiss();
                });
                alertDialog = alert.create();
            }else {
                strArray = new String[secondlist.size()];
                for (int i = 0; i < secondlist.size(); i++)
                    strArray[i] = String.valueOf(secondlist.get(i));
                final String[] nums = strArray;
                AlertDialog.Builder alert = new AlertDialog.Builder(this);
                alert.setTitle(getString(R.string.choose));
                alert.setItems(nums, (dialogInterface, i) -> {
                    chooseNum = nums[i];
                    chosen = true;
                    alertDialog.dismiss();
                });
                alertDialog = alert.create();
            }
            alertDialog.show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DynamicColors.applyToActivityIfAvailable(this);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getWindow().setStatusBarColor(SurfaceColors.SURFACE_2.getColor(this));
        setContentView(R.layout.activity_pve);
        initButtons();
        if (a == 0) {
            first = new Human();
            second = new SimpleAI();
        } else {
            first = new SimpleAI();
            second = new Human();
        }
        board = new Field[3][3];
        firstPlayedPieces = new boolean[9];
        secondPlayedPieces = new boolean[9];
        winner = null;
        if (a != 0) {
            playGame();
        }
    }

    public void restart(View view) {
        recreate();
    }

    public void getResult() {
        if (winner == null) {
            new AlertDialog.Builder(
                PveActivity.this)
                .setMessage(R.string.res0)
                .show();
        } else if (winner == first && a == 0 || winner == second && a == 1) {
            new AlertDialog.Builder(
                    PveActivity.this)
                    .setMessage(R.string.res1)
                    .show();
        } else {
            new AlertDialog.Builder(
                    PveActivity.this)
                    .setMessage(R.string.res2)
                    .show();
        }
    }

    public void playGame() {
        if (player == 1) {
            if (can1) {
                Move move1;
                if (a == 0) {
                    move1 = new Move(x, y, Integer.parseInt(chooseNum));
                } else {
                    move1 = first.makeMove(board, true, firstPlayedPieces, secondPlayedPieces);
                    x = move1.x();
                    y = move1.y();
                    chooseNum = String.valueOf(move1.value());
                    if (x == 0) {
                        switch (y) {
                            case 0:
                                Button button0 = findViewById(R.id.button0);
                                button0.setText(chooseNum);
                                button0.setTextColor(getColor(R.color.red));
                                break;
                            case 1:
                                Button button1 = findViewById(R.id.button1);
                                button1.setText(chooseNum);
                                button1.setTextColor(getColor(R.color.red));
                                break;
                            case 2:
                                Button button2 = findViewById(R.id.button2);
                                button2.setText(chooseNum);
                                button2.setTextColor(getColor(R.color.red));
                                break;
                        }
                    } else if (x == 1) {
                        switch (y) {
                            case 0:
                                Button button3 = findViewById(R.id.button3);
                                button3.setText(chooseNum);
                                button3.setTextColor(getColor(R.color.red));
                                break;
                            case 1:
                                Button button4 = findViewById(R.id.button4);
                                button4.setText(chooseNum);
                                button4.setTextColor(getColor(R.color.red));
                                break;
                            case 2:
                                Button button5 = findViewById(R.id.button5);
                                button5.setText(chooseNum);
                                button5.setTextColor(getColor(R.color.red));
                                break;
                        }
                    } else {
                        switch (y) {
                            case 0:
                                Button button6 = findViewById(R.id.button6);
                                button6.setText(chooseNum);
                                button6.setTextColor(getColor(R.color.red));
                                break;
                            case 1:
                                Button button7 = findViewById(R.id.button7);
                                button7.setText(chooseNum);
                                button7.setTextColor(getColor(R.color.red));
                                break;
                            case 2:
                                Button button8 = findViewById(R.id.button8);
                                button8.setText(chooseNum);
                                button8.setTextColor(getColor(R.color.red));
                                break;
                        }
                    }
                }
                if (move1.value() > 8 || move1.value() < 0 || move1.x() < 0 || move1.y() < 0 || move1.x() > 2 || move1.y() > 2) {
                    winner = second;
                    end = true;
                    return;
                } else if (firstPlayedPieces[move1.value()]) {
                    winner = second;
                    end = true;
                    return;
                }
                if (board[move1.x()][move1.y()] == null) {
                    board[move1.x()][move1.y()] = new Field(move1.value(), true);
                    firstPlayedPieces[move1.value()] = true;
                } else if (board[move1.x()][move1.y()].value() < move1.value() && !board[move1.x()][move1.y()].firstPlayer()) {
                    board[move1.x()][move1.y()] = new Field(move1.value(), true);
                    firstPlayedPieces[move1.value()] = true;
                } else {
                    winner = second;
                    end = true;
                    return;
                }
            }else {
                winner = second;
                end = true;
                return;
            }
            player = 2;
        } else {
            if (can2) {
                Move move2;
                if (a == 0) {
                    move2 = second.makeMove(board, false, firstPlayedPieces, secondPlayedPieces);
                    x = move2.x();
                    y = move2.y();
                    chooseNum = String.valueOf(move2.value());
                    if (x == 0) {
                        switch (y) {
                            case 0:
                                Button button0 = findViewById(R.id.button0);
                                button0.setText(chooseNum);
                                button0.setTextColor(getColor(R.color.red));
                                break;
                            case 1:
                                Button button1 = findViewById(R.id.button1);
                                button1.setText(chooseNum);
                                button1.setTextColor(getColor(R.color.red));
                                break;
                            case 2:
                                Button button2 = findViewById(R.id.button2);
                                button2.setText(chooseNum);
                                button2.setTextColor(getColor(R.color.red));
                                break;
                        }
                    } else if (x == 1) {
                        switch (y) {
                            case 0:
                                Button button3 = findViewById(R.id.button3);
                                button3.setText(chooseNum);
                                button3.setTextColor(getColor(R.color.red));
                                break;
                            case 1:
                                Button button4 = findViewById(R.id.button4);
                                button4.setText(chooseNum);
                                button4.setTextColor(getColor(R.color.red));
                                break;
                            case 2:
                                Button button5 = findViewById(R.id.button5);
                                button5.setText(chooseNum);
                                button5.setTextColor(getColor(R.color.red));
                                break;
                        }
                    } else {
                        switch (y) {
                            case 0:
                                Button button6 = findViewById(R.id.button6);
                                button6.setText(chooseNum);
                                button6.setTextColor(getColor(R.color.red));
                                break;
                            case 1:
                                Button button7 = findViewById(R.id.button7);
                                button7.setText(chooseNum);
                                button7.setTextColor(getColor(R.color.red));
                                break;
                            case 2:
                                Button button8 = findViewById(R.id.button8);
                                button8.setText(chooseNum);
                                button8.setTextColor(getColor(R.color.red));
                                break;
                        }
                    }
                } else {
                    move2 = new Move(x, y, Integer.parseInt(chooseNum));
                }

                if (move2.value() > 8 || move2.value() < 0 || move2.x() < 0 || move2.y() < 0 || move2.x() > 2 || move2.y() > 2) {
                    winner = first;
                    end = true;
                    return;
                } else if (secondPlayedPieces[move2.value()]) {
                    winner = first;
                    end = true;
                    return;
                }
                if (board[move2.x()][move2.y()] == null) {
                    board[move2.x()][move2.y()] = new Field(move2.value(), false);
                    secondPlayedPieces[move2.value()] = true;
                } else if (board[move2.x()][move2.y()].value() < move2.value() && board[move2.x()][move2.y()].firstPlayer()) {
                    board[move2.x()][move2.y()] = new Field(move2.value(), false);
                    secondPlayedPieces[move2.value()] = true;
                } else {
                    winner = first;
                    end = true;
                    return;
                }
            }else {
                winner = first;
                end = true;
                return;
            }
            player = 1;
        }
        List<Integer> firstlist = IntStream.range(0, 9).filter(i -> !firstPlayedPieces[i]).boxed().collect(Collectors.toList());
        List<Integer> secondlist = IntStream.range(0, 9).filter(i -> !secondPlayedPieces[i]).boxed().collect(Collectors.toList());
        TextView textView1 = findViewById(R.id.textView2);
        TextView textView2 = findViewById(R.id.textView6);
        if(a == 0){
            String a = secondlist.toString();
            textView1.setText(a);
            String b = firstlist.toString();
            textView2.setText(b);
        }else {
            String a = firstlist.toString();
            textView1.setText(a);
            String b = secondlist.toString();
            textView2.setText(b);
        }


        int check1 = 0, check2 = 0;
        for (Field[] fields : board) {
            for (Field field : fields) {
                if (field != null) {
                    if (field.firstPlayer()) check1++;
                    else check2++;
                }
            }
            if (check1 == 3 || check2 == 3) {
                if (check1 == 3) winner = first;
                else winner = second;
                end = true;
                return;
            } else {
                check1 = 0;
                check2 = 0;
            }
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[j][i] != null) {
                    if (board[j][i].firstPlayer()) check1++;
                    else check2++;
                }
            }
            if (check1 == 3 || check2 == 3) {
                if (check1 == 3) winner = first;
                else winner = second;
                end = true;
                return;
            } else {
                check1 = 0;
                check2 = 0;
            }
        }
        for (int i = 0; i < 3; i++) {
            if (board[i][i] != null) {
                if (board[i][i].firstPlayer()) check1++;
                else check2++;
            }
        }
        if (check1 == 3 || check2 == 3) {
            if (check1 == 3) winner = first;
            else winner = second;
            end = true;
            return;
        } else {
            check1 = 0;
            check2 = 0;
        }
        for (int i = 0; i < 3; ) {
            for (int j = 2; j >= 0; j--) {
                if (board[i][j] != null) {
                    if (board[i][j].firstPlayer()) check1++;
                    else check2++;
                }
                i++;
            }
        }
        if (check1 == 3 || check2 == 3) {
            if (check1 == 3) winner = first;
            else winner = second;
            end = true;
            return;
        }
        can1 = false;
        can2 = false;
        loop1:
        for (Field[] fields : board) {
            for (Field field : fields) {
                if (field == null) {
                    can1 = true;
                    break loop1;
                }
                if (!field.firstPlayer()) {
                    for (int k = 0; k < 9; k++) {
                        if (!firstPlayedPieces[k] && k > field.value()) {
                            can1 = true;
                            break loop1;
                        }
                    }
                }
            }
        }
        loop2:
        for (Field[] fields : board) {
            for (Field field : fields) {
                if (field == null) {
                    can2 = true;
                    break loop2;
                }
                if (field.firstPlayer()) {
                    for (int k = 0; k < 9; k++) {
                        if (!secondPlayedPieces[k] && k > field.value()) {
                            can2 = true;
                            break loop2;
                        }
                    }
                }
            }
        }
        if (firstlist.isEmpty() && secondlist.isEmpty()) {
            int sum1 = 0, sum2 = 0;
            for (Field[] Fields : board) {
                for (Field field : Fields) {
                    if (field != null) {
                        if (field.firstPlayer()) sum1 += field.value();
                        else sum2 += field.value();
                    }
                }
            }
            if (sum1 > sum2) winner = second;
            else if (sum1 < sum2) winner = first;
            else winner = null;
            end = true;
        } else if (firstlist.isEmpty() && !can2) {
            winner = first;
            end = true;
        }
    }
}