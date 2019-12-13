package view;

import game.*;

import javax.swing.*;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;


public class GamePanel extends JPanel {

    //Info passed from settingspanel
    int rows = 4;
    int columns = 4;
    String theme;


    private final ImageIcon backIcon = new ImageIcon("Files/shoes/8.jpg");
    private List<CardClass> cards;
    private CardClass selectedCard;
    private CardClass c1;
    private CardClass c2;
    private Timer t;


    public GamePanel(){


        //test
        System.out.print(rows);
        System.out.print(columns);

        int pairs = 8;
        List<CardClass> cardsList = new ArrayList<>();
        List<ImageIcon> cardIconList = new ArrayList<>();

        for (int i=0; i<pairs; i++){
            ImageIcon[] icon = new ImageIcon[pairs];
            String imageName = "Files/shoes/"+i+".jpg";
            icon[i] = new ImageIcon(imageName);
            cardIconList.add(icon[i]);
            cardIconList.add(icon[i]);
        }

        Collections.shuffle(cardIconList);


        for (Icon icon : cardIconList){
            CardClass c = new CardClass();
            c.setIcon(backIcon);
            c.setIcons(icon);

            //c.setId(val+100);
            c.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent ae){
                    selectedCard = c;
                    doTurn();
                }
            });
            cardsList.add(c);
        }
        this.cards = cardsList;
        //set up the timer
        t = new Timer(750, new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                checkCards();
            }
        });

        t.setRepeats(false);

        //set up the board itself
        setLayout(new GridLayout(this.rows,this.columns));
        for (CardClass c : cards){
            add(c);
        }

    }

    public void doTurn(){
        if (c1 == null && c2 == null){
            c1 = selectedCard;
            c1.setIcon(c1.getIcons());
        }

        if (c1 != null && c1 != selectedCard && c2 == null){
            c2 = selectedCard;
            c2.setIcon(c2.getIcons());

            t.start();

        }
    }

    public void checkCards(){
        if (c1.getIcons() == c2.getIcons()){//match condition
            c1.setEnabled(false); //disables the button
            c2.setEnabled(false);
            c1.setMatched(true); //flags the button as having been matched
            c2.setMatched(true);
            if (this.isGameWon()){
                JOptionPane.showMessageDialog(this, "You win!");
                System.exit(0);
            }
        }

        else{
            c1.setIcon(backIcon);
            c2.setIcon(backIcon);

        }
        c1 = null; //reset c1 and c2
        c2 = null;
    }

    public boolean isGameWon(){
        for(CardClass c: this.cards){
            if (c.getMatched() == false){
                return false;
            }
        }
        return true;
    }
}


