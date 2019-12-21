package model;

import java.util.ArrayList;

public class Game {

    private Settings settings;
    private GameBoard gameBoard;
    private ArrayList<Player> knownPlayers;

    public Game(Settings settings){
        this.settings = settings;
        this.initialize();
    }

    public void initialize() {
        this.gameBoard = new GameBoard(settings);
    }

    public ArrayList<Card> getBoard(){
        return gameBoard.getBoard();
    }
    public Settings getSettings(){return this.settings;}
    public void setKnownPlayers(ArrayList<Player> players) {
        this.knownPlayers = players;
    }
    public void addToKnownPlayers(Player player){
        this.knownPlayers.add(player);
    }
    public ArrayList<Player> getKnownPlayers() {
        return this.knownPlayers;
    }
}
