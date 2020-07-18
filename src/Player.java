/*
    The Player class defines information of a player, such as name
    and score. It also manipulates the score of a player.
*/
public class Player {
    protected String name;
    protected int score;

    //Player constructor
    public Player(String name) {
        this.name = name;
        score = 0;
    }

    //Sets name of player
    public void setName(String name) {
        this.name = name;
    }

    //Returns name of player
    public String getName() {
        return name;
    }

    //Returns score of player
    public int getScore() {
        return score;
    }

    //Increments score of player by 1
    public void incrementScore() {
        score++;
    }
}
