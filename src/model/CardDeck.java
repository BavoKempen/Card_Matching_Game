package model;

import java.util.ArrayList;

// Interface used because method will be overridden by different cardDecks according to difficulty
public interface CardDeck {

    ArrayList<Card> getCards();

}
