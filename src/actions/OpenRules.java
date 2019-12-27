package actions;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

/*
An Actionlistener class of JButton click that opens textfile upon button click
 */
public class OpenRules implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        // Avoid errors
        try {

            File myFile = new File("Files/RULES.pdf");
            myFile.setWritable(false);

            Desktop.getDesktop().open(myFile);
            //Desktop.getDesktop().open(new java.io.File("Files/RULES.pdf"));

        } catch (IOException e) {

            e.printStackTrace();

        }
    }
}
