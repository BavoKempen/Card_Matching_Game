package view;

import game.CardButton;
import model.Card;
import model.Game;

import java.util.ArrayList;
import java.util.Random;

public class pveGamePanel extends GamePanel {
    private Game game;
    private CommunicationPanel communicationPanel;
    private Random randomNumber;
    private ArrayList<CardButton> temporaryCardList;

    private CardButton selectedCard;

    public pveGamePanel(Game game, CommunicationPanel communicationPanel) {
        super(game, communicationPanel);

        this.game = game;
        this.communicationPanel = communicationPanel;
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
        /*if (CardButton(Card))
        for (CardButton c : this.cardsList){
            if (c.getMatched() == false){
                int index = randomGenerator.nextInt(catalogue.size());
            }
        }

         */
        this.cardsList.get(3).doClick();
        this.cardsList.get(1).doClick();

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
