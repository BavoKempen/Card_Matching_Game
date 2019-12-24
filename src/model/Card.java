package model;

import javax.swing.*;

/*
In sum:
        1) Cards were created in the CardDecks where the images are loaded as Icon. As such, the Card class serves as a
        communication class between the CardButtons which are added dynamically in the game and the completely filled CardDecks
            a) icon is passed through constructor to set an icon on the respective card in the ArrayList<Card> which are
            thereafter added to the JButtons on the panel.
            b) Mostly due to the creation of front end (GUI) and game logic separately. When thinking on how to dynamically
            load the cards to the cardButtons this was needed as in-between passage because I did not want to load the icons
            in the game itself, solely create the buttons on which can be pressed take from the ArrayList<Card> that have fixed
            number of pairs (i.e., available images in Files/shoes/*.jpg or Files/worldleaders/*.jpg.
            c) As such we still have access to the raw filepaths which enables easy comparison x.toString.equals(x) during
            the game.
 */

public class Card {

    // Declare Icon variable
    private Icon icon;

    // The turned communicates whether the CardButton with the icons attached are the same or not after turning them around
    // on button click by user.
    private boolean turned;

    // Pass Icon through constructor to attach it to Card object used in CardDeck, GameBoard and Game itself through CardButton.
    public Card(Icon icon){

        setIcon(icon);

    }

    // setIcon is a method of JButton in which we pass the Icon of the card
    public void setIcon(Icon icon){

     this.icon = icon;

    }

    // Likewise getIcon is a method of JButton through which we retrieve the Icon on the respective card
    public Icon getIcon(){

        return this.icon;

    }

    // Card class equivalent of setMatched, getMatched in CardButton
    // sets a Card to true if turned twice (i.e., two JButtons pressed with same ImageIcon on it)
    public void setTurned(boolean turned) {

        this.turned = turned;

    }

    public boolean getTurned() {

        return this.turned;

    }
}
