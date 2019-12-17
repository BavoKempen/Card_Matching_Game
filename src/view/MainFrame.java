package view;

import actions.CloseOnExit;
import model.Game;
import model.Player;
import model.Settings;
import settings.PlayerLabels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MainFrame extends JFrame {

    //all info Back-end
    Game game;

    //UI
    ActionPanel actionPanel;
    SettingsPanel settingsPanel;
    GamePanel gamePanel;
    CommunicationPanel communicationPanel;

    JPanel mainPanel;
    CardLayout cardLayout;
    private Object PlayerLabels;


    /*
    The view.MainFrame() should be maximum screen according to user's dimensions
    Three Panels needed in Layout:
    (1) Center: view.SettingsPanel, view.GamePanel in mainPanel linked to startButton
    (2) Bottom: view.ActionPanel: Start, Stop, Rules
    (3) Line_end: RealTimePanel: Respective Players, Top5Highscore, Timer, ...
     */
    public MainFrame(Game game){
        super("Memory Game");

        this.game = game;

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        settingsPanel = new SettingsPanel(game);
        gamePanel = new GamePanel(game);

        mainPanel.add(settingsPanel, "settingsPanel");
        mainPanel.add(gamePanel, "gamePanel");

        actionPanel = new ActionPanel(game);
        communicationPanel = new CommunicationPanel(game);


        add(mainPanel, BorderLayout.CENTER);
        add(actionPanel, BorderLayout.PAGE_END);
        add(communicationPanel, BorderLayout.LINE_END);
        //Global GUI settings and user specific full screen resolution display
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800,500);
        pack();
        setLocationByPlatform(true);

        //setExtendedState(JFrame.MAXIMIZED_BOTH);
        //setUndecorated(true);
        setVisible(true);



        //view.SettingsPanel contains all user selected input

        //Add all the actionListeners to extract values of settings panel
        actionPanel.startButton.addActionListener(this::actionPerformed);
        actionPanel.exitButton.addActionListener(new CloseOnExit());







    }

    //TODO: Pass forward info from view.SettingsPanel to view.GamePanel

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == actionPanel.startButton) {
            gameOn();
            setPlayerNames(game);
        }





    }

    public void gameOn(){
        this.gamePanel.initialize();
        cardLayout.show(mainPanel, "gamePanel");


    }
    public void setPlayerNames(Game game){


        System.out.println("c-"+game.getSettings().getPlayers().size());

        //System.out.print(game.getSettings().getPlayers().size());
        game.getSettings().removePlayers(0);
        game.getSettings().removePlayers(0);
        System.out.println(game.getSettings().getComputerOrMultiplayer());


        if (game.getSettings().getComputerOrMultiplayer() == false){
            game.getSettings().addPlayer(settingsPanel.pveOrPvp.playerOne.getText());
            game.getSettings().addPlayer(settingsPanel.pveOrPvp.playerTwo.getText());
            communicationPanel.revalidate();




        }
        else
            game.getSettings().addPlayer(settingsPanel.pveOrPvp.playerOne.getText());
            game.getSettings().addPlayer("Computer");


        //this.communicationPanel.playerLabels(game);
        System.out.println("b-"+game.getSettings().getPlayers().get(0));
        System.out.println("b-"+game.getSettings().getPlayers().get(1));

        communicationPanel.removeAll();
        settings.PlayerLabels refreshPlayers = new PlayerLabels(game);
        communicationPanel.add(refreshPlayers, BorderLayout.NORTH);
        communicationPanel.validate();
        communicationPanel.repaint();




    }
}


