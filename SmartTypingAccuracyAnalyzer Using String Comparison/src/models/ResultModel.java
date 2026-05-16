package models;

public class ResultModel {

    private double accuracy;
    private double speed;
    private int matchedChars;
    private int unmatchedChars;
    private int specialChars;
    private int spaces;
    private int alphanumericChars;
    private int totalTyped;
    private long timeTaken;

    public ResultModel(double accuracy, double speed,
                       int matchedChars, int unmatchedChars,
                       int specialChars, int spaces, int alphanumericChars,
                       int totalTyped, long timeTaken) {
        this.accuracy = accuracy;
        this.speed = speed;
        this.matchedChars = matchedChars;
        this.unmatchedChars = unmatchedChars;
        this.specialChars = specialChars;
        this.spaces = spaces;
        this.alphanumericChars = alphanumericChars;
        this.totalTyped = totalTyped;
        this.timeTaken = timeTaken;
    }

    public double getAccuracy() { return accuracy; }
    public double getSpeed() { return speed; }
    public int getMatchedChars() { return matchedChars; }
    public int getUnmatchedChars() { return unmatchedChars; }
    public int getSpecialChars() { return specialChars; }
    public int getSpaces() { return spaces; }
    public int getAlphanumericChars() { return alphanumericChars; }
    public int getTotalTyped() { return totalTyped; }
    public long getTimeTaken() { return timeTaken; }
}
