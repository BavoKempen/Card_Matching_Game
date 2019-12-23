package settings;

import model.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ThemesSelection extends JPanel {

    private Game game;


    //Label
    private JLabel themesLabel;

    //buttons
    ButtonGroup themesButtonGroup;
    JRadioButton shoesButton;
    JRadioButton mathsButton;
    JRadioButton worldLeadersButton;
    String gameTheme = "shoes";


    public ThemesSelection(Game game){

        this.game = game;

        //Label assign text
        themesLabel = new JLabel("Theme:");

        //Buttons
        shoesButton = new JRadioButton("Shoes");
        worldLeadersButton = new JRadioButton("World Leaders");
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


        //Label
        c.gridx = 0;
        c.gridy = 0;
        add(themesLabel, c);

        //Button player one and player two
        c.gridy = 1;
        add(shoesButton, c);

        //text fields for names of player one and possibly player two
        c.gridy = 2;
        add(worldLeadersButton,c);
        c.anchor=GridBagConstraints.NORTHWEST;


        //actual listeners
        //append value to string variable to later insert at Start button to choose the theme
        shoesButton.setSelected(true);

    }
    public class ThemeListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ee) {


            if (shoesButton.isSelected())
                game.getSettings().setTheme("shoes");

            else if (worldLeadersButton.isSelected())
                game.getSettings().setTheme("worldleaders");

        }
    }


}
