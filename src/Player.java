public class Player {
    protected String name;
    protected int score;

    public Player(String name) {
        this.name = name;
        score = 0;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public void incrementScore() {
        score++;
    }
}
