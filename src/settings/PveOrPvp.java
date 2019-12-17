package settings;

import model.Game;
import model.Settings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PveOrPvp extends JPanel {

    private Game game;

    // Computer vs other human
    //Labels
    private JLabel playModeLabel;
    private JLabel nameLabel;

    //create radiobuttons

    //Buttons
    JRadioButton pveButton;
    JRadioButton pvpButton;
    private ButtonGroup playModeButtonGroup;

    //text fields
    public JTextField playerOne;
    public JTextField playerTwo;

    public PveOrPvp(Game game){

        this.game = game;
        // Computer vs. human with radio buttons
        playModeLabel = new JLabel("Game Mode");
        nameLabel = new JLabel("Player Name");

        //Buttons + group (so that only one can be clicked at a given time)
        pveButton = new JRadioButton("PVE", true); //default on PVE
        pveButton.setHorizontalTextPosition(SwingConstants.LEFT); //label of button left
        pvpButton = new JRadioButton("PVP");
        pvpButton.setHorizontalTextPosition(SwingConstants.LEFT);
        playModeButtonGroup = new ButtonGroup();
        // Text fields for player names
        playerOne = new JTextField(20);
        playerTwo = new JTextField(20);

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

        //align everything to the left
        c.anchor=GridBagConstraints.WEST;
        //Action Listeners
        //add SAME action listener to buttons, so that switching inbetween (de)activates the player two text field
        pveButton.addActionListener(new EnableListener());
        pvpButton.addActionListener(new EnableListener());

        //TEXT field recordings will be included in "START" button I think

        //default on PVE, so deselect the playertwo text field a priori
        playerTwo.setEnabled(false);
    }

    private class EnableListener implements ActionListener { //control textfields according to radiobutton pressed (pvp/pve)
        @Override
        public void actionPerformed(ActionEvent actionEvent) { //make playerTwo textfield writable after selecting pvp AND clear it again if pve is reselected
            if(pvpButton.isSelected()) {
                playerTwo.setEnabled(true);
                game.getSettings().setComputerOrMultiplayer(false);
            }



            else if (pveButton.isSelected()){
                playerTwo.setEnabled(false);
                playerTwo.setText(""); //Delete second name if switched back to PVE
                game.getSettings().setComputerOrMultiplayer(true);
            }
        }



    }
}
