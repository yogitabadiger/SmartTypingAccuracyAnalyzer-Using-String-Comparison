package game;

public class GameController {

    private String currentSentence;
    private long startTime;
    private long endTime;

    public void startGame() {
        currentSentence = SentenceManager.getRandomSentence();
        startTime = System.currentTimeMillis();
    }

    public void endGame() {
        endTime = System.currentTimeMillis();
    }

    public String getCurrentSentence() {
        return currentSentence;
    }

    public long getTimeTaken() {
        return (endTime - startTime) / 1000;
    }
}