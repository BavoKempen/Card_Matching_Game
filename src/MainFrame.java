import com.sun.tools.javac.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.JFrame.*;

public class MainFrame extends JFrame {

    ActionPanel actionPanel;
    SettingsPanel settingsPanel;
    GamePanel gamePanel;
    CommunicationPanel communicationPanel;

    JPanel mainPanel;
    CardLayout cardLayout;

    /*
    The MainFrame() should be maximum screen according to user's dimensions
    Three Panels needed in Layout:
    (1) Center: SettingsPanel, GamePanel in mainPanel linked to startButton
    (2) Bottom: ActionPanel: Start, Stop, Rules
    (3) Line_end: RealTimePanel: Respective Players, Top5Highscore, Timer, ...
     */
    public MainFrame(){
        super("Memory Game");

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        settingsPanel = new SettingsPanel();
        gamePanel = new GamePanel();
        mainPanel.add(settingsPanel, "settingsPanel");
        mainPanel.add(gamePanel, "gamePanel");

        actionPanel = new ActionPanel();
        communicationPanel = new CommunicationPanel();


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



        //SettingsPanel contains all user selected input

        //Add all the actionListeners to extract values of settings panel
        actionPanel.startButton.addActionListener(this::actionPerformed);
        actionPanel.exitButton.addActionListener(new CloseOnExit());







    }

    //TODO: start button should become unclickable until game is finished

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == actionPanel.startButton) {
            int rows = (int) settingsPanel.rowsAndColumns.rowSpinner.getValue();
            int columns = (int) settingsPanel.rowsAndColumns.columnSpinner.getValue();
            String theme = settingsPanel.themesSelection.gameTheme;
            int difficulty = settingsPanel.difficultyLevel.difficultyInt;

            if (settingsPanel.pveOrPvp.pveButton.isSelected()){
                String playerOneName = settingsPanel.pveOrPvp.playerOne.getText();
                String playertwoName = "Computer"; //TO DO: Make this something that people cannot break, e.g., hash string

            }
            else if (settingsPanel.pveOrPvp.pvpButton.isSelected()){
                String playerOneName = settingsPanel.pveOrPvp.playerOne.getText();
                String playertwoName = settingsPanel.pveOrPvp.playerTwo.getText();
            }


            // TO DO: Remove settings Panel and add GamePanel

        }
        gameOn();

    }
    public void gameOn(){

        cardLayout.show(mainPanel, "gamePanel");

    }
}


