package model;

/*

In Sum:
        1) Player class enables us to group the name and score of a player into a single object
            a) These are put in an ArrayList and derived from there based on their index in list
            b) Every Player object has setter and getter for both name and score so that through settings these can be
            updated at all time and stored accordingly.
            c) Also used to construct ArrayList of high scores that consists of Player objects with respective name and score
            d) Type double was chosen for the scores variable to enable normalization in the high scores

 */

public class Player {

    // Declare variables in Player Class
    private String name;
    private double score;

    // Pass name through constructor so that name can be set accordingly with setName method
    // As such it is used when creating a new Player object and name is passed to indicate new Player with score = 0
    // thereafter score can be added (e.g., loadHighScores() in RunMemoryGame or during the game itself upon making a point).
    // Also, was initially created for the game and gamePanel and thereafter recycled in loadHighScores in which we just
    // overwrite the this.setScore(0) with the respective player's score
    public Player(String name){

        this.setName(name);

        this.setScore(0);
    }

    // setters and getters to access and manipulate Player object name and score
    public void setName(String name){

        this.name = name;

    }

    public String getName(){

        return this.name;

    }

    public void setScore(double score){

        this.score = score;

    }

    public double getScore(){

        return this.score;

    }
}
