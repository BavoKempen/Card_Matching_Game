package settings;

import model.Game;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.util.Hashtable;

public class DifficultyLevel extends JPanel {

    private Game game;

    //Label
    private JLabel difficultyLabel;

    //Slider
    private JSlider difficultySlider;
    private DefaultBoundedRangeModel sliderModel;
    private Hashtable positionLabels;

    //variable to store difficulty, initiate as 1, otherwise default = 0 and will cause error
    int difficultyInt = 1;


    public DifficultyLevel(Game game){

        this.game = game;

        //Label
        difficultyLabel = new JLabel("Difficulty Level");

        //Slider
        //sliderModel = new DefaultBoundedRangeModel(1,1,4);
        difficultySlider = new JSlider(1,2,1); //slider with our values
        difficultySlider.setMajorTickSpacing(1); //put ticks on slider (1-4)
        difficultySlider.setPaintTicks(true); //make the ticks visible

        //labels to be put on the ticks
        difficultySlider.setPaintLabels(true);

        //make table with labels and respective position
        Hashtable<Integer, JLabel> positionLabels = new Hashtable<Integer, JLabel>();
        positionLabels.put(1, new JLabel("EZ"));
        positionLabels.put(2, new JLabel("PZ"));
        //positionLabels.put(3, new JLabel("GG"));
        //positionLabels.put(4, new JLabel("WP"));
        //add labels to respective spot on slider
        difficultySlider.setLabelTable(positionLabels);

        //Layout of the panel
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();


        //Label
        c.gridx = 0;
        c.gridy = 0;
        add(difficultyLabel, c);

        //Button player one and player two
        c.gridy = 1;
        add(difficultySlider, c);

        //add listener to slider
        difficultySlider.addChangeListener(new difficultyChangeListener());





    }

    public class difficultyChangeListener implements ChangeListener {
        @Override
        public void stateChanged(ChangeEvent eee) {
            JSlider sourceSlider = (JSlider) eee.getSource();
            game.getSettings().setDifficulty(sourceSlider.getValue());


        }
    }
}
