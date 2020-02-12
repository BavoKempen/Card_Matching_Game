package settings;

import model.Game;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

/*
In sum:
        1) JPanel with JSpinners in through which the user can communicate the amount of rows and columns
            a) Max: 6 (i.e., 36 JButtons)
            b) Min: 2 so that always at least 2x2 grid (2 turns)
            c) Information is passed from changelisteners through game to settings for usage in gamePanel construction.

 */

public class RowsAndColumns extends JPanel {

    // Pass and retrieve information
    private Game game;

    // JSpinners
    JSpinner rowSpinner;
    JSpinner columnSpinner;

    // Jlabels
    private JLabel rowLabel;
    private JLabel columnLabel;
    private JLabel gridLabel;

    public RowsAndColumns(Game game){

        // Pass info
        this.game = game;

        // Flexible
        setLayout(new GridBagLayout());

        //labels
        rowLabel = new JLabel("Rows");
        columnLabel = new JLabel("Columns");
        gridLabel = new JLabel("Dimensions of Game Board");

        // Create Spinners with a minimum of 2 and maximum of 6, starting at 4 as default and in/decrement 1
        // 4 indicates default rows and columns
        rowSpinner = new JSpinner(new SpinnerNumberModel(4,2,6,1));
        ((JSpinner.DefaultEditor) rowSpinner.getEditor()).getTextField().setEditable(false); // Only changed via buttonclick

        columnSpinner = new JSpinner(new SpinnerNumberModel(4,2,6,1));
        ((JSpinner.DefaultEditor) columnSpinner.getEditor()).getTextField().setEditable(false);


        //Layout of the panel
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();


        //Label
        c.gridx = 0;
        c.gridy = 0;
        add(gridLabel, c);

        // Labels of rows/columns
        c.gridy = 1;
        add(rowLabel, c);

        c.gridy = 2;
        add(columnLabel, c);


        // The actual "spinners"
        c.gridx = 1;
        c.gridy = 1;
        add(rowSpinner,c);

        c.gridy = 2;
        add(columnSpinner, c);

        // Align to the left
        c.anchor=GridBagConstraints.WEST;

        // Spinner changelisteners: Pass changes in rows/columns from user -> settings
        rowSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e2) {
                JSpinner rowSpinner = (JSpinner) e2.getSource();
                game.getSettings().setRows((int) rowSpinner.getValue());
            }

        });

        columnSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JSpinner columnSpinner = (JSpinner) e.getSource();
                game.getSettings().setColumns((int) columnSpinner.getValue());
            }
        });
    }
}
