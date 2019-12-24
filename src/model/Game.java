package model;

import java.util.ArrayList;

/*
In sum:
        1) The player class is used to place references between "front" and "back" end. As such, it takes settings in its
        constructor so that information can be passed around and adjusted accordingly between classes if they receive
        game in their constructor.
            a) Upon calling this.game.initialize() the GameBoard is initialized which is constructed out of the needed
            CardDeck (dependent on difficulty) is adjust according to the settings in gamePanel upon start-up gamePanel.
                i) Care: The GameBoard consists of Card (i.e., basically ArrayList with images inside as Icon) objects
                NOT CardButton
            b) Methods to adjust knownPlayers, which is a temporary ArrayList that contains the database of highscores

 */

public class Game {

    // As always declaration
    private Settings settings;
    private GameBoard gameBoard;
    private ArrayList<Player> knownPlayers;

    // Pass settings through constructor so that information upon calling "game = this.game" in other classes gives access
    // to the settings class where information resides and can be adjusted
    public Game(Settings settings){

        // Passes up-to-date settings
        this.settings = settings;

        // Calls that method that initializes the gameBoard with the just defined settings
        this.initialize();

    }

    // Method to update GameBoard class with correct settings when starting up the actual gamePanel
    public void initialize() {

        this.gameBoard = new GameBoard(settings);

    }

    // Method to retrieve gameboard information that was set by initialize method
    public ArrayList<Card> getBoard(){

        return gameBoard.getBoard();

    }

    // The Aorta of information flow in this game which allowed me to access and manipulate information real-time by
    // passing the game class through the respective classes' their constructor. It returns all the current settings and
    // allows for manipulation according to the methods defined in settings!
    public Settings getSettings(){

        return this.settings;

    }

    // Needed for database management: Did not want to place this in settings given that it does not contribute to the actual
    // application state/ game state aside from loading them into the highScoresPanel. Hence, it made sense to just pass
    // and manipulate them here. Also, after every game a new highscore should be added, thus only relevant then.

    // Loads the temporary constructed ArrayList of loadHighScores() method with Player objects into the application at
    // RunMemoryGame.
    public void setKnownPlayers(ArrayList<Player> players) {

        this.knownPlayers = players;

    }

    // Adds a player to the ArrayList if during application at runtime someone wins a game
    public void addToKnownPlayers(Player player){

        this.knownPlayers.add(player);

    }

    // Used to retrieve the highscores that were loaded in at start and after a won game.
    public ArrayList<Player> getKnownPlayers() {

        return this.knownPlayers;

    }
}
