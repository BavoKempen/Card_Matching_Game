import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionPanel extends JPanel{

    SettingsPanel settingsPanel;
    JButton startButton;
    JButton exitButton;
    JButton rulesButton;

    public ActionPanel(){
        startButton = new JButton("Start Game");
        exitButton = new JButton("End Game");
        rulesButton = new JButton("Game Rules");

        //Layout of the panel
        setLayout(new FlowLayout());
        setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

        add(startButton);
        add(exitButton);
        add(rulesButton);

        rulesButton.addActionListener(new OpenRules());



    }



    }

