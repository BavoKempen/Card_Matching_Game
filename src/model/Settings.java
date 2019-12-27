package model;

import java.util.ArrayList;

/*
In sum:
        1) The Settings class has no UI specific UI functionalities but is passed through Game() or sometimes directly
        to the other classes for information retrieval or storage.
            a) Setters: Methods with prefix "set" are used to store info from other class/ method of class -> settings
            b) Getters: Methods with prefix "get" are used to retrieve information settings -> class/ method of class
            c) Default values were assigned to most variables so that on application start-up the Game(settings) can take
            these values as starting point.
 */

public class Settings {

    // Settings communicated from settingsPanel -> settings -> gamePanel with their respective Panel
    // PveOrPvp
    private boolean singlePlayer;

    // RowsAndColumns
    private int rows;
    private int columns;

    // DifficultyLevel
    private int difficulty;

    // ThemesSelection
    private String theme;

    // ArrayList in which the two players in active game are stored from settingsPanel and retrieved for updates of communicationPanel etc
    ArrayList<Player> players;

    // Used to add determine winner, as such its value can be 0/1 and serves as input of index in ArrayList players when
    // the game concludes.
    private int number;

    // If after all CardButtons are set to Matched == true, the score of both players is equal -> set on true
    private boolean draw;

    // Width and height of JFrame on start up, from here calculate the relative width and heights of the other containers
    // present in the JFrame and their components for relative resizing.
    private double height;
    private double width;



    public Settings(){

        // Default values set for settings and loaded into new Game(settings) after calling settings = new Settings() in
        // RunMemoryGame() and at the restartButton
        this.setRows(4);
        this.setColumns(4);
        this.setDifficulty(1);
        this.theme = "shoes";
        this.setSinglePlayer(true);

        // Declare ArrayList and add two default names in case user does not provide one or plays pve (AI).
        // The two default names get overwritten on game start up
        players = new ArrayList<Player>();

        this.addPlayer(new Player("Guest Player"));
        this.addPlayer((new Player("AI")));

        // I assume it will not be a draw
        this.setDraw(false);

    }


    // The following code are the actual Setters and Getters used to communicate in-between components and the game

    // Setters and Getters used to transfer information from user input at settingsPanel to gamePanel through Game(settings)
    // i.e., passed through constructor of respective classes as "Game game" and initialized as "this.game = game" which
    // passes the information up to date to the Jpanel containers in "view" and their components.
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

    public void setSinglePlayer(boolean singlePlayer){

        this.singlePlayer = singlePlayer;

    }

    public boolean getSinglePlayer(){

        return this.singlePlayer;

    }

    // Add Player object (contains its own setters and getters: set/getName and set/getScore) into ArrayList used to
    // indicate the players that are active in the current game state in gamePanel on game start up
    public void addPlayer(Player player){

        this.players.add(player);

    }

    // The ArrayList from which the game gets the Player information in e.g., communicationPanel -> playerLabels
    public ArrayList<Player> getPlayers(){

        return this.players;

    }

    // Used to determine winner/draw at the end of the game
    public void setIntWinner(int number){

        this.number = number;

    }

    public int getIntWinner(){

        return this.number;

    }

    public void setDraw(boolean draw){

        this.draw = draw;

    }

    public boolean getDraw(){

        return this.draw;

    }

    // Add/take points to Player object that is indicated as currentPlayer in the gamePanel
    // Addition of 1 point after finding two standard matching cards
    public void setPoint(Player p){

        p.setScore(p.getScore()+1);

    }

    // Set points to zero if the player accidentally clicks one bomb but cannot find the other within 1 turn
    public void takeBombPoint(Player p){

        p.setScore(0);

    }

    // Add extra points for "dismantling" the bomb i.e., after the player clicks both bombs within 1 turn
    public void setBombExtraPoint(Player p){

        p.setScore(p.getScore()+3);

    }

    // Add extra point if playerOne (i.e., the player that started game) scores as first due to disadvantage
    public void setExtraPoint(Player p){

        p.setScore(p.getScore() + 2);

    }


    // The following setters and getters were used to calculate component and container sizes relative to the global MainFrame
    // These are communicated at game startup and by default (height = 600, width = 1000) in MainFrame.
    public void setWindowHeight(double height){

        this.height = height;

    }

    public void setWindowWidth(double width){

        this.width = width;

    }

    // Based on provided JFrame height/width I calculate relative height/width of containers placed in JFrame MainFrame
    public double getActionPanelHeight(){

        return this.height*.1;

    }

    public double getMainPanelHeight(){

        return this.height*.9;

    }

    public double getActionAndMainPanelWidth(){

        return this.width*.75;

    }

    public double getCommunicationPanelWidth(){

        return this.width*.25;

    }

    // Here based on amount of columns and rows the relative CardButton dimensions are calculated so that the ImageIcon
    // dimensions can be matched accordingly prior to setIcon() (Swing). This information is called in gamePanel in the
    // resizeImageIcon() method.
    public double getIconWidth(){

        return this.getActionAndMainPanelWidth()/columns;

    }

    public double getIconHeight(){

        return this.getMainPanelHeight()/rows;

    }
}
