package view;

import settings.DifficultyLevel;
import settings.PveOrPvp;
import settings.RowsAndColumns;
import settings.ThemesSelection;

import javax.swing.*;
import java.awt.*;

public class SettingsPanel extends JPanel {

    PveOrPvp pveOrPvp;
    RowsAndColumns rowsAndColumns;
    DifficultyLevel difficultyLevel;
    ThemesSelection themesSelection;

    public SettingsPanel(){

        setLayout(new GridLayout(2,2));

        pveOrPvp = new PveOrPvp();
        rowsAndColumns = new RowsAndColumns();
        difficultyLevel = new DifficultyLevel();
        themesSelection = new ThemesSelection();

        add(pveOrPvp);
        add(rowsAndColumns);
        add(difficultyLevel);
        add(themesSelection);



    }
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(500, 400);
    }


}
