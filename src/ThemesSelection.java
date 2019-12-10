import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ThemesSelection extends JPanel {


    //Label
    private JLabel themesLabel;

    //buttons
    ButtonGroup themesButtonGroup;
    JRadioButton shoesButton;
    JRadioButton mathsButton;
    JRadioButton worldLeadersButton;
    String gameTheme = "shoes";


    public ThemesSelection(){

        //Label assign text
        themesLabel = new JLabel("Theme:");

        //Buttons
        shoesButton = new JRadioButton("Shoes");
        mathsButton = new JRadioButton("Mathematics");
        worldLeadersButton = new JRadioButton("World Leaders");
        themesButtonGroup = new ButtonGroup();

        //join buttons
        themesButtonGroup.add(shoesButton);
        themesButtonGroup.add(mathsButton);
        themesButtonGroup.add(worldLeadersButton);

        //Action Listeners
        shoesButton.addActionListener(new ThemeListener());
        mathsButton.addActionListener(new ThemeListener());
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

        c.gridy = 2;
        add(mathsButton, c);


        //text fields for names of player one and possibly player two
        c.gridy = 3;
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
                gameTheme = "shoes";
            else if (mathsButton.isSelected())
                gameTheme = "mathematics";
            else if (worldLeadersButton.isSelected())
                gameTheme = "worldleaders";




        }
    }


}
