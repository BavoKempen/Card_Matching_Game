package view;

import model.Game;
import model.Player;
import model.Settings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Comparator;

public class MainFrame extends JFrame {

    //Pass default info of backend
    private Game game;

    //UI subcontainers that are placed within the MainFrame.
    private ActionPanel actionPanel;
    private SettingsPanel settingsPanel;
    private GamePanel gamePanel;
    private CommunicationPanel communicationPanel;

    // mainPanel serves as a container for SettingsPanel and GamePanel that are swapped using CardLayout after JButton press.
    private JPanel mainPanel;
    private CardLayout cardLayout;


    // Game: Passed through for information access and transfer.

    public MainFrame(Game game){

        // Set title JFrame
        super("Memory Game");

        // Access game class to pass information to Settings.
        this.game = game;

        // JFrame's preferred size
        setPreferredSize(new Dimension(1000,600));

        // Pass preferred size to Settings through game.
        game.getSettings().setWindowHeight(600);
        game.getSettings().setWindowWidth(1000);

        // Initialize Cardlayout for the mainPanel.
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Declare Panels that will be placed in the container class MainFrame.
        // Pass game for information transfer and accessibility.
        settingsPanel = new SettingsPanel(game);
        actionPanel = new ActionPanel(game);
        communicationPanel = new CommunicationPanel(game);

        // Only add the settingsPanel to the mainPanel, gamePanel will be added later after user makes made choices in UI.
        // cf. GameOn() method
        mainPanel.add(settingsPanel, "settingsPanel");

        // Specify preferred sizes of JPanels relative to JFrame size. Respective info is stored a priori in Settings()
        communicationPanel.setPreferredSize(new Dimension((int) Math.round(game.getSettings().getCommunicationPanelWidth()), (int) Math.round(game.getSettings().getMainPanelHeight())));
        actionPanel.setPreferredSize(new Dimension((int) Math.round(game.getSettings().getActionAndMainPanelWidth()),(int) Math.round(game.getSettings().getActionPanelHeight())));
        mainPanel.setPreferredSize(new Dimension((int) Math.round(game.getSettings().getActionAndMainPanelWidth()), (int) Math.round(game.getSettings().getMainPanelHeight())));

        // Add panels to MainFrame using BorderLayout.
        add(mainPanel, BorderLayout.CENTER);
        add(actionPanel, BorderLayout.SOUTH);
        add(communicationPanel, BorderLayout.LINE_END);

        //Global GUI settings cf. documentation.
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setLocationByPlatform(true); // GUI in middle rather than top left
        setVisible(true);

        // Add actionListeners to start/exit/ restart button
        // They can be combined with e.getsource and their respective methods
        actionPanel.startButton.addActionListener(this::actionPerformed);
        actionPanel.exitButton.addActionListener(this::actionPerformed);
        actionPanel.restartButton.addActionListener(this::actionPerformed);

    }

    // Depending button that is clicked, an action is performed
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == actionPanel.startButton) {

            // Method defined underneath
            // Takes user input info from settingsPanel and swaps to gamePanel
            gameOn();

        }

        if (e.getSource() == actionPanel.exitButton){

            // Show dialog pop up when user wants to quit, only if again confirmed the application is closed
            int confirmed = JOptionPane.showConfirmDialog(null,"Are you sure you want to exit the Application?",
                    "Exit Application", JOptionPane.YES_NO_OPTION);
            if (confirmed == JOptionPane.YES_OPTION){
                System.exit(0);

            }
        }

        if (e.getSource() == actionPanel.restartButton){

            int confirmed = JOptionPane.showConfirmDialog(null,"Are you sure you want to exit the current game?",
                    "Exit Game", JOptionPane.YES_NO_OPTION);

            if (confirmed == JOptionPane.YES_OPTION){

                // Calls method defined underneath. Still threading issues.
                restartGame();

            }
        }
    }

    public void gameOn(){

        // Set name of players in the Settings
        this.settingsPanel.pveOrPvp.saveSettings();

        // Dependent of single vs multiplayer the gamePanel is initialized respectively and added to the
        // mainPanel
        if (game.getSettings().getSinglePlayer()) {
            gamePanel = new PveGamePanel(game, communicationPanel);
        } else {
            gamePanel = new GamePanel(game , communicationPanel);
        }

        mainPanel.add(gamePanel, "gamePanel");

        // Initialize the gamePanel with Settings info for the game
        // Reinitialize the communicationPanel to update names etc from default to user input
        this.gamePanel.initialize();
        this.communicationPanel.initialize();

        // Bring the gamePanel to the front and the settingsPanel to the back in mainPanel container
        cardLayout.show(mainPanel, "gamePanel");

        //Turn off start game button and turn on restart button (because game has started)
        actionPanel.startButton.setEnabled(false);
        actionPanel.restartButton.setEnabled(true);
    }

    public void restartGame(){

        // Create temporary ArrayList to store Player objects that derived from database on application start-up
        // and new winners since game is active. I do this because the game and settings classes have to be renewed and thus
        // stored info gets lost, including highscores info previously loaded.
        ArrayList<Player> tempArrayList = new ArrayList<Player>();
        for (Player p : game.getKnownPlayers()) {
            tempArrayList.add(p);
        }

        // Sort again in descending order
        tempArrayList.sort(Comparator.comparing(Player::getScore).reversed());

        Settings settings = new Settings();
        game = new Game(settings);
        game.setKnownPlayers(tempArrayList);

        // Close current and open new JFrame with again default settings
        this.dispose();
        new MainFrame(game);

    }
}


