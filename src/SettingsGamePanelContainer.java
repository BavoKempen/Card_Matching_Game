/*
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsGamePanelContainer extends JPanel implements ActionListener {
    SettingsPanel settingsPanel = new SettingsPanel();
    GamePanel gamePanel = new GamePanel;
    ActionPanel actionPanel;


    public SettingsGamePanelContainer(){
        add(settingsPanel);
        setLayout(new GridLayout(2,2));
        //Add all the actionListeners to extract values of settings panel
        actionPanel.startButton.addActionListener(this::actionPerformed);

    }
    //TODO: start button should become unclickable until game is finished

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == actionPanel.startButton) {
            int rows = (int) settingsPanel.rowsAndColumns.rowSpinner.getValue();
            int columns = (int) settingsPanel.rowsAndColumns.columnSpinner.getValue();
            String theme = settingsPanel.themesSelection.gameTheme;
            int difficulty = settingsPanel.difficultyLevel.difficultyInt;

            if (settingsPanel.pveOrPvp.pveButton.isSelected()) {
                String playerOneName = settingsPanel.pveOrPvp.playerOne.getText();
                String playertwoName = "Computer"; //TO DO: Make this something that people cannot break, e.g., hash string

            } else if (settingsPanel.pveOrPvp.pvpButton.isSelected()) {
                String playerOneName = settingsPanel.pveOrPvp.playerOne.getText();
                String playertwoName = settingsPanel.pveOrPvp.playerTwo.getText();
            }
            remove(settingsPanel);
            add(gamePanel);



            // TO DO: Remove settings Panel and add GamePanel

        }
        repaint();
        revalidate();

    }}
*/