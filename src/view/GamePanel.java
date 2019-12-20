package view;

import game.*;
import model.Card;
import model.Game;

import javax.swing.*;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;


public class GamePanel extends JPanel {

    //Info passed from settingspanel

    private Game game;

    private ImageIcon backIcon = new ImageIcon("Files/backIcon/backIcon.jpg");
    ArrayList<CardButton> cardsList;
    private CardButton selectedCard;
    private CardButton c1;
    private CardButton c2;
    private Timer t;





    public GamePanel(Game game){



        //reference game info
        this.game = game;

        //list of button elements that is linked to each card in board (list of cards)


    }

    public void initialize() {

        cardsList = new ArrayList<>();

        this.game.initialize();


        System.out.println("test-"+this.game.getBoard().size());

        for (Card card : this.game.getBoard()){
            CardButton c = new CardButton(card);
            ImageIcon newIcon = reSizeIcon(c, backIcon);
            c.setIcon(newIcon);

            //c.setId(val+100);
            c.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent ae){
                    selectedCard = c;
                    doTurn();


                }

            });
            cardsList.add(c);
        }
        Collections.shuffle(cardsList);
        //set up the timer
        t = new Timer(750, new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                checkCards();
            }

        });

        t.setRepeats(false);

        //set up the board itself
        setLayout(new GridLayout(this.game.getSettings().getRows(),this.game.getSettings().getColumns()));
        for (CardButton c : cardsList){
            add(c);
        }

    }


    public void doTurn(){
        if (c1 == null && c2 == null){
            c1 = selectedCard;
            c1.setIcon(c1.getVisibleIcon());
        }

        if (c1 != null && c1 != selectedCard && c2 == null){
            c2 = selectedCard;
            c2.setIcon(c2.getVisibleIcon());

            t.start();


        }
    }

    public void checkCards(){
        if (c1.getVisibleIcon() == c2.getVisibleIcon()){//match condition
            c1.setEnabled(false); //disables the button
            c2.setEnabled(false);
            c1.setMatched(true); //flags the button as having been matched
            c2.setMatched(true);
            game.getSettings().setPoint(true); //pass that point has been made
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
        System.out.println(game.getSettings().getChecked());
        game.getSettings().setCardChecked(true);
        System.out.println(game.getSettings().getChecked());







    }

    public boolean isGameWon(){
        for(CardButton c: this.cardsList){
            if (c.getMatched() == false){
                return false;
            }
        }
        return true;
    }

    public ImageIcon reSizeIcon(CardButton c, ImageIcon icon){
        
        // get width and height of Jbuttons
        int buttonWidth = (int) c.getPreferredSize().getWidth();
        int buttonHeight = (int) c.getPreferredSize().getHeight();

        // set sized accordingly
        Image img = icon.getImage();
        BufferedImage bi = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        Graphics g = bi.createGraphics();
        g.drawImage(img, 0, 0, buttonWidth, buttonHeight, null);
        ImageIcon newIcon = new ImageIcon(bi);

        return newIcon;

    }
}


