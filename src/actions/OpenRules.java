package actions;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class OpenRules implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        try {
            Desktop.getDesktop().open(new java.io.File("Files/RULES.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
