package model;

import java.util.ArrayList;
/*
In sum:
        1) The Cards are retrieved from the relevant EasyCardDeck/MediumCardDeck and added twice to the board which is
        again an ArrayList<Card>, however, now adjusted according to user input in the settingsPanel and this will be loaded
        in the gamePanel, not full CardDeck
            a) Each Card is also added twice to board because you need two for matching pairs.

 */

public class GameBoard {

    // Create placeholder to dynamically determine which CardDeck to load (i.e., no bomb or with bomb) with switch
    private CardDeck placeHolderDeck;

    // New ArrayList<Card> of cards that will be used for gamePanel construction
    private ArrayList<Card> board;

    // Here we pass the settings instead of game, given that game initializes the GameBoard class in the gamePanel
    private Settings settings;

    public GameBoard(Settings settings){

        // Instantiate
        this.board = new ArrayList<>();
        this.settings = settings;

        // Instantiate possible decks which take theme in their constructor
        CardDeck deckEasy = new EasyCardDeck(settings.getTheme());
        CardDeck deckMedium = new MiddleCardDeck(settings.getTheme());

        // Dependent on difficulty passed to the settings the placeHolderDeck changes
        switch (settings.getDifficulty()){
            case 1:
                placeHolderDeck = deckEasy;
                break;
            case 2:
                placeHolderDeck = deckMedium;
                break;

        }


        // Spread deck over board according to rows and columns, and divide by two because each card will be added twice
        // Shuffle will happen in gamePanel for convenience
        for (int i = 0 ; i < (this.settings.getColumns()*this.settings.getRows())/2 ; i++){

            board.add(placeHolderDeck.getCards().get(i));
            board.add(placeHolderDeck.getCards().get(i));

        }
    }

    // Method to access board ArrayList<Card> in other classes
    public ArrayList<Card> getBoard(){
        return board;
    }
}
