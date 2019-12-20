package view;

import javafx.scene.effect.Blend;
import model.Game;
import model.Player;
import model.Settings;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class RunMemoryGame {
    public static void main (String[] args){

        SwingUtilities.invokeLater(new Runnable() { //To make it more robust, asynchronously queues the constructor on
            // event dispatching thread. Thank you YouTube!

            public void run() {
                /*add default game settings

                 */
                Settings settings = new Settings();
                Game game = new Game(settings);
                game.setKnownPlayers(loadHighScores());
                new MainFrame(game); //create new class of view.MainFrame type defined in respective class where the rest can be added

            }
        });

    }

    private static ArrayList<Player> loadHighScores(){
        String filePath = "Files/highScores/highScores.txt";

        StringBuilder highScoreBuilder = new StringBuilder();
        String result;
        ArrayList<Player> players = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath)))
        {

            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null)
            {
                highScoreBuilder.append(sCurrentLine).append("\n");
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        result = highScoreBuilder.toString();

        String temp = "";
        String name = "";
        int score = 0;
        for(int i = 0; i<result.length(); i++){
            if (result.charAt(i)=='-'){
                name = temp;
                temp = "";

                i++;
            } else if (result.charAt(i)==';') {
                score = Integer.parseInt(temp);
                temp = "";

                System.out.println(name);
                System.out.println(score);

                Player player = new Player(name);
                player.setScore(score);
                players.add(player);

                i++;
            }
            temp += result.charAt(i);

        }

        // Sort if you want

        return players;
    }

}
