package view;

import actions.*;
import model.Game;

import javax.swing.*;
import java.awt.*;

/*
In Sum:
        1) Container for all the buttons that elicit a global change
            a) startButton: Initializes game with settings given by user
            b) exitButton: Closes application with JDialog pop up
            c) restartButton: Navigates back to initial state application to f.e. play a new game
            d) rulesButton: Pops up window with the rules of the game outlined
            e) a), b) and c) have actionlistener in MainFrame, because e.g., dispose() affects whole Frame etc.
                d) has actionlistener as separate class, could be untangled as such more easily than the former.

 */

public class ActionPanel extends JPanel{

    // Info Pass
    private Game game;

    // Buttons
    // First three package private for easy access in MainFrame
    JButton startButton;
    JButton exitButton;
    JButton restartButton;
    private JButton rulesButton;


    public ActionPanel(Game game){

        // Pass and retrieve info
        // here for stylistic reasons (i.e., size frame etc)
        this.game = game;

        // Buttons
        startButton = new JButton("Start Game");
        exitButton = new JButton("Exit Application");
        restartButton = new JButton("Restart Game");
        rulesButton = new JButton("Game Rules");

        // Layout of the panel, horizontal and vertical gap of 20px and 5px respectively between buttons and actionPanel's
        // boundaries.
        setLayout(new FlowLayout(FlowLayout.LEADING, 20,5));
        setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

        // Set Preferred size relative to frame size
        // Use Math.round, otherwise casting to (int) will round e.g., 6.99 to 6, with math.round to 7.
        startButton.setPreferredSize(new Dimension( (int) Math.round(game.getSettings().getActionAndMainPanelWidth()*.2), (int) Math.round(game.getSettings().getActionPanelHeight()*.8)));
        exitButton.setPreferredSize(new Dimension( (int) Math.round(game.getSettings().getActionAndMainPanelWidth()*.2), (int) Math.round(game.getSettings().getActionPanelHeight()*.8)));
        restartButton.setPreferredSize(new Dimension( (int) Math.round(game.getSettings().getActionAndMainPanelWidth()*.2), (int) Math.round(game.getSettings().getActionPanelHeight()*.8)));
        rulesButton.setPreferredSize(new Dimension( (int) Math.round(game.getSettings().getActionAndMainPanelWidth()*.2), (int) Math.round(game.getSettings().getActionPanelHeight()*.8)));

        // Add buttons from left to right in this order
        add(startButton);
        add(restartButton);
        add(exitButton);
        add(rulesButton);

        restartButton.setEnabled(false);

        // ActionListener of rules Buttons, more info in class OpenRules
        rulesButton.addActionListener(new OpenRules());

    }
}

