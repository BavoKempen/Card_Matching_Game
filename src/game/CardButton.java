package game;

import model.Card;

import javax.swing.*;

/*
In Sum:
        1) The gamePanel will consist of JButtons for click registration, that is why we create here the CardButton class
        that extends JButton giving it the functionality of a JButton
            a) Goes through the Card class to check for Icons and set/get them accordingly
            b) Likewise to give and get their matched status (i.e., if both are found in one turn).
 */
public class CardButton extends JButton{

    // Declare
    private Card card;

    // Pass Card class through constructor to be able to pass information through methods to and from the Card class
    public CardButton(Card card) {

        this.card = card;

    }

    // get the icon that is shown
    public Icon getVisibleIcon(){

        return this.card.getIcon();
    }

    // set a card to matched status Card class
    public void setMatched(boolean turned){

        this.card.setTurned(turned);

    }

    // get a cards matched status in the Card class
    public boolean getMatched(){

        return this.card.getTurned();

    }
}

