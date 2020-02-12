package view;

import game.*;
import model.Card;
import model.Game;
import model.Player;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/*
    1) The GamePanel(game, communicationPanel) class is the most complex class and contains the game logic.
        a) Game is used to pass information within the GamePanel class and between numerous other classes and methods
        b) The method initialize() is called in MainFrame after startButton click
        c) GamePanel() only contains game logic of player versus player (pvp) games, instead against the computer is
        the class PveGamePanel extended from GamePanel(). As such, I could adjust the game logic with short @override in
        the PveGamePanel. Nonetheless, main game flow and logic is the same aside from user to user interaction and communication
        to highscores, ...
    2) Methods used during the game can be found underneath and communicate with game and settings through setters and getters

 */

public class GamePanel extends JPanel {

    // Info passed
    private Game game;

    // Declare final image
    private Image img;

    // Get default image to set on the back of the "cards"

    {
        try {
            img = ImageIO.read(new File("Files/backIcon/backIcon.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Make ImageIcon explicit a prior
    private ImageIcon backIcon = new ImageIcon(img);

    // This ArrayList is filled completely with all available cards, shuffled and then added to the game
    ArrayList<CardButton> cardsList;

    // Necessary during Game, serve as placeholders of CardButtons in game logic
    private CardButton selectedCard;
    private CardButton c1;
    private CardButton c2;
    private Timer t;

    // Relevant if difficulty with bombs was chosen
    private String bomb;

    // Rows and columns to keep code cleaner
    private int rows;
    private int columns;

    // To be able to communicate between gamePanel and communicationPanel
    private CommunicationPanel communicationPanel;

    // Package private, used to convey info of current player (score, name, activeplayer) to communicationPanel and used
    // for game logic.
    Player currentPlayer;



    public GamePanel(Game game, CommunicationPanel communicationPanel) {

        //reference game and communicationPanel info
        this.game = game;
        this.communicationPanel = communicationPanel;

    }

    // Contains actual game logic
    public void initialize() {

        // Get the rows and columns to pass later
        this.rows = game.getSettings().getRows();
        this.columns = game.getSettings().getColumns();

        // Set currentplayer to Player one (playerOne textfield in PveOrPvp), pass it to the communication panel so that
        // it is adjusted accordingly
        currentPlayer = game.getSettings().getPlayers().get(0);
        this.communicationPanel.playerLabels.setActivePlayer(currentPlayer);

        // Declare new cardsList (i.e., empty)
        cardsList = new ArrayList<>();

        // Joins together relevant path to bomb for in the checkCards() method and select bomb of correct theme
        bomb = String.join("/", "Files", "bomb", String.join("_", game.getSettings().getTheme(), "bomb.jpg"));

        // Initialize game class which selects the relevant CardDeck (theme, difficulty) through GameBoard(settings)
        // and the respective settings are up to date with user input.
        this.game.initialize();

        // For each loop that iterates over the card objects in the gameBoard and applies them to the Cardbuttons used
        // during the game. The default ImageIcon (cf. up) is set on the CardButtons and resized according to customized
        // method that can be found in the bottom of this class
        for (Card card : this.game.getBoard()){
            CardButton c = new CardButton(card);
            c.setIcon(resizeImageIcon(backIcon));

            // An actionListener is applied to each CardButton, and thus when clicked is turned around with the turnCard()
            // method. The selectedCard = c is referenced to the turnCard() method and checkCards() method. Hence,
            // Every time a CardButton is clicked (i.e., actionPerformed) the turnCard method is initialized.
            c.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent ae){

                    selectedCard = c;
                    turnCard();

                }
            });

            // Fill cardsList with CardButtons
            cardsList.add(c);
        }

        // Randomize the CardButtons positions in the ArrayList cardsList so that the pairs are not laid out next to each other.
        Collections.shuffle(cardsList);

        // Timer that will be initialized in the turnCards() method.
        // The machine checks whether the two pressed CardButtons are equal or not with small delay (750 ms).
        t = new Timer(750, new ActionListener(){
            public void actionPerformed(ActionEvent ae){

                checkCards();

            }

        });

        t.setRepeats(false);

        // GridLayout, because allows for easy configuration of dynamic grids
        setLayout(new GridLayout(this.rows,this.columns));

        // Add the CardButtons to the GridLayout according to user input rows and columns for this game retrieved from settings
        for (CardButton c : cardsList){
            add(c);
        }
    }

    // The game Board of CardButtons is created and methods that apply game logic added and outlined underneath:

    // turnCard() method checks whether the user pressed his/her first or second CardButton
    // Works closely together with the checkCards method where c1 & c2 will be set to null again.
    // Sets the ImageIcon, resized to the CardButton, on the CardButton derived from cardButton which was set a priori
    // on the Cards in the EasyCardDeck or MiddleCardDeck used to create the CardButtons.

    public void turnCard(){
        if (c1 == null && c2 == null){
            c1 = selectedCard;
            c1.setIcon(resizeImageIcon((ImageIcon) c1.getVisibleIcon()));
        }

        if (c1 != null && c1 != selectedCard && c2 == null){
            c2 = selectedCard;
            c2.setIcon(resizeImageIcon((ImageIcon) c2.getVisibleIcon()));

            // Start the timer after two cardButtons were clicked
            // This initializes the CheckCards() method.
            t.start();

        }
    }

    // The CheckCards method does two main things:
    //      1) Check whether the clicked CardButtons are equal or not
    //      2) Check whether one or two of the clicked CardButtons are bombs and assign/delete points of the respective
    //      player accordingly.

    public void checkCards(){

        // c1 and c2 were not yet set to null at this point, hence making it possible to compare the filenames of the buttons
        // thereafter we can check for bombs or not. This was more efficient than the other way around (i.e., first check for
        // bombs and then whether the cardButtons clicked were equal or not.
        // .getVisibleIcon returns Icon object.

        // Check whether Pressed CardButtons are equal
        if (c1.getVisibleIcon() == c2.getVisibleIcon()){//match condition

            // If one is the bomb, the other CardButtons is also a bomb (due to predetermined equality).
            // Predefined bomb during the start of game dependent on user settings, convert Icon object to string and
            // compare with .equals because == breaks and if possible better to use .equals()
            // If so, add a certain amount of bonus points (3) to respective player's score in settings
            if (c1.getVisibleIcon().toString().equals(bomb)){

                game.getSettings().setBombExtraPoint(currentPlayer);

            }

            else {
                // If the first person to find two matching cards is the FirstPlayer add two points instead of two
                if (game.getSettings().getPlayers().get(0).getScore() == 0 && game.getSettings().getPlayers().get(1).getScore() == 0 && currentPlayer == game.getSettings().getPlayers().get(0)){

                    game.getSettings().setExtraPoint(currentPlayer);

                }

                else {
                    // Just add one point to whoever scored a point
                    game.getSettings().setPoint(currentPlayer); //pass that point has been made

                }
            }

            // Disable RadioButtons and keep them turned upside down through setMatched
            c1.setEnabled(false); // Disables the button
            c2.setEnabled(false);

            c1.setMatched(true); // Flags the button as having been matched
            c2.setMatched(true);

            // Finally last check for CardButtons that were equal: Check whether there are cards left through the isGameWon() method.
            if (this.isGameWon()){

                // If so, the game is finished and a winner is communicated to settings
                this.setWinner();

                // Settings communicates back whether it was a draw or a player won and displays respective message.
                if (game.getSettings().getDraw() == true){

                    JOptionPane.showMessageDialog(this, "It Was a Draw, Well Played!" + "\nPress Restart Game to Play Again!");
                }

                else {

                    JOptionPane.showMessageDialog(this, "Congratulations "+game.getSettings().getPlayers().get(game.getSettings().getIntWinner()).getName() + "\nYou Win!" + "\nPress Restart Game to Play Again!");

                }

                // Adds Player that won to the highScores ArrayList, or if draw adds both players
                this.saveHighScores();
            }
        }

        // If the CardButton c1 is not equal to c2 then we check for bombs and apply penalty if present to respective
        // player's score, thereafter, "turns" the icons again and sets c1 & c2 to null so that process can be repeated.
        else {

            if (c1.getVisibleIcon().toString().equals(bomb) || c2.getVisibleIcon().toString().equals(bomb)){

                game.getSettings().takeBombPoint(currentPlayer);

            }

            // Set default back
            c1.setIcon(resizeImageIcon(backIcon));
            c2.setIcon(resizeImageIcon(backIcon));
        }

        c1 = null; //reset c1 and c2
        c2 = null;

        // Calls method that indicates next player's turn, which communicates the "turn" to the communicationPanel for
        // the players, so that they can act accordingly.
        this.nextPlayer();
    }


    // Checks whether there are still CardButtons in the cardsList that have not yet been set to matched yet (cf. CheckCards())
    // Also called in CheckCards(), terminates the game if set to true
    public boolean isGameWon(){

        for(CardButton c: this.cardsList){

            if (c.getMatched() == false){

                return false;

            }
        }

        return true;

    }

    // Customized Rescaling method that takes ImageIcon as input.
    // Rescales the ImageIcon according to estimated height and width of the CardButtons
    public ImageIcon resizeImageIcon(ImageIcon imageIcon){

        // Transform ImageIcon back to Image object for rescaling
        Image image = imageIcon.getImage();

        // Get width and height of Jbuttons
        // Width and height was already calculated in settings based on the amount of rows and columns and the available
        // surface of the gamePanel, coming from mainPanel.
        int buttonWidth = (int) Math.round(game.getSettings().getIconWidth());
        int buttonHeight = (int) Math.round(game.getSettings().getIconHeight());

        // Rescale image size accordingly with estimated CardButton dimensions
        Image resizedImage = image.getScaledInstance(buttonWidth, buttonHeight, Image.SCALE_SMOOTH);

        // Resized image transformed again to ImageIcon and thereafter returned so that this can be set on the
        // CardButton when clicked or as the backIcon.
        ImageIcon resizedImageIcon = new ImageIcon(resizedImage);

        return resizedImageIcon;

    }

    // This methods communicates with settings to determine the current player and set the next player
    public void nextPlayer() {

        // if currentplayer is the same as playerOne, the currentPlayer becomes playerTwo
        if (currentPlayer == game.getSettings().getPlayers().get(0)) {

            currentPlayer = game.getSettings().getPlayers().get(1);

        }

        else {

            currentPlayer = game.getSettings().getPlayers().get(0);

        }

        // Communicate change in currentPlayer to the communicationPanel so that the player(s) can see who's turn it is
        this.communicationPanel.playerLabels.setActivePlayer(currentPlayer);

        // Reinitialize the playerLabels panel through this.communicationPanel.initialize(){this.playerLabels.initialize()}
        // Acts kind of like a repaint, however, reinitialization was easier to implement with consistent changes.
        this.communicationPanel.initialize();

    }

    // After a game is won, or draw between humans, players need to be added to the highscores ArrayList
    // (i.e., setKnownPlayers) this is done in the setWinner() method through game and settings communication.
    // Based on score difference the respective player(s) name and score are added as Player object.
    // setIntWinner is set in settings to communicate the correct message after if(this.isGamewon()) when all CardButtons
    // are set to Matched(true).
    public void setWinner(){

        // if playerOne's score > playerTwo's score
        if (game.getSettings().getPlayers().get(0).getScore() > game.getSettings().getPlayers().get(1).getScore()){

            // Normalize score
            game.getSettings().getPlayers().get(0).setScore(game.getSettings().getPlayers().get(0).getScore()/((this.rows*this.columns)/2));

            // Add Player object of playerOne with its score and name to the list of KnownPlayers (a.k.a. highScores)
            game.addToKnownPlayers(game.getSettings().getPlayers().get(0));
            game.getSettings().setIntWinner(0);

        }

        // Reverse of above
        else if(game.getSettings().getPlayers().get(0).getScore() < game.getSettings().getPlayers().get(1).getScore()){

            //Normalize
            game.getSettings().getPlayers().get(1).setScore(game.getSettings().getPlayers().get(1).getScore()/((this.rows*this.columns)/2));

            // Add to HighScores
            game.addToKnownPlayers(game.getSettings().getPlayers().get(1));
            game.getSettings().setIntWinner(1);

        }

        // Set boolean draw value as true to communicate after if(this.isGameWon()) and add both players to highScores ArrayLIst.
        else {

            game.getSettings().setDraw(true);

            // Normalize
            game.getSettings().getPlayers().get(0).setScore(game.getSettings().getPlayers().get(0).getScore()/((this.rows*this.columns)/2));
            game.getSettings().getPlayers().get(1).setScore(game.getSettings().getPlayers().get(1).getScore()/((this.rows*this.columns)/2));

            game.addToKnownPlayers(game.getSettings().getPlayers().get(0));
            game.addToKnownPlayers(game.getSettings().getPlayers().get(1));

        }

    }

    // Write the adjusted highscores ArrayList back to highScores.txt
    // Called after setWinner() method so that newest player(s) is added to highScores.txt
    public void saveHighScores() {

        try(BufferedWriter writer = new BufferedWriter(new FileWriter("Files/highScores/highScores.txt"))) {

            // for each player object in the ArrayList saved of knownPlayers
            // Write as String "playername-playerscore;"
            for (Player p : game.getKnownPlayers()) {

                String temp = "";
                temp += p.getName() + "-" + p.getScore() + ";";
                writer.write(temp);

            }

        }
        catch (IOException e) {

            e.printStackTrace();

        }
    }
}


