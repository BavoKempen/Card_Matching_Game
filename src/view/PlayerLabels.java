package view;

import model.Game;
import model.Player;

import javax.swing.*;
import java.awt.*;

/*
In Sum:
        1) PlayerLabels class actually is the dynamic part of the communcationPanel during the game
            a) Serves as subcontainer for JLabels used here.
            b) setActivePlayer method
            c) initialize method
 */

public class PlayerLabels extends JPanel {

    // Pass info
    private Game game;

    // JLabels that will be "refreshed" during game
    private JLabel playerOneScore;
    private JLabel playerTwoScore;
    private JLabel activePlayerLabel;

    // JLabels initialized during game start up etc.
    private JLabel playerOneLabel;
    private JLabel playerTwoLabel;
    private JLabel vs;



    public PlayerLabels(Game game){

        // Pass information
        this.game = game;

        // More control over Layout
        GridBagConstraints c = new GridBagConstraints();
        setLayout(new GridBagLayout());

        // Initialize labels with infor derived from settings through game
        playerOneLabel = new JLabel(game.getSettings().getPlayers().get(0).getName(),SwingConstants.LEFT);
        playerTwoLabel = new JLabel(game.getSettings().getPlayers().get(1).getName(),SwingConstants.LEFT);

        playerOneScore = new JLabel("Score: " + game.getSettings().getPlayers().get(0).getScore(),SwingConstants.LEFT);
        playerTwoScore = new JLabel("Score: " + game.getSettings().getPlayers().get(1).getScore(), SwingConstants.LEFT);

        activePlayerLabel = new JLabel(game.getSettings().getPlayers().get(0).getName() + "'s turn", SwingConstants.CENTER);
        vs = new JLabel("Versus",SwingConstants.CENTER);

        // Customize JLabel's size and fonts
        playerOneLabel.setPreferredSize(new Dimension(110, 40));
        playerOneScore.setPreferredSize(new Dimension(110, 40));

        playerOneLabel.setFont(new Font("Serif", Font.BOLD,16));
        playerOneScore.setFont(new Font("Serif", Font.BOLD,16));

        vs.setPreferredSize(new Dimension(220, 40));
        vs.setFont(new Font("Serif", Font.BOLD,20));

        playerTwoLabel.setPreferredSize(new Dimension(110,40));
        playerTwoScore.setPreferredSize(new Dimension(110,40));

        playerTwoLabel.setFont(new Font("Serif", Font.BOLD,16));
        playerTwoScore.setFont(new Font("Serif", Font.BOLD,16));

        activePlayerLabel.setPreferredSize(new Dimension(220, 65));
        activePlayerLabel.setFont(new Font("Serif", Font.BOLD,20));

        // Define GridBagConstraints for each component and position them respectively in the JPanel of PlayerLabels
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx =0;
        c.gridy =0;
        c.weightx = .5;
        add(playerOneLabel,c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 0;
        c.weightx = .5;
        add(playerOneScore,c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy= 1;
        c.gridwidth = 2;
        c.weightx = 0.0;
        add(vs,c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx =0;
        c.gridy =2;
        c.weightx = .5;
        add(playerTwoLabel,c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 2;
        c.weightx = .5;
        add(playerTwoScore,c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy= 3;
        c.gridwidth = 2;
        c.weightx = 0.0;
        add(activePlayerLabel,c);

    }

    // Method: Receives name from the gamePanel where this is set every turn
    public void setActivePlayer(Player p) {

        activePlayerLabel.setText(p.getName() + "'s turn");

    }

    // Separate initialization because score has to be updated one move behind activePlayerLabel
    public void initialize() {

        // Update information: Summarized flow: gamePanel -> settings -> playerLabels
        playerOneLabel.setText(game.getSettings().getPlayers().get(0).getName());
        playerTwoLabel.setText(game.getSettings().getPlayers().get(1).getName());

        playerOneScore.setText("Score: " + game.getSettings().getPlayers().get(0).getScore());
        playerTwoScore.setText("Score: " + game.getSettings().getPlayers().get(1).getScore());

    }
}


