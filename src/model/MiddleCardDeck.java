package model;

import javax.swing.*;
import java.util.ArrayList;

public class MiddleCardDeck implements CardDeck {

    private String themeLocation;
    private ArrayList<Card> cards;

    public MiddleCardDeck(String themeLocation){
        this.cards = new ArrayList<Card>();

        this.themeLocation = themeLocation;
        String defaultMap = "Files/";
        int pairs = 8;
        /*
        while loop? to load in all images from map
         */
        for (int i=0; i<pairs; i++){
            ImageIcon[] icon = new ImageIcon[pairs];
            String imageName = defaultMap+themeLocation+"/"+i+".jpg";
            icon[i] = new ImageIcon(imageName);

            cards.add(new Card(icon[i]));
            /*
            add trap cards underneath
             */
        }
        String bombName = defaultMap+"bomb/"+themeLocation+"_bomb.jpg";
        ImageIcon bombIcon = new ImageIcon(bombName);
        cards.add(new Card(bombIcon));
    }

    @Override
    public ArrayList<Card> getCards() {
        return cards;
    }
}
