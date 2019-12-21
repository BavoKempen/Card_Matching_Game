package view;

import model.Game;
import model.Player;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.security.PrivateKey;

public class HighScoresPanel extends JPanel {

    private Game game;

    private JLabel highScoresLabel;
    private JScrollPane highScoresPane;
    private JTable highScoresFill;

    public HighScoresPanel(Game game){

        this.game = game;

        highScoresLabel = new JLabel("HighScores", SwingConstants.CENTER);

        String columnNames[]= {"Rank", "Player Name", "Score"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0){
            @Override
            public boolean isCellEditable(int row, int column) {
            //all cells false
                return false;
            }
        };

        for (int i = 0; i < game.getKnownPlayers().size(); i++){
            int rank = i +1;
            String playerName = game.getKnownPlayers().get(i).getName();
            int playerScore = game.getKnownPlayers().get(i).getScore();

            Object[] dataRows = {rank, playerName, playerScore};
            tableModel.addRow(dataRows);
        }



        highScoresFill = new JTable(tableModel);
        highScoresPane = new JScrollPane(highScoresFill);

        highScoresPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        highScoresPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        highScoresLabel.setPreferredSize(new Dimension(180,50));
        highScoresLabel.setFont(new Font("Serif", Font.BOLD,30));


        highScoresPane.setPreferredSize(new Dimension(180, 180));

        add(highScoresLabel, BorderLayout.NORTH);
        add(highScoresPane, BorderLayout.CENTER);




    }
}
