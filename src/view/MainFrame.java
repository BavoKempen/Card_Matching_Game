package view;

import actions.CloseOnExit;
import model.Game;
import model.Player;
import model.Settings;
import settings.PveOrPvp;

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

    //Dynamic
    private PlayerLabels playerLabels;
    Player player;
    Settings settings;



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

        communicationPanel.setPreferredSize(new Dimension(200,500));
        //actionPanel.setPreferredSize(new Dimension(700,100) );
        mainPanel.setPreferredSize(new Dimension(650, 400));




        add(mainPanel, BorderLayout.CENTER);
        add(actionPanel, BorderLayout.PAGE_END);
        add(communicationPanel, BorderLayout.LINE_END);
        //Global GUI settings and user specific full screen resolution display
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(900,500);
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




    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == actionPanel.startButton) {
            gameOn();
            //setPlayerName();
            actionPanel.startButton.setEnabled(false);


            //playerLabels.setPlayerText(settingsPanel.pveOrPvp.playerOne.getText(), settingsPanel.pveOrPvp.playerTwo.getText());
            // playerLabels.playerOneLabel.setText("derp");

        }


    }

    public void gameOn(){
        this.gamePanel.initialize();
        cardLayout.show(mainPanel, "gamePanel");
    }

    public void setPlayerName(){

        game.getSettings().addPlayer(settingsPanel.pveOrPvp.playerOne.getText());
        game.getSettings().addPlayer(settingsPanel.pveOrPvp.playerTwo.getText());
        System.out.println(game.getSettings().getPlayers().get(0));
//        playerLabels.setPlayerText(String.valueOf(game.getSettings().getPlayers().get(0)), String.valueOf(game.getSettings().getPlayers().get(1)));

    }



}


