package com.example.ticTacToeApp;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class TicTacToeGame {
    private final int NUMBER_OF_BUTTONS = 9;
    private final int FRAME_WIDTH = 900;
    private final int FRAME_HEIGHT = 750;
    private int BUTTON_SIZE = 300;
    private static char turn = 'X';
    JFrame frame = new JFrame();
    JButton[] buttons = new JButton[NUMBER_OF_BUTTONS];

    public void play(){
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setLayout(new java.awt.GridLayout(3, 3, 0, 0));

        for (int i = 0; i < NUMBER_OF_BUTTONS; i++){
            buttons[i] = new JButton();
            buttons[i].setOpaque(true);
            frame.add(buttons[i]);
        }

        for (int i = 0; i < NUMBER_OF_BUTTONS; i++){
            buttons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    JButton button = (JButton) e.getSource();
                    button.setFont(new Font("Arial", Font.BOLD, BUTTON_SIZE));
                    button.setForeground(Color.BLACK);
                    if (turn == 'X' && button.getText().equals("")){
                        button.setText("X");
                        turn = 'O';
                    }
                    else if (turn == 'O' && button.getText().equals("")){
                        button.setText("O");
                        turn = 'X';
                    }
                    // check for winner after each move
                    checkWinner(buttons, frame);
                }
            });
        }

        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int newFontSize = Math.min(frame.getWidth(), frame.getHeight()) / 3; // Adjust this factor as needed
                BUTTON_SIZE = newFontSize;
                Font newFont = new Font("Arial", Font.BOLD, BUTTON_SIZE);
                for (JButton button : buttons) {
                    button.setFont(newFont);
                }
            }
        });

        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }


    private void checkWinner(JButton[] buttons, JFrame frame){
        int i = 0, j = 1, k = 2;
        for (; i < NUMBER_OF_BUTTONS; i+=3, j+=3, k+=3){ // check rows
            if (buttons[i].getText() == "X" && buttons[j].getText() == "X" && buttons[k].getText() == "X"){
                showVictoryMessage(true, false, i, j, k);
            }
            else if (buttons[i].getText() == "O" && buttons[j].getText() == "O" && buttons[k].getText() == "O"){
                showVictoryMessage(false, true, i, j, k);
            }
        }
        for (i = 0, j = 3, k = 6; i < 3; i++, j++, k++){ // check columns
            if (buttons[i].getText() == "X" && buttons[j].getText() == "X" && buttons[k].getText() == "X"){
                showVictoryMessage(true, false, i, j, k);
            }
            else if (buttons[i].getText() == "O" && buttons[j].getText() == "O" && buttons[k].getText() == "O"){
                showVictoryMessage(false, true, i, j, k);
            }
        }
        if (buttons[0].getText() == "X" && buttons[4].getText() == "X" && buttons[8].getText() == "X") { // first diagonal
            showVictoryMessage(true, false, 0, 4, 8);
        }
        else if (buttons[0].getText() == "O" && buttons[4].getText() == "O" && buttons[8].getText() == "O"){
            showVictoryMessage(false, true, 0, 4, 8);
        }
        if (buttons[2].getText() == "X" && buttons[4].getText() == "X" && buttons[6].getText() == "X"){ // second diagonal
            showVictoryMessage(true, false, 2, 4, 6);
        }
        else if (buttons[2].getText() == "O" && buttons[4].getText() == "O" && buttons[6].getText() == "O"){
            showVictoryMessage(false, true, 2, 4, 6);
        }

        for (i = 0; i < NUMBER_OF_BUTTONS; i++){ // check for draw
            if (buttons[i].getText() == ""){
                break;
            }
            else if (i == NUMBER_OF_BUTTONS - 1 && (buttons[i].getText() == "X" || buttons[i].getText() == "O")){
                showVictoryMessage(false, false, i, j, k);
            }
        }
    }


    private void showVictoryMessage(Boolean playerXwon, Boolean playerOwon, int i, int j, int k){
        if (playerXwon && !playerOwon){
            color(i, j, k);
            JOptionPane.showMessageDialog(frame, "Player X won", "Winner", JOptionPane.INFORMATION_MESSAGE);
            reset();
        } else if (playerOwon && !playerXwon){
            color(i, j, k);
            JOptionPane.showMessageDialog(frame, "Player O won", "Winner", JOptionPane.INFORMATION_MESSAGE);
            reset();
        } else if (!playerXwon && !playerOwon){
            JOptionPane.showMessageDialog(frame, "It's a draw", "Draw", JOptionPane.INFORMATION_MESSAGE);
            reset();
        }
    }


    private void unColor(){
        for (int i = 0; i < buttons.length; i++){
            buttons[i].setForeground(Color.BLACK);
        }
    }


    private void color(int i, int j, int k){
        buttons[i].setForeground(Color.GREEN);
        buttons[j].setForeground(Color.GREEN);
        buttons[k].setForeground(Color.GREEN);
    }

    
    private void reset(){
        for (int i = 0; i < buttons.length; i++){
            buttons[i].setText("");
        }
        turn = 'X';
        unColor();
    }
}
