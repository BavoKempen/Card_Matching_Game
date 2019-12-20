package view;

import actions.*;
import model.Game;

import javax.swing.*;
import java.awt.*;

public class ActionPanel extends JPanel{

    SettingsPanel settingsPanel;
    JButton startButton;
    JButton exitButton;
    JButton rulesButton;

    private PlayerLabels playerLabels;

    public ActionPanel(Game game){
        startButton = new JButton("Start Game");
        exitButton = new JButton("End Game");
        rulesButton = new JButton("Game Rules");

        //Layout of the panel
        setLayout(new FlowLayout());
        setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

        add(startButton);
        add(exitButton);
        add(rulesButton);

        rulesButton.addActionListener(new OpenRules());




    }




}

