package utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {

    public static List<String> readSentences(String filePath) {

        List<String> sentences = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

            String line;

            while ((line = br.readLine()) != null) {

                if (!line.trim().isEmpty()) {
                    sentences.add(line);
                }
            }

        } catch (IOException e) {

            System.out.println("Error reading file: " + e.getMessage());
        }

        return sentences;
    }
}