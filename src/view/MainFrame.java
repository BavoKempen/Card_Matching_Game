package view;

import com.sun.tools.javac.Main;
import model.Game;
import model.Player;
import model.Settings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.HierarchyBoundsListener;
import java.awt.event.HierarchyEvent;
import java.util.ArrayList;

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
        setPreferredSize(new Dimension(1000,600));
        System.out.println(getWidth());
        game.getSettings().setWindowHeight(600);
        game.getSettings().setWindowWidth(1000);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        settingsPanel = new SettingsPanel(game);
        actionPanel = new ActionPanel(game);
        communicationPanel = new CommunicationPanel(game);

        mainPanel.add(settingsPanel, "settingsPanel");


        communicationPanel.setPreferredSize(new Dimension((int) Math.round(game.getSettings().getCommunicationPanelWidth()), (int) Math.round(game.getSettings().getMainPanelHeight())));
        actionPanel.setPreferredSize(new Dimension((int) Math.round(game.getSettings().getActionAndMainPanelWidth()),(int) Math.round(game.getSettings().getActionPanelHeight())));
        mainPanel.setPreferredSize(new Dimension((int) Math.round(game.getSettings().getActionAndMainPanelWidth()), (int) Math.round(game.getSettings().getMainPanelHeight())));



        /*
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();



        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = .75;
        c.weighty = .9;
        c.gridx = 0;
        c.gridy = 0;
        add(mainPanel, c);

        c.fill = GridBagConstraints.VERTICAL;
        c.weightx = .25;
        c.gridheight = 2;
        c.gridx = 1;
        add(communicationPanel, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = .75;
        c.weighty = .1;
        c.gridx = 0;
        c.gridy = 1;
        add(actionPanel, c);

         */







        add(mainPanel, BorderLayout.CENTER);
        add(actionPanel, BorderLayout.SOUTH);
        add(communicationPanel, BorderLayout.LINE_END);




        //Global GUI settings and user specific full screen resolution display
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        //setSize(900,500);
        pack();
        //setLocationByPlatform(true);

        //setExtendedState(JFrame.MAXIMIZED_BOTH);
        //setUndecorated(true);
        setVisible(true);


        //view.SettingsPanel contains all user selected input

        //Add all the actionListeners to extract values of settings panel
        actionPanel.startButton.addActionListener(this::actionPerformed);
        actionPanel.exitButton.addActionListener(this::actionPerformed);
        actionPanel.restartButton.addActionListener(this::actionPerformed);







    }




    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == actionPanel.startButton) {
            gameOn();

        }
        if (e.getSource() == actionPanel.exitButton){
            int confirmed = JOptionPane.showConfirmDialog(null,"Are you sure you want to exit the game?",
                    "Exit Game", JOptionPane.YES_NO_OPTION);
            if (confirmed == JOptionPane.YES_OPTION){
                System.exit(0);
            }

        }

        if (e.getSource() == actionPanel.restartButton){

            restartGame();

        }



    }

    public void gameOn(){
        this.settingsPanel.pveOrPvp.saveSettings();

        if (game.getSettings().getSinglePlayer()) {
            gamePanel = new pveGamePanel(game, communicationPanel);
        } else {
            gamePanel = new GamePanel(game , communicationPanel);
        }

        mainPanel.add(gamePanel, "gamePanel");

        this.gamePanel.initialize();
        this.communicationPanel.initialize();
        cardLayout.show(mainPanel, "gamePanel");
        actionPanel.startButton.setEnabled(false);
    }

    public void restartGame(){
        ArrayList<Player> tempArrayList = new ArrayList<Player>();
        for (Player p : game.getKnownPlayers()) {
            tempArrayList.add(p);
        }
        Settings settings = new Settings();
        game = new Game(settings);
        game.setKnownPlayers(tempArrayList);
        settingsPanel = new SettingsPanel(game);
        //communicationPanel = new CommunicationPanel(game);
        //actionPanel = new ActionPanel(game);
        communicationPanel.playerLabels.initialize();
        mainPanel.add(settingsPanel, "settingsPanel");
        cardLayout.show(mainPanel, "settingsPanel");


        actionPanel.startButton.setEnabled(true);
    }

}


