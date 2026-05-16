package game;

import java.util.List;
import java.util.Random;
import utils.FileUtils;

public class SentenceManager {

    private static final List<String> sentences =
            FileUtils.readSentences("resources/sentences.txt");

    private static final Random random = new Random();

    public static String getRandomSentence() {

        if (sentences.isEmpty()) {
            return "No sentences available";
        }

        return sentences.get(random.nextInt(sentences.size()));
    }
}