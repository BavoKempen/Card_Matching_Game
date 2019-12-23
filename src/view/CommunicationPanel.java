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
        System.out.println(game.getSettings().getCommunicationPanelWidth());
        playerLabels.setPreferredSize(new Dimension((int) Math.round(game.getSettings().getCommunicationPanelWidth()*.9),(int) Math.round(game.getSettings().getMainPanelHeight()*.4)));

        highScoresPanel = new HighScoresPanel(game);
        highScoresPanel.setPreferredSize(new Dimension((int) Math.round(game.getSettings().getCommunicationPanelWidth()*.9), (int) Math.round(game.getSettings().getMainPanelHeight()*.45)));

        add(playerLabels, BorderLayout.NORTH);
        add(highScoresPanel, BorderLayout.LINE_END);
        setBackground(Color.RED);


    }

    public void initialize(){

        playerLabels.initialize();

    }
}
