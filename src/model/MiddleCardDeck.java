package model;

import javax.swing.*;
import java.util.ArrayList;

// cf. EasyCardDeck for basic clarification
// MiddleCardDeck will add one bombcard to the CardDeck
public class MiddleCardDeck implements CardDeck {

    // Declare
    private ArrayList<Card> cards;

    public MiddleCardDeck(String themeLocation){

        // Declare ArrayList and the selected theme by user
        this.cards = new ArrayList<>();

        // Again set defaults cf. EasyCardDeck
        String defaultMap = "Files/";
        int PAIRS = 18;

        // Add the bomb Card a priori to the list of Cards
        String bombName = defaultMap+"bomb/"+themeLocation+"_bomb.jpg";
        ImageIcon bombIcon = new ImageIcon(bombName);
        cards.add(new Card(bombIcon));

        // Same as in EasyCardDeck
        for (int i=0; i<PAIRS; i++){

            ImageIcon[] icon = new ImageIcon[PAIRS];
            String imageName = defaultMap+themeLocation+"/"+i+".jpg";
            icon[i] = new ImageIcon(imageName);

            cards.add(new Card(icon[i]));

        }

    }

    // Override the Interface empty method body with return cards (i.e., ArrayList of card objects)
    @Override
    public ArrayList<Card> getCards() {

        return cards;

    }
}
