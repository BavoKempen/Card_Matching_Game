import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CloseOnExit implements ActionListener {
    JDialog exitFrame;
    MainFrame mainFrame;
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        int confirmed = JOptionPane.showConfirmDialog(null,
                "Are you sure you want to exit the program?", "Exit Program Message Box",
                JOptionPane.YES_NO_OPTION);

        if (confirmed == JOptionPane.YES_OPTION) {
            exitFrame.dispose();
            mainFrame.dispose();

        } else
            exitFrame.dispose();

    }
}