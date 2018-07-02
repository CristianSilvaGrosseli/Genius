package cristian.genius;

public class Player {

    private String name;
    private int score;
    Player(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return this.name;
    }

    public int getScore() {
        return score;
    }
}
