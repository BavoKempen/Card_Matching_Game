package view;

import model.Game;
import model.Player;

import javax.swing.*;
import java.awt.*;

public class PlayerLabels extends JPanel {


    private Game game;

    public JLabel playerOneLabel;
    public JLabel playerTwoLabel;
    private JLabel vs;
    public JLabel playerOneScore;
    public JLabel playerTwoScore;
    private JLabel currentGame;
    private JLabel activePlayerLabel;



    public PlayerLabels(Game game){

        this.game = game;
        GridBagLayout nameScoreLayout = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        setLayout(nameScoreLayout);

        currentGame = new JLabel("Score", SwingConstants.CENTER);

        playerOneLabel = new JLabel(game.getSettings().getPlayers().get(0).getName(),SwingConstants.CENTER);
        playerTwoLabel = new JLabel(game.getSettings().getPlayers().get(1).getName(),SwingConstants.CENTER);
        activePlayerLabel = new JLabel(game.getSettings().getPlayers().get(0).getName() + "'s turn");

        vs = new JLabel("Versus",SwingConstants.CENTER);

        System.out.println("repaint PlayerLabels");
        playerOneScore = new JLabel("Score: " + game.getSettings().getPlayers().get(0).getScore(),SwingConstants.LEFT); //+ game.getSettings().getPointsPlayerOne()
        playerTwoScore = new JLabel("Score: " + game.getSettings().getPlayers().get(1).getScore(), SwingConstants.LEFT);//+ game.getSettings().getPointsPlayerTwo()

        currentGame.setPreferredSize(new Dimension(200,20));

        playerOneLabel.setPreferredSize(new Dimension(100,60 ));
        playerOneScore.setPreferredSize(new Dimension(100,60));

        vs.setPreferredSize(new Dimension(200,10 ));
        vs.setFont(new Font("Serif", Font.BOLD,10));

        playerTwoLabel.setPreferredSize(new Dimension(100,60 ));
        playerTwoScore.setPreferredSize(new Dimension(100, 60));
        activePlayerLabel.setPreferredSize(new Dimension(200,50));
//        game.getSettings().setGreen(playerOneLabel);
  //      game.getSettings().setRed(playerTwoLabel);

        c.gridx = 0;
        c.gridy = 0;

        add(currentGame,c);

        c.gridy =1;
        add(playerOneLabel,c);
        c.gridx = 1;
        add(playerOneScore,c);

        c.gridx = 0;
        c.gridy= 2;
        add(vs,c);

        c.gridy = 3;
        add(playerTwoLabel,c);

        c.gridx = 1;
        add(playerTwoScore,c);

        c.gridy = 4;
        add(activePlayerLabel, c);

    }

    public void setActivePlayer(Player p) {
        activePlayerLabel.setText(p.getName() + "'s turn");

    }


    public void initialize() {
        playerOneLabel.setText(game.getSettings().getPlayers().get(0).getName());
        playerTwoLabel.setText(game.getSettings().getPlayers().get(1).getName());

        playerOneScore.setText("Score: " + game.getSettings().getPlayers().get(0).getScore());
        playerTwoScore.setText("Score: " + game.getSettings().getPlayers().get(1).getScore());

        //activePlayerLabel.setText(game.getSettings().getPlayers().get(0).getName()+ "'s turn");
    }
}


