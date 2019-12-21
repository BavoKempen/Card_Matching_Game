package game;

import model.Card;

import javax.swing.*;

public class CardButton extends JButton{

    private Card card;
    private Icon visibleIcon;

    public CardButton(Card card) {
        this.card = card;
    }

    //public void setIcons(Icon visibleIcon){this.visibleIcon = visibleIcon;}

    public Icon getVisibleIcon(){return this.card.getIcon();}

    public void setMatched(boolean turned){
        this.card.setTurned(turned);
    }

    public boolean getMatched(){
        return this.card.getTurned();
    }
}

