package farkle;


public abstract class Player {
    String name;
    int currentScore;
    int totalScore;

    public Player(String name) {
        this.name = name;
        this.currentScore = 0;
        this.totalScore = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCurrentScore() {
        return currentScore;
    }

    public void setCurrentScore(int currentScore) {
        this.currentScore = currentScore;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }
}
