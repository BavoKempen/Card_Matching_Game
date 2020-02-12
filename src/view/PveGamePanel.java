package view;

import game.CardButton;
import model.Game;
import java.util.ArrayList;
import java.util.Random;

/*
In Sum:
        1) Most of game Logic is the same in a person vs. person or person vs. computer game
            a) Extends from GamePanel (i.e., copies class, unless changed in extension)
            b) @Override useful to adjust methods to second player as computer
            c) method playAITurn():
                i) NOTE: This name in no way means that I implemented an ML algorithm or AI in the game.
         2) This gamePanel is chosen after the startButton is clicked in gameOn() method in the MainFrame class
          if settings indicated that the user wanted a single player game (i.e., pve)

 */

public class PveGamePanel extends GamePanel {

    // Pass information and access to communicationPanel
    private Game game;
    private CommunicationPanel communicationPanel;

    // For Randomization in PlayAITurn() method
    private Random randomNumber = new Random();

    // Needed in PlayAITurn(), serves as temporary container for two numbers
    private ArrayList<Integer> cardSelectList = new ArrayList<Integer>();


    public PveGamePanel(Game game, CommunicationPanel communicationPanel) {

        // Extends from ...
        super(game, communicationPanel);

        // Pass information, establish connection
        this.game = game;
        this.communicationPanel = communicationPanel;

    }

    // Given that AI is set as default name in settings for the game if getSinglePlayer() == true and thus pve button is selected
    @Override
    public void nextPlayer() {

        // If the current player is playerOne (human), change it to playerTwo (computer)
        if (currentPlayer == game.getSettings().getPlayers().get(0)) {

            currentPlayer = game.getSettings().getPlayers().get(1);
        }

        // Reversed
        else {

            currentPlayer = game.getSettings().getPlayers().get(0);

        }

        // If this player is called AI AND the single game mode is on (to be sure)
        if (currentPlayer.getName() == "AI" && game.getSettings().getSinglePlayer()) {

            // implement AI logic method for its turn
            this.playAITurn();

        }

        // Refresh PlayerLabels JPanel and set active player to the current player
        this.communicationPanel.playerLabels.setActivePlayer(currentPlayer);
        this.communicationPanel.initialize();

    }

    // AI logic
    public void playAITurn() {

        // Initialize Integer ArrayList
        cardSelectList = new ArrayList<Integer>();

        /*
        1) The computer selects two numbers at random within the range of the icons
            a) If these buttons were not yet set to matched (true) (i.e., other player found them) add the two numbers to
            the ArrayList of integers (cardSelectList)
            b) We determine CardButtons by using the random generated numbers as their respective index in the cardsList
            c) If two numbers found, .doClick which is the programmed version of a JButton click with mouse.
         */

        while (cardSelectList.size() < 2){

            // Generate two random numbers
            int index = randomNumber.nextInt(cardsList.size());
            int index2 = randomNumber.nextInt(cardsList.size());

            // Add them to the list if not yet found
            if (this.cardsList.get(index).getMatched()==false && this.cardsList.get(index2).getMatched() == false && index != index2){
                cardSelectList.add(index);
                cardSelectList.add(index2);
            }
        }

        // Simulate click on cardButton
        this.cardsList.get(cardSelectList.get(0)).doClick();
        this.cardsList.get(cardSelectList.get(1)).doClick();

        // Empty the list for the next turn of the AI
        this.cardSelectList.removeAll(cardSelectList);
    }

    // Override because computer/AI should not be saved to highScores database as specified in guidelines
    @Override
    public void setWinner(){

        // Same as in gamePanel
        if (game.getSettings().getPlayers().get(0).getScore() > game.getSettings().getPlayers().get(1).getScore()){

            // Normalize score
            game.getSettings().getPlayers().get(0).setScore(game.getSettings().getPlayers().get(0).getScore()/((this.game.getSettings().getRows()*this.game.getSettings().getColumns())/2));

            game.addToKnownPlayers(game.getSettings().getPlayers().get(0));
            game.getSettings().setIntWinner(0);
        }

        else if (game.getSettings().getPlayers().get(0).getScore() < game.getSettings().getPlayers().get(1).getScore()){

            // No addition to Player Highscore database
            game.getSettings().setIntWinner(1);

        }

        else {
            // Normalize score
            game.getSettings().getPlayers().get(0).setScore(game.getSettings().getPlayers().get(0).getScore()/((game.getSettings().getRows()*game.getSettings().getColumns())/2));

            // Only add human to database
            game.addToKnownPlayers(game.getSettings().getPlayers().get(0));
            game.getSettings().setDraw(true);

        }
    }
}
