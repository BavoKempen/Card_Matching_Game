package ApplicationStartUp;

import javafx.scene.effect.Blend;
import model.Game;
import model.Player;
import model.Settings;
import view.MainFrame;

import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

/*
RunMemoryGame class encapsulates the main method from which the application is started AND contains a method that loads
in the data from our database.
 */

public class RunMemoryGame {
    public static void main (String[] args){

        /*
        Try and catch: Collects available LAF's on system and selects the "Nimbus" LAF, provided that it
        was installed which should come by default with installation Java SE 6 Update 10 (6u10) or above. Else: Default LAF is used (i.e., Crossplatform LAF).
        https://docs.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */

        try {
            for(LookAndFeelInfo info: UIManager.getInstalledLookAndFeels()){
                if ("Nimbus".equals(info.getName())){
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
        }

        /*
        The SwingUtilities.invokeLater method was used due to Swing's "single-threaded" characteristics. Make sure
        UI construction and updates happen consequently on the Event Dispatch Thread (EDT), making the application more
        robust in general (https://www.javamex.com/tutorials/threads/invokelater.shtml + YouTube).
         */

        SwingUtilities.invokeLater(new Runnable() {

            public void run() {

                /*
                1) Settings(): Class filled with getters and setters to store and pass information between the different
                components of the GUI through game(). It is initialized with respective default values (cf. Settings()).
                2) Game(): This Class is passed between all relevant GUI components etc. and contains a method getSettings()
                which allows information to be passed back and forth to Settings() until needed
                3) game.setKnownPlayers(loadHighScores()): Sets players stored in highscores.txt database into an ArrayList
                that consists of Player objects. The latter consists of the name and respective score.
                4) new MainFrame(game): The main container which extends a JFrame and within this class the subsequent
                GUI components are built. It also receives "game" in its constructor so that information can be passed.
                 */

                Settings settings = new Settings();
                Game game = new Game(settings);
                game.setKnownPlayers(loadHighScores());
                new MainFrame(game); //create new class of view.MainFrame type defined in respective class where the rest can be added

            }
        });


    }
    /*
    The loadHighScores method retrieves information from the database (highScores.txt) and parses it to Player objects.
     */
    private static ArrayList<Player> loadHighScores(){

        //Database path in project.
        String filePath = "Files/highScores/highScores.txt";

        /*
        String objects are immutable sequences of characters, StringBuilder allows for mutations
        (https://www.geeksforgeeks.org/stringbuilder-class-in-java-with-examples/). We append the output
        of the BufferedReader that "scans" over the .txt file line. Thereafter, the whole String is stored in the String
        result ready to be broken down into Player objects.
        NOTE: Information in the highScores.txt file is stored as: name-score;
        */
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

        /*
        The following for loop will iterate over the String result object that contains all the name-score pairs of the
        highScores.txt. Characters are continuously added until "-" or ";" is encountered. The insofar gathered String
        stored in temp will then be stored in name, cleared and filled with the number that is then stored in score. As
        soon as name-points pair is gathered, they are joined in a Player object that are added to an ArrayList for storage.
        i++ is added after both if/if else to jump to the next character and not get stuck in infinite loop.
         */

        String temp = "";
        String name = "";
        double score = 0;

        for(int i = 0; i<result.length(); i++){
            if (result.charAt(i)=='-'){
                name = temp;
                temp = "";

                i++;
            } else if (result.charAt(i)==';') {
                score = Double.parseDouble(temp);
                temp = "";

                Player player = new Player(name);
                player.setScore(score);
                players.add(player);

                i++;
            }
            temp += result.charAt(i);

        }

        // Before returning the players ArrayList<Player> we sort it descending on score. Default comparing sort is
        // ascending (thus .reversed()).
        players.sort(Comparator.comparing(Player::getScore).reversed());

        //Sorted ArrayList ready to be passed to game which holds the knownPlayers (i.e., highscores) throughout the game.
        return players;
    }

}
