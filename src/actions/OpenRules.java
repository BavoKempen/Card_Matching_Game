package actions;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/*
An Actionlistener class of JButton click that opens textfile upon button click
 */
public class OpenRules implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        // Avoid errors
        try {

            Desktop.getDesktop().open(new java.io.File("Files/RULES.txt"));

        } catch (IOException e) {

            e.printStackTrace();

        }
    }
}
