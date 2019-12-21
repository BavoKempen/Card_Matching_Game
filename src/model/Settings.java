package model;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Settings {

    private boolean singlePlayer;
    private int rows;
    private int columns;
    private int difficulty;
    ArrayList<Player> players;
    private String theme;
    private boolean playerT;
    private JLabel label;
    private int pointsPlayerOne;
    private int pointsPlayerTwo;
    private int counter;
    private boolean checked;
    private int number;

    public Settings(){
        this.setRows(4);
        this.setColumns(4);
        this.setDifficulty(1);
        this.theme = "shoes";
        this.setSinglePlayer(true);
        players = new ArrayList<Player>();
        //this.addPlayer("Player One");
        //this.addPlayer("Computer");
        this.pointsPlayerOne = 1;
        this.pointsPlayerTwo = 0;
        this.counter = 0;
        this.setCardChecked(false);

        this.addPlayer(new Player("Default John"));
        this.addPlayer((new Player("AI")));





    }

    public void setTheme(String theme){
        this.theme = theme;
    }
    public String getTheme() {
        return theme;
    }

    public void setRows(int rows){
        this.rows = rows;
    }
    public int getRows(){
        return this.rows;
    }

    public void setColumns(int columns){
        this.columns = columns;
    }
    public int getColumns(){
        return this.columns;
    }

    public void setDifficulty(int difficulty){
        this.difficulty = difficulty;
    }
    public int getDifficulty(){
        return this.difficulty;
    }

    public void addCounter(int counter){this.counter = counter++;}
    public int getCounter(){return this.counter;}


    public void setSinglePlayer(boolean singlePlayer){this.singlePlayer = singlePlayer;}
    public boolean getSinglePlayer(){return this.singlePlayer;}

    public void addPlayer(Player player){
        this.players.add(player);
    }
    public void removePlayers(Player player){this.players.remove(player);
    }

    public ArrayList<Player> getPlayers(){
        return this.players;
    }

    public void setGreen(JLabel label){
        label.setForeground(Color.GREEN);

        label.repaint();
        label.validate();
    }
    public void setRed(JLabel label){
        label.setForeground(Color.RED);

        label.repaint();
        label.validate();
    }

    public void setIntWinner(int number){
        this.number = number;
    }
    public int getIntWinner(){
        return this.number;
    }



    public void setPoint(Player p){
        p.setScore(p.getScore()+1);
        System.out.println(p.getName() + " your score is " + p.getScore());
    }
    public boolean getPoint(){return this.playerT;}

    public void setCardChecked(boolean checked){this.checked = checked;}
    public boolean getChecked(){return this.checked;}


}
