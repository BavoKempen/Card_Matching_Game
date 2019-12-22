package view;

import game.CardButton;
import model.Card;
import model.Game;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Random;

public class pveGamePanel extends GamePanel {
    private Game game;
    private CommunicationPanel communicationPanel;
    private Random randomNumber = new Random();

    private ArrayList<Integer> cardSelectList = new ArrayList<Integer>();

    private ArrayList<CardButton> temporaryCardList;

    private CardButton selectedCard;

    public pveGamePanel(Game game, CommunicationPanel communicationPanel) {
        super(game, communicationPanel);

        this.game = game;
        this.communicationPanel = communicationPanel;
        temporaryCardList = new ArrayList<CardButton>();
    }

    @Override
    public void nextPlayer() {
        if (currentPlayer == game.getSettings().getPlayers().get(0)) {
            currentPlayer = game.getSettings().getPlayers().get(1);
        } else {
            currentPlayer = game.getSettings().getPlayers().get(0);
        }


        if (currentPlayer.getName() == "AI" && game.getSettings().getSinglePlayer()) {
            // implement AI stuff
            this.communicationPanel.initialize();
            this.playAITurn();
        }

        this.communicationPanel.playerLabels.setActivePlayer(currentPlayer);
        this.communicationPanel.initialize();
    }

    public void playAITurn() {
        System.out.println("pve Game");
        cardSelectList = new ArrayList<Integer>();


        while (cardSelectList.size() < 2){

            int index = randomNumber.nextInt(cardsList.size());
            int index2 = randomNumber.nextInt(cardsList.size());
            if (this.cardsList.get(index).getMatched()==false && this.cardsList.get(index2).getMatched() == false && index != index2){
                cardSelectList.add(index);
                cardSelectList.add(index2);
            }

        }
        System.out.println("the list "+cardSelectList);
        this.cardsList.get(cardSelectList.get(0)).doClick();
        this.cardsList.get(cardSelectList.get(1)).doClick();
        this.cardSelectList.removeAll(cardSelectList);
        System.out.println("removed list "+ cardSelectList);






        System.out.println("numbers: " + cardSelectList);
        cardSelectList.add(5);
        System.out.println("numbers: " + cardSelectList.size());


        //this.cardsList.get(3).doClick();
        //this.cardsList.get(1).doClick();

        //Implement AI Logic

    }
    @Override
    public void setWinner(){
        if (game.getSettings().getPlayers().get(0).getScore() >= game.getSettings().getPlayers().get(1).getScore()){
            game.addToKnownPlayers(game.getSettings().getPlayers().get(0));
            game.getSettings().setIntWinner(0);
        }
        else {
            game.getSettings().setIntWinner(1);
        }
    }
}
