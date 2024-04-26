package farkle;

public class ComputerPlayer extends Player {
    private Difficulty difficulty;

    public ComputerPlayer(String name) {
        super(name);
        this.difficulty = Difficulty.EASY;
    }

    public ComputerPlayer(String name, Difficulty difficulty) {
        super(name);
        this.difficulty = difficulty;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }
}
