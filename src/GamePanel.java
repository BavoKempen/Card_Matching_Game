import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GamePanel extends JPanel implements ActionListener {

    private int rowInteger;
    private int columnInteger;

    public GamePanel(){

        setBackground(Color.BLUE);
        add(new JLabel("Game"));

        JButton button = new JButton("test");
        add(button);

        button.addActionListener(this);







    }
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(500, 400);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        System.out.println("test test");
    }
}
