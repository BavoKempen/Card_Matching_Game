package view;

import model.Game;
import settings.PlayerLabels;

import javax.swing.*;
import java.awt.*;

public class CommunicationPanel extends JPanel {

    private Game game;
    PlayerLabels playerLabels;

    public CommunicationPanel(Game game){

        this.game = game;

        playerLabels = new PlayerLabels(game);

        add(playerLabels, BorderLayout.NORTH);

        setBackground(Color.RED);
        setPreferredSize(new Dimension(200,500));
    }
}
