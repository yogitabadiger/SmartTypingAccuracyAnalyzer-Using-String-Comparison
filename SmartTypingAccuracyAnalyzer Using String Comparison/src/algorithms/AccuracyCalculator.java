package algorithms;

public class AccuracyCalculator {

    public static double calculateAccuracy(String original, String typed) {
        int distance = LevenshteinDistance.calculate(original, typed);
        int maxLength = Math.max(original.length(), typed.length());
        if (maxLength == 0) return 100;
        return ((double)(maxLength - distance) / maxLength) * 100;
    }

    /** Count characters in the typed string that match the original at the same position. */
    public static int countMatched(String original, String typed) {
        int count = 0;
        int len = Math.min(original.length(), typed.length());
        for (int i = 0; i < len; i++) {
            if (original.charAt(i) == typed.charAt(i)) count++;
        }
        return count;
    }

    /** Count characters in the typed string that do NOT match the original at the same position,
     *  plus any extra characters typed beyond the original length. */
    public static int countUnmatched(String original, String typed) {
        int mismatched = 0;
        int len = Math.min(original.length(), typed.length());
        for (int i = 0; i < len; i++) {
            if (original.charAt(i) != typed.charAt(i)) mismatched++;
        }
        // Extra characters typed beyond original
        mismatched += Math.abs(original.length() - typed.length());
        return mismatched;
    }

    /** Count special characters (non-alphanumeric, non-space) in the typed string. */
    public static int countSpecialChars(String typed) {
        int count = 0;
        for (char c : typed.toCharArray()) {
            if (!Character.isLetterOrDigit(c) && c != ' ') count++;
        }
        return count;
    }

    /** Count spaces in the typed string. */
    public static int countSpaces(String typed) {
        int count = 0;
        for (char c : typed.toCharArray()) {
            if (c == ' ') count++;
        }
        return count;
    }

    /** Count alphanumeric characters in the typed string. */
    public static int countAlphanumeric(String typed) {
        int count = 0;
        for (char c : typed.toCharArray()) {
            if (Character.isLetterOrDigit(c)) count++;
        }
        return count;
    }
}
