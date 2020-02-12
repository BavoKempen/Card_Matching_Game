package settings;

import model.Game;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.util.Hashtable;

/*
In sum:
    1) User selects difficulty level of game with JSlider which is placed in a JPanel as container
        a) easy -> no bombs in game
        b) medium -> bombs in game
        c) difficult -> multiple bombs in the game

 */

public class DifficultyLevel extends JPanel {

    // To pass and retrieve information
    private Game game;

    //Label to indicate name
    private JLabel difficultyLabel;

    //Slider: Component that will be used for information transmission from user -> game
    private JSlider difficultySlider;

    public DifficultyLevel(Game game){

        this.game = game;

        // Label
        difficultyLabel = new JLabel("Difficulty Level");

        // Slider
        difficultySlider = new JSlider(1,2,1); // slider with our values
        difficultySlider.setMajorTickSpacing(1); // put ticks on slider (1-4)
        difficultySlider.setPaintTicks(true); // make the ticks visible

        // Labels to be put on the ticks
        difficultySlider.setPaintLabels(true);

        // Make table with labels and respective position
        Hashtable<Integer, JLabel> positionLabels = new Hashtable<Integer, JLabel>();
        positionLabels.put(1, new JLabel("No Bomb Card"));
        positionLabels.put(2, new JLabel("Bomb Card"));

        // Add labels to respective spot on slider
        difficultySlider.setLabelTable(positionLabels);

        // Layout of the panel
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        // Label position
        c.gridx = 0;
        c.gridy = 0;
        add(difficultyLabel, c);

        // Button player one and player two
        c.gridy = 1;
        add(difficultySlider, c);

        // Add CHANGElistener to slider
        // Respective value in Settings gets updated whenever slider value changes (integer) and is thereafter used in
        // gamePanel construction (i.e., addition of bomb cards)
        difficultySlider.addChangeListener(new difficultyChangeListener());





    }

    public class difficultyChangeListener implements ChangeListener {

        // Override value when change happens, store it and communicate it to Settings of game
        @Override
        public void stateChanged(ChangeEvent eee) {

            JSlider sourceSlider = (JSlider) eee.getSource();
            game.getSettings().setDifficulty(sourceSlider.getValue());

        }
    }
}
