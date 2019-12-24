package view;

import model.Game;

import javax.swing.*;
import java.awt.*;

/*
In Sum:
        1) Mid level container for the playerLabels and highScoresPanel
            a) Pass game to its components
            b) Contains Initialize method that calls the initialize method of playerLabels.
                i) Used for dynamic score updates
                ii) Used for display active player
        2) Receives information from both settingsPanel and gamePanel and passes it to components that adjust accordingly.

 */

public class CommunicationPanel extends JPanel {

    // Pass information
    private Game game;

    // Declare components package private for easy accessibility during intercommunication
    PlayerLabels playerLabels;
    HighScoresPanel highScoresPanel;

    public CommunicationPanel(Game game){

        // Pass and get information
        this.game = game;

        // Declare sub containers that are placed within the communicationPanel
        playerLabels = new PlayerLabels(game);
        playerLabels.setPreferredSize(new Dimension((int) Math.round(game.getSettings().getCommunicationPanelWidth()*.9),(int) Math.round(game.getSettings().getMainPanelHeight()*.4)));

        highScoresPanel = new HighScoresPanel(game);
        highScoresPanel.setPreferredSize(new Dimension((int) Math.round(game.getSettings().getCommunicationPanelWidth()*.9), (int) Math.round(game.getSettings().getMainPanelHeight()*.45)));

        // Attach components to opposite side of JPanel
        add(playerLabels, BorderLayout.NORTH);
        add(highScoresPanel, BorderLayout.LINE_END);
    }

    // Pass call for initialize to playerLabels so that communicationPanel.initialize() is sufficient to reinitialize the
    // playerLabels class
    public void initialize(){

        playerLabels.initialize();

    }
}
