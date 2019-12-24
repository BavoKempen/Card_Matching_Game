package model;

import javax.swing.*;
import java.util.ArrayList;

/*
In sum:
        1) Implements the Interface of CardDeck which solely contains an empty ArrayList<Card> with the getCards() method.
            a) This container method is overridden here by the method since it will go from empty to full upon game Start
            b) EasyCardDeck is used when user sets the difficulty to easy.
 */

// Implements because getCards is an extension of Interface method (e.g., https://www.w3schools.com/java/java_interface.asp)
public class EasyCardDeck implements CardDeck {

    // Declare
    private String themeLocation;
    private ArrayList<Card> cards;

    // Pass themeLocation in constructor so that this information can be used during construction of EasyCardDeck (i.e.,
    // take relevant images out of the relevant folders
    public EasyCardDeck(String themeLocation){

        // Create ArrayList of Card objects
        this.cards = new ArrayList<Card>();

        // Declare the themeLocation that is specified in settings
        this.themeLocation = themeLocation;

        // Store base string to access images
        String defaultMap = "Files/";

        // Amount of pictures present in Files/*/*.jpg
        int pairs = 8;

        // Load in images iteratively and add to the ArrayList<Card> cards.
        // Images are named as 0.jpg, 1.jpg for the purpose of this for loop
        for (int i=0; i<pairs; i++){

            ImageIcon[] icon = new ImageIcon[pairs];
            String imageName = defaultMap+themeLocation+"/"+i+".jpg";
            icon[i] = new ImageIcon(imageName);

            cards.add(new Card(icon[i]));

        }
    }

    // Make getCards() method so that it returns the filled ArrayList<Card> so that it can be accessed in the gamePanel
    @Override
    public ArrayList<Card> getCards() {

        return cards;

    }
}
