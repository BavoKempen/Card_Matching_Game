package view;

import game.*;
import model.Card;
import model.Game;
import model.Player;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;


public class GamePanel extends JPanel {

    //Info passed from settingspanel

    private Game game;

    private Image img;

    {
        try {
            img = ImageIO.read(new File("Files/backIcon/backIcon.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private ImageIcon backIcon = new ImageIcon(img);

    //private ImageIcon backIcon = new ImageIcon("Files/backIcon/backIcon.jpg");
    ArrayList<CardButton> cardsList;
    private CardButton selectedCard;
    private CardButton c1;
    private CardButton c2;
    private Timer t;
    private String bomb;

    private CommunicationPanel communicationPanel;

    Player currentPlayer;



    public GamePanel(Game game, CommunicationPanel communicationPanel) {



        //reference game info
        this.game = game;
        this.communicationPanel = communicationPanel;



        //list of button elements that is linked to each card in board (list of cards)


    }

    public void initialize() {

        currentPlayer = game.getSettings().getPlayers().get(0);
        this.communicationPanel.playerLabels.setActivePlayer(currentPlayer);
        cardsList = new ArrayList<>();
        bomb = String.join("/", "Files", "bomb", String.join("_", game.getSettings().getTheme(), "bomb.jpg"));

        this.game.initialize();


        System.out.println("test-"+this.game.getBoard().size());

        for (Card card : this.game.getBoard()){
            CardButton c = new CardButton(card);
            //Image resizedImage = img.getScaledInstance((int) Math.round(game.getSettings().getIconWidth()), (int) Math.round(game.getSettings().getIconHeight()), Image.SCALE_SMOOTH);
            //ImageIcon newIcon = reSizeIcon(c, backIcon);
            c.setIcon(resizeImageIcon(backIcon));


            //c.setId(val+100);
            c.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent ae){
                    selectedCard = c;
                    //System.out.println("what is c: "+c.getVisibleIcon());
                    doTurn();


                }

            });
            // fill cardList
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
            c1.setIcon(resizeImageIcon((ImageIcon) c1.getVisibleIcon()));
        }

        if (c1 != null && c1 != selectedCard && c2 == null){
            c2 = selectedCard;
            c2.setIcon(resizeImageIcon((ImageIcon) c2.getVisibleIcon()));

            t.start();


        }
    }

    public void checkCards(){

        if (c1.getVisibleIcon().toString().equals(bomb) || c2.getVisibleIcon().toString().equals(bomb)){
            System.out.println("it works!");
        }

        if (c1.getVisibleIcon() == c2.getVisibleIcon()){//match condition
            if (c1.getVisibleIcon().toString().equals(bomb)){
                game.getSettings().setBombExtraPoint(currentPlayer);
            }
            else {
                game.getSettings().setPoint(currentPlayer); //pass that point has been made

            }
            c1.setEnabled(false); //disables the button
            c2.setEnabled(false);
            c1.setMatched(true); //flags the button as having been matched
            c2.setMatched(true);
            if (this.isGameWon()){
                this.setWinner();
                JOptionPane.showMessageDialog(this, game.getSettings().getPlayers().get(game.getSettings().getIntWinner()).getName() + "You win!");
                //save highscores
                this.saveHighScores();
                System.exit(0);
            }


        }


        else{
            if (c1.getVisibleIcon().toString().equals(bomb) || c2.getVisibleIcon().toString().equals(bomb)){
                game.getSettings().takeBombPoint(currentPlayer);
            }
            c1.setIcon(resizeImageIcon(backIcon));
            c2.setIcon(resizeImageIcon(backIcon));

        }
        c1 = null; //reset c1 and c2
        c2 = null;

        this.nextPlayer();
    }



    public boolean isGameWon(){
        for(CardButton c: this.cardsList){
            if (c.getMatched() == false){
                return false;
            }
        }
        return true;
    }

    public ImageIcon resizeImageIcon(ImageIcon imageIcon){

        Image image = imageIcon.getImage();
        // get width and height of Jbuttons
        int buttonWidth = (int) Math.round(game.getSettings().getIconWidth());
        int buttonHeight = (int) Math.round(game.getSettings().getIconHeight());

        // set sized accordingly
        Image resizedImage = image.getScaledInstance(buttonWidth, buttonHeight, Image.SCALE_SMOOTH);
        ImageIcon resizedImageIcon = new ImageIcon(resizedImage);

        return resizedImageIcon;

    }

    public void nextPlayer() {
        if (currentPlayer == game.getSettings().getPlayers().get(0)) {
            currentPlayer = game.getSettings().getPlayers().get(1);
        } else {
            currentPlayer = game.getSettings().getPlayers().get(0);
        }

        this.communicationPanel.playerLabels.setActivePlayer(currentPlayer);
        this.communicationPanel.initialize();
    }


    public void saveHighScores() {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("Files/highScores/highScores.txt"))) {
            for (Player p : game.getKnownPlayers()) {
                String temp = "";
                temp += p.getName() + "-" + p.getScore() + ";";
                writer.write(temp);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void setWinner(){
        if (game.getSettings().getPlayers().get(0).getScore() > game.getSettings().getPlayers().get(1).getScore()){
            game.addToKnownPlayers(game.getSettings().getPlayers().get(0));
            game.getSettings().setIntWinner(0);
        }
        else if(game.getSettings().getPlayers().get(0).getScore() < game.getSettings().getPlayers().get(1).getScore()){
            game.addToKnownPlayers(game.getSettings().getPlayers().get(1));
            game.getSettings().setIntWinner(1);
        }
        else {
            game.addToKnownPlayers(game.getSettings().getPlayers().get(0));
            game.addToKnownPlayers(game.getSettings().getPlayers().get(1));
        }

    }

}


