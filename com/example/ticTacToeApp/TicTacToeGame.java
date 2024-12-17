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
    private final int FRAME_SIZE = 900;
    private final int BUTTON_SIZE = 300;
    private static char turn = 'X';

    public void play(){
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(FRAME_SIZE, FRAME_SIZE);
        frame.setLayout(new java.awt.GridLayout(3, 3, 0, 0));
        JButton[] buttons = new JButton[NUMBER_OF_BUTTONS];

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
                Font newFont = new Font("Arial", Font.BOLD, newFontSize);
                for (JButton button : buttons) {
                    button.setFont(newFont);
                }
            }
        });

        frame.setVisible(true);
    }


    private void checkWinner(JButton[] buttons, JFrame frame){
        int i = 0, j = 1, k = 2;
        for (; i < NUMBER_OF_BUTTONS; i+=3, j+=3, k+=3){ // check rows
            if (buttons[i].getText() == "X" && buttons[j].getText() == "X" && buttons[k].getText() == "X"){
                showVictoryMessage(frame, true, false, i, j, k, buttons);
            }
            else if (buttons[i].getText() == "O" && buttons[j].getText() == "O" && buttons[k].getText() == "O"){
                showVictoryMessage(frame, false, true, i, j, k, buttons);
            }
        }
        for (i = 0, j = 3, k = 6; i < 3; i++, j++, k++){ // check columns
            if (buttons[i].getText() == "X" && buttons[j].getText() == "X" && buttons[k].getText() == "X"){
                showVictoryMessage(frame, true, false, i, j, k, buttons);
            }
            else if (buttons[i].getText() == "O" && buttons[j].getText() == "O" && buttons[k].getText() == "O"){
                showVictoryMessage(frame, false, true, i, j, k, buttons);
            }
        }
        if (buttons[0].getText() == "X" && buttons[4].getText() == "X" && buttons[8].getText() == "X") { // first diagonal
            showVictoryMessage(frame, true, false, 0, 4, 8, buttons);
        }
        else if (buttons[0].getText() == "O" && buttons[4].getText() == "O" && buttons[8].getText() == "O"){
            showVictoryMessage(frame, false, true, 0, 4, 8, buttons);
        }
        if (buttons[2].getText() == "X" && buttons[4].getText() == "X" && buttons[6].getText() == "X"){ // second diagonal
            showVictoryMessage(frame, true, false, 2, 4, 6, buttons);
        }
        else if (buttons[2].getText() == "O" && buttons[4].getText() == "O" && buttons[6].getText() == "O"){
            showVictoryMessage(frame, false, true, 2, 4, 6, buttons);
        }

        for (i = 0; i < NUMBER_OF_BUTTONS; i++){ // check for draw
            if (buttons[i].getText() == ""){
                break;
            }
            else if (i == NUMBER_OF_BUTTONS - 1 && (buttons[i].getText() == "X" || buttons[i].getText() == "O")){
                showVictoryMessage(frame, false, false, i, j, k, buttons);
            }
        }
    }


    private void showVictoryMessage(JFrame frame, Boolean playerXwon, Boolean playerOwon, int i, int j, int k, JButton[] buttons){
        if (playerXwon && !playerOwon){
            color(buttons, i, j, k);
            JOptionPane.showMessageDialog(frame, "Player X wins", "Winner", JOptionPane.INFORMATION_MESSAGE);
            reset(buttons);
        } else if (playerOwon && !playerXwon){
            color(buttons, i, j, k);
            JOptionPane.showMessageDialog(frame, "Player O wins", "Winner", JOptionPane.INFORMATION_MESSAGE);
            reset(buttons);
        } else if (!playerXwon && !playerOwon){
            JOptionPane.showMessageDialog(frame, "It's a draw", "Draw", JOptionPane.INFORMATION_MESSAGE);
            reset(buttons);
        }
    }


    private void unColor(JButton[] buttons){
        for (int i = 0; i < buttons.length; i++){
            buttons[i].setForeground(Color.BLACK);
        }
    }


    private void color(JButton[] buttons, int i, int j, int k){
        buttons[i].setForeground(Color.GREEN);
        buttons[j].setForeground(Color.GREEN);
        buttons[k].setForeground(Color.GREEN);
    }

    
    private void reset(JButton[] buttons){
        for (int i = 0; i < buttons.length; i++){
            buttons[i].setText("");
        }
        turn = 'X';
        unColor(buttons);
    }
}
