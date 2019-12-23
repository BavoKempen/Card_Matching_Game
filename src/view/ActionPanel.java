package view;

import actions.*;
import model.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionPanel extends JPanel{

    JButton startButton;
    JButton exitButton;
    JButton rulesButton;
    JButton restartButton;


    public ActionPanel(Game game){
        startButton = new JButton("Start Game");
        exitButton = new JButton("End Game");
        rulesButton = new JButton("Game Rules");
        restartButton = new JButton("Restart Game");

        //Layout of the panel
        setLayout(new FlowLayout());
        setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

        add(startButton);
        add(exitButton);
        add(rulesButton);
        add(restartButton);
        setBackground(Color.BLUE);

        rulesButton.addActionListener(new OpenRules());




    }


}

