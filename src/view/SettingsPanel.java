package view;

import model.Game;
import settings.DifficultyLevel;
import settings.PveOrPvp;
import settings.RowsAndColumns;
import settings.ThemesSelection;

import javax.swing.*;
import java.awt.*;

public class SettingsPanel extends JPanel {

    private Game game;

    PveOrPvp pveOrPvp;
    RowsAndColumns rowsAndColumns;
    DifficultyLevel difficultyLevel;
    ThemesSelection themesSelection;

    public SettingsPanel(Game game){

        this.game = game;

        setLayout(new GridLayout(2,2));

        pveOrPvp = new PveOrPvp(game);
        rowsAndColumns = new RowsAndColumns(game);
        difficultyLevel = new DifficultyLevel(game);
        themesSelection = new ThemesSelection(game);



        add(pveOrPvp);
        add(rowsAndColumns);
        add(difficultyLevel);
        add(themesSelection);



    }
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(650, 400);
    }



}
