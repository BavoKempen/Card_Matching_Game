package settings;

import model.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ThemesSelection extends JPanel {

    // Pass info
    private Game game;

    // Label
    private JLabel themesLabel;

    // Buttons
    ButtonGroup themesButtonGroup;
    JRadioButton shoesButton;
    JRadioButton worldLeadersButton;

    public ThemesSelection(Game game){

        this.game = game;

        // Label assign text
        themesLabel = new JLabel("Theme:");

        // Buttons
        shoesButton = new JRadioButton("Shoes");
        worldLeadersButton = new JRadioButton("World Leaders");

        // Group buttons so that only one can be selected
        themesButtonGroup = new ButtonGroup();

        //join buttons
        themesButtonGroup.add(shoesButton);
        themesButtonGroup.add(worldLeadersButton);

        //Action Listeners
        shoesButton.addActionListener(new ThemeListener());
        worldLeadersButton.addActionListener(new ThemeListener());

        //Layout of the panel
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        // Make following components align northwest
        c.anchor=GridBagConstraints.NORTHWEST;

        // Label
        c.gridx = 0;
        c.gridy = 0;
        add(themesLabel, c);

        // Buttons
        c.gridy = 1;
        add(shoesButton, c);

        c.gridy = 2;
        add(worldLeadersButton,c);


        // Actual listeners
        // Append value to string variable in settings through game to later use in construction game
        // Default selected, also specified as such in settings
        shoesButton.setSelected(true);

    }

    private class ThemeListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ee) {

            if (shoesButton.isSelected())
                game.getSettings().setTheme("shoes");

            else if (worldLeadersButton.isSelected())
                game.getSettings().setTheme("worldleaders");

        }
    }
}
