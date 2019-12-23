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

        GridBagConstraints c = new GridBagConstraints();
        setLayout(new GridBagLayout());


        playerOneLabel = new JLabel(game.getSettings().getPlayers().get(0).getName(),SwingConstants.LEFT);
        playerTwoLabel = new JLabel(game.getSettings().getPlayers().get(1).getName(),SwingConstants.LEFT);
        activePlayerLabel = new JLabel(game.getSettings().getPlayers().get(0).getName() + "'s turn", SwingConstants.CENTER);

        vs = new JLabel("Versus",SwingConstants.CENTER);

        System.out.println("repaint PlayerLabels" +game.getSettings().getMainPanelHeight()*.4);
        playerOneScore = new JLabel("Score: " + game.getSettings().getPlayers().get(0).getScore(),SwingConstants.LEFT); //+ game.getSettings().getPointsPlayerOne()
        playerTwoScore = new JLabel("Score: " + game.getSettings().getPlayers().get(1).getScore(), SwingConstants.LEFT);//+ game.getSettings().getPointsPlayerTwo()

        playerOneLabel.setPreferredSize(new Dimension(110, 40));
        playerOneScore.setPreferredSize(new Dimension(110, 40));
        playerOneLabel.setFont(new Font("Serif", Font.BOLD,16));
        playerOneScore.setFont(new Font("Serif", Font.BOLD,16));
        //playerOneLabel.setPreferredSize(new Dimension((int) Math.round((game.getSettings().getCommunicationPanelWidth()*.9)*.5),(int) Math.round((game.getSettings().getMainPanelHeight()*.4)*.2)));
        //playerOneScore.setPreferredSize(new Dimension((int) Math.round((game.getSettings().getCommunicationPanelWidth()*.9)*.5),(int) Math.round((game.getSettings().getMainPanelHeight()*.4)*.2)));

        //vs.setPreferredSize(new Dimension((int) Math.round((game.getSettings().getCommunicationPanelWidth()*.9)),(int) Math.round((game.getSettings().getMainPanelHeight()*.4)*.2)));
        vs.setPreferredSize(new Dimension(220, 40));
        vs.setFont(new Font("Serif", Font.BOLD,20));

        playerTwoLabel.setPreferredSize(new Dimension(110,40));
        playerTwoScore.setPreferredSize(new Dimension(110,40));
        playerTwoLabel.setFont(new Font("Serif", Font.BOLD,16));
        playerTwoScore.setFont(new Font("Serif", Font.BOLD,16));
        activePlayerLabel.setPreferredSize(new Dimension(220, 65));
        activePlayerLabel.setFont(new Font("Serif", Font.BOLD,20));
        //playerTwoLabel.setPreferredSize(new Dimension((int) Math.round((game.getSettings().getCommunicationPanelWidth()*.9)*.5),(int) Math.round((game.getSettings().getMainPanelHeight()*.4)*.2)));
        //playerTwoScore.setPreferredSize(new Dimension((int) Math.round((game.getSettings().getCommunicationPanelWidth()*.9)*.5),(int) Math.round((game.getSettings().getMainPanelHeight()*.4)*.2)));
        //activePlayerLabel.setPreferredSize(new Dimension((int) Math.round((game.getSettings().getCommunicationPanelWidth()*.9)),(int) Math.round((game.getSettings().getMainPanelHeight()*.4)*.4)));
//        game.getSettings().setGreen(playerOneLabel);
  //      game.getSettings().setRed(playerTwoLabel);


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


