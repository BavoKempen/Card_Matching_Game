package view;

import model.Game;
import settings.DifficultyLevel;
import settings.PveOrPvp;
import settings.RowsAndColumns;
import settings.ThemesSelection;

import javax.swing.*;
import java.awt.*;

/*
SettingsPanel class serves as a container for all the components that will be used to retrieve settings through user input.
Added to MainFrame mainPanel.
 */

public class SettingsPanel extends JPanel {

    // Pass game to retrieve and put info
    private Game game;

    PveOrPvp pveOrPvp;
    RowsAndColumns rowsAndColumns;
    DifficultyLevel difficultyLevel;
    ThemesSelection themesSelection;

    public SettingsPanel(Game game){

        // Again pass object
        this.game = game;

        // Place in GridLayout for nice equal space for each component (4 subcomponents)
        setLayout(new GridLayout(2,2));

        // New and pass game info
        pveOrPvp = new PveOrPvp(game);
        rowsAndColumns = new RowsAndColumns(game);
        difficultyLevel = new DifficultyLevel(game);
        themesSelection = new ThemesSelection(game);

        // Add components to JPanel with GridLayout
        // They are also containers for their respective components
        add(pveOrPvp);
        add(rowsAndColumns);
        add(difficultyLevel);
        add(themesSelection);

    }
}
