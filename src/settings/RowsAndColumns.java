package settings;

import model.Game;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class RowsAndColumns extends JPanel {

    private Game game;



    //spinners
    JSpinner rowSpinner;
    JSpinner columnSpinner;

    //labels
    private JLabel rowLabel;
    private JLabel columnLabel;
    private JLabel gridLabel;

    public RowsAndColumns(Game game){

        this.game = game;

        setLayout(new GridBagLayout());

        //labels
        rowLabel = new JLabel("Rows");
        columnLabel = new JLabel("Columns");
        gridLabel = new JLabel("Size of Game Panel");

        //create Spinners with a minimum of 1 and maximum of 10, starting at 4 as default and in/decrement 1
        rowSpinner = new JSpinner(new SpinnerNumberModel(4,1,10,1));
        ((JSpinner.DefaultEditor) rowSpinner.getEditor()).getTextField().setEditable(false);

        columnSpinner = new JSpinner(new SpinnerNumberModel(4,1,10,1));
        ((JSpinner.DefaultEditor) columnSpinner.getEditor()).getTextField().setEditable(false);


        //Layout of the panel
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();


        //Label
        c.gridx = 0;
        c.gridy = 0;
        add(gridLabel, c);

        //Button player one and player two
        c.gridy = 1;
        add(rowLabel, c);

        c.gridy = 2;
        add(columnLabel, c);


        //text fields for names of player one and possibly player two
        c.gridx = 1;
        c.gridy = 1;
        add(rowSpinner,c);

        c.gridy = 2;
        add(columnSpinner, c);

        //align everything to the left
        c.anchor=GridBagConstraints.WEST;

        //spinner changelisteners to (1) constrain manual input to min and max of JSpinners, and (2) Extract info (i.e., rows/columns) to use for the construction of gameGUI
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
