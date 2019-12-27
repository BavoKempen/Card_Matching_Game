package settings;

import model.Game;
import view.PlayerLabels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/*
In Sum:
    1) Player selects single or multiplayer (pve/pvp) through JRadiobutton
        a) RadioButton: select one of either options
        b) Has effect on whether second JTextfield is editable
    2) Retrieve text from textfield(s) dependent on chosen option in 1) by user
        a) Actionlistener on textfields to remove ";" and "-" so that user cannot use them in their name.
        As such, avoiding problems with load in/write of highscores to database (highScores.txt).
        b) Send changes in textfields to settings so that we can retrieve info in the PlayerLabels panel, highScoresPanel

 */

public class PveOrPvp extends JPanel {

    // Used to pass information
    private Game game;

    // Vs computer or vs other human
    // Labels
    private JLabel playModeLabel;
    private JLabel nameLabel;

    // Create radiobuttons and put them in the group so that only one can be selected at any given time
    // Buttons
    JRadioButton pveButton;
    JRadioButton pvpButton;
    private ButtonGroup playModeButtonGroup;

    // Text fields
    public JTextField playerOne;
    public JTextField playerTwo;

    private PlayerLabels playerLabels;


    public PveOrPvp(Game game){

        // Pass info
        this.game = game;

        // Computer vs. human with radio buttons
        playModeLabel = new JLabel("Game Mode");
        nameLabel = new JLabel("Player Name");

        // Buttons + group (so that only one can be clicked at a given time)
        pveButton = new JRadioButton("PvE", true); //default on PVE
        pveButton.setHorizontalTextPosition(SwingConstants.LEFT); //label of button left

        pvpButton = new JRadioButton("PvP");
        pvpButton.setHorizontalTextPosition(SwingConstants.LEFT);

        playModeButtonGroup = new ButtonGroup();

        // Text fields for player names
        playerOne = new JTextField(20);
        playerTwo = new JTextField(20);

        // Add keylisteners to jtextfields so that ";" and "-" cannot be used in the username.
        // Symbols are removed from field when user writes them.
        playerOne.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char ch = e.getKeyChar();
                if (ch == KeyEvent.VK_SEMICOLON || ch == KeyEvent.VK_MINUS){
                    e.consume();
                }
            }
        });

        playerTwo.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char ch = e.getKeyChar();
                if (ch == KeyEvent.VK_SEMICOLON || ch == KeyEvent.VK_MINUS){
                    e.consume();
                }
            }
        });


        //join buttons in button group so that only one can be selected at any given time
        playModeButtonGroup.add(pveButton);
        playModeButtonGroup.add(pvpButton);

        //Layout of the panel
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();


        //Label
        c.gridx = 0;
        c.gridy = 0;
        add(playModeLabel, c);

        //Button player one and player two
        c.gridy = 1;
        add(pveButton, c);

        c.gridy = 2;
        add(pvpButton, c);

        //name label
        c.gridx = 1;
        c.gridy = 0;
        add(nameLabel,c);

        //text fields for names of player one and possibly player two
        c.gridy = 1;
        add(playerOne,c);

        c.gridy = 2;
        add(playerTwo, c);

        // Align everything to the left
        c.anchor=GridBagConstraints.WEST;

        // Action Listeners
        // Add SAME action listener to buttons, so that switching in-between (de)activates the player two text field
        pveButton.addActionListener(new EnableListener());
        pvpButton.addActionListener(new EnableListener());

        // Default on PVE, so deselect the playertwo text field a priori
        playerTwo.setEnabled(false);
    }

    // Save names to Player object in players ArrayList in the settings
    public void saveSettings(){
        String nameOne = playerOne.getText();
        String nameTwo = playerTwo.getText();

        if (nameOne.length() !=0 ){
            game.getSettings().getPlayers().get(0).setName(nameOne);
        }

        if(nameTwo.length() !=0 ){
            game.getSettings().getPlayers().get(1).setName(nameTwo);
        }
    }

    // The actionlistener for the radiobuttons and communication of their selection with settings
    private class EnableListener implements ActionListener { // Control textfields according to radiobutton pressed (pvp/pve)
        @Override
        public void actionPerformed(ActionEvent actionEvent) { // Make playerTwo textfield writable after selecting pvp
            // AND clear it again if pve is reselected
            if(pvpButton.isSelected()) {
                playerTwo.setEnabled(true);
                game.getSettings().setSinglePlayer(false);
            }

            else if (pveButton.isSelected()){
                playerTwo.setEnabled(false);
                playerTwo.setText(""); //Delete second name if switched back to PVE
                game.getSettings().setSinglePlayer(true);
            }
        }
    }
}
