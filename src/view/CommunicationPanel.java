package view;

import model.Game;

import javax.swing.*;
import java.awt.*;

public class CommunicationPanel extends JPanel {

    private Game game;
    PlayerLabels playerLabels;
    HighScoresPanel highScoresPanel;

    public CommunicationPanel(Game game){

        this.game = game;

        playerLabels = new PlayerLabels(game);
        playerLabels.setPreferredSize(new Dimension(200,200));
        highScoresPanel = new HighScoresPanel(game);
        highScoresPanel.setPreferredSize(new Dimension(200, 250));


        add(playerLabels, BorderLayout.NORTH);
        add(highScoresPanel, BorderLayout.SOUTH);


        setPreferredSize(new Dimension(200,500));
    }
}
