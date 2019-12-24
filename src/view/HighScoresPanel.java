package view;

import model.Game;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/*
In sum:
        1) ArrayList of highScores made during RunMemoryGame() class or updated with the setWinner() method in the
        gamePanel class are added in a JTable.
            a) First created table model to add players row by row.
            b) Put in JScrollPane in the hope that the reader will play the game endlessly! Likewise, the list of highscores
            can extend endlessly.
 */

public class HighScoresPanel extends JPanel {

    // Pass info
    private Game game;

    private JLabel highScoresLabel;
    private JScrollPane highScoresPane;
    private JTable highScoresFill;

    public HighScoresPanel(Game game){

        // Pass/retrieve information
        this.game = game;

        // Instantiate JLabel
        highScoresLabel = new JLabel("HighScores", SwingConstants.CENTER);

        // Create columnNames String object that is put in the tableModel subsequently
        String columnNames[]= {"Rank", "Player", "Score"};

        // Instantiate the tableModel as Default, make the cells of the table non editable by user in GUI.
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0){

            @Override
            public boolean isCellEditable(int row, int column) {

                //all cells false
                return false;
            }
        };

        // Generate dataRows Object to be added iteratively to the tableModel
        // game.getKnownPlayers() is a getter connected to the ArrayList of Players' highscores
        for (int i = 0; i < game.getKnownPlayers().size(); i++){

            // rank + 1 because of 0 indexing in java
            int rank = i +1;
            String playerName = game.getKnownPlayers().get(i).getName();
            int playerScore = game.getKnownPlayers().get(i).getScore();

            // Equals predefined columnNames
            Object[] dataRows = {rank, playerName, playerScore};

            tableModel.addRow(dataRows);
        }


        // Create JTable component with the tableModel and then place this JTable into
        // a JScrollPane for infinite highscores.
        highScoresFill = new JTable(tableModel);
        highScoresPane = new JScrollPane(highScoresFill);

        // Always activate vertical scrolling, horizontal only when necessary
        highScoresPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        highScoresPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        // Give font type and size to the JLabel
        highScoresLabel.setFont(new Font("Serif", Font.BOLD,35));

        // Set relative size based on containers components are placed in
        highScoresLabel.setPreferredSize(new Dimension((int) Math.round((game.getSettings().getCommunicationPanelWidth()*.9)*.95), (int) Math.round((game.getSettings().getMainPanelHeight()*.45)*.20)));
        highScoresPane.setPreferredSize(new Dimension((int) Math.round((game.getSettings().getCommunicationPanelWidth()*.9)*.95), (int) Math.round((game.getSettings().getMainPanelHeight()*.45)*.75)));

        // BorderLayout suffices
        add(highScoresLabel, BorderLayout.NORTH);
        add(highScoresPane, BorderLayout.SOUTH);

    }
}
