package model;

import javax.swing.*;

public class Card {

    private Icon icon;
    private boolean turned;

    public Card(Icon icon){
        setIcon(icon);
    }

    public void setIcon(Icon icon){
     this.icon = icon;
    }
    public Icon getIcon(){
        return this.icon;
    }

    public void setTurned(boolean turned) {
        this.turned = turned;
    }
    public boolean getTurned() {
        return this.turned;
    }
}
