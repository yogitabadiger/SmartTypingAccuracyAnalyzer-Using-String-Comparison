package algorithms;

public class SpeedCalculator {

    public static double calculateWPM(String typedText, long timeInSeconds) {

        int wordCount = typedText.trim().split("\\s+").length;

        double minutes = timeInSeconds / 60.0;

        if (minutes == 0) {
            return 0;
        }

        return wordCount / minutes;
    }
}