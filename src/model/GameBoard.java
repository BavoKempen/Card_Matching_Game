package model;

import java.util.ArrayList;

public class GameBoard {

    private CardDeck placeHolderDeck;
    //2d array for board
    private ArrayList<Card> board;
    private Settings settings;

    public GameBoard(Settings settings){
        this.board = new ArrayList<>();

        this.settings = settings;

        //default -> ifelse
        CardDeck deck = new EasyCardDeck(settings.getTheme());
        CardDeck deckMedium = new MiddleCardDeck(settings.getTheme());

        switch (settings.getDifficulty()){
            case 1:
                placeHolderDeck = deck;
                break;
            case 2:
                placeHolderDeck = deckMedium;
                break;

        }

        /*
        Spread deck over board according to rows and columns
         */
        System.out.println("b- "+this.settings.getColumns() + " " + this.settings.getRows());
        System.out.println("c- " + board.size());
        for (int i = 0 ; i < (this.settings.getColumns()*this.settings.getRows())/2 ; i++){
            board.add(placeHolderDeck.getCards().get(i));
            board.add(placeHolderDeck.getCards().get(i));
        }
        System.out.println("c- " + board.size());

        //Implement shuffle         Collections.shuffle(board);

    }

    public ArrayList<Card> getBoard(){
        return board;
    }
}
